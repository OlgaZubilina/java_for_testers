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

import static ru.stqa.adressbook.tests.TestBase.app;

public class RemoveContactFromGroup extends TestBase {

    @Test
    public void canRemoveContactFromGroup() {
        Allure.step("Checking precondition", step ->{if (app.groups().getList().size()==0)
            app.groups().createGroup(new GroupData().withName("test"));});

        var group = app.groups().getList().get(0);

        app.contacts().filterContactsByGroup(group);
        if (app.contacts().getList().size()==0)
            app.contacts().createContactWithGroup(new ContactData().withFirstname("test").withPhoto(CommonFunctions.randomFile("src/test/resources/images")),group);
        app.contacts().filterContactsByGroup(group);
        var oldContactsWithoutGroup = app.jdbc().getContactListWithoutGroup();
var contactsFilter = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(contactsFilter.size());
       var contact = contactsFilter.get(index);

        app.contacts().removeContactFromGroup(contact, group);
        var newContactsWithoutGroup = app.jdbc().getContactListWithoutGroup();
        var expectedList = new ArrayList<>(oldContactsWithoutGroup);
        expectedList.add(contact);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContactsWithoutGroup.sort(compareById);
        expectedList.sort(compareById);
        Allure.step("Validating result",step ->{Assertions.assertEquals(newContactsWithoutGroup, expectedList);});


}

}
