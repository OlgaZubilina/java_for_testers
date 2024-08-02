package ru.stqa.adressbook.tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.adressbook.common.CommonFunctions;
import ru.stqa.adressbook.model.ContactData;
import ru.stqa.adressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class AddContactToGroup extends TestBase
{
@Test
 public void canAddContactToGroup() {

    Allure.step("Checking precondition",step ->{if (app.groups().getCount() == 0) {//создание новой группы при отсутствии созданных групп
        app.groups().createGroup(new GroupData("", "name", "header", "footer"));
    }});
    //1. Получаем первоначальный список контактов с группами
    var oldContactsInGroup = app.jdbc().getListContactsInGroup();
    //2Добавляем контакт в группу
    //2.1 Проверяем наличие группы
     var group = app.groups().getList().get(0); //запоминаем группу

    //2.2 Выбираем контакт без текущей группы группы
    var allContacts = ((app.contacts().getList()));
    app.contacts().filterContactsByGroup(group);
    var contactsInGroup = app.contacts().getList();
    allContacts.removeAll(contactsInGroup);

   if (allContacts.size() == 0) {//создаем контакт без группы если в предыдущем шаге список пустой
        var contact = new ContactData()
                .withFirstname(CommonFunctions.randomString(6))
                .withLastname(CommonFunctions.randomString(6))
                .withAdress(CommonFunctions.randomString(6))
                .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));
        app.contacts().createContact(contact);
        allContacts = app.contacts().getList();
        app.contacts().filterContactsByGroup(group);
        allContacts.removeAll(contactsInGroup);
   }
        //contacts = app.jdbc().getContactListWithoutGroupTest(group);}//заносим список контактов без группы повторно
    //выбираем рандомный контакт из отфильтрованного списка

    var rnd = new Random();
    var index = rnd.nextInt(allContacts.size());
    var contact = allContacts.get(index);
    app.contacts().filterContactsByAll();
    app.contacts().addToGroup(contact,group);
    
    //3 Получаем новый список контактов с группами
    var newContactsInGroup = app.jdbc().getListContactsInGroup();
    //4 Сверяем списки

  var expectedList = new ArrayList<>(oldContactsInGroup);
    expectedList.add(contact);
    Comparator<ContactData> compareById = (o1, o2) -> {
        return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
    };
    newContactsInGroup.sort(compareById);
    expectedList.sort(compareById);
    Allure.step("Validating result",step ->{Assertions.assertEquals(newContactsInGroup, expectedList);});


}

}
