package ru.stqa.adressbook.tests;

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

  if (app.jdbc().getContactListWithoutGroup().size() == 0) {//создание нового контакта  при отсутствии его в отфильтрованном списке без групп
         var contact = new ContactData()
                   .withFirstname(CommonFunctions.randomString(6))
                    .withLastname(CommonFunctions.randomString(6))
                 .withAdress(CommonFunctions.randomString(6))
                   .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));
      app.contacts().createContact(contact);}

      if (app.jdbc().getGroupList().size() == 0) {//создание новой группы при отсутствии созданных групп
           app.groups().createGroup(new GroupData("", "name", "header", "footer"));
        }
       var oldContactsInGroup = app.jdbc().getListContactsInGroup();
      var contacts = app.jdbc().getContactList();
      var rnd = new Random();
      var index = rnd.nextInt(contacts.size());
      var group = app.jdbc().getGroupList().get(0);
       app.contacts().addToGroup(contacts.get(index),group);
        var newContactsInGroup = app.jdbc().getListContactsInGroup();
        var expectedList = new ArrayList<>(oldContactsInGroup);
        expectedList.add(contacts.get(index));
     Comparator<ContactData> compareById = (o1, o2) -> {
         return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
     };
     newContactsInGroup.sort(compareById);
     expectedList.sort(compareById);
        Assertions.assertEquals(newContactsInGroup, expectedList);


}}
