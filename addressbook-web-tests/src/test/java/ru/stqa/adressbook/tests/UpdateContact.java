package ru.stqa.adressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.adressbook.common.CommonFunctions;
import ru.stqa.adressbook.model.ContactData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class UpdateContact extends TestBase {

    @Test
    void canUpdateContact() {
   if(!app.contacts().isContactPresent()){
    app.contacts().createContact(new ContactData("id","firstname",  "lastname", "adress", ""));
        }
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData().withFirstname("update firstname").withPhoto(CommonFunctions.randomFile("src/test/resources/images"));
        app.contacts().updateContact(oldContacts.get(index), testData);
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index,testData.withId(oldContacts.get(index).id()).withAdress("").withPhoto(""));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
   }

   @Test
    void canUpdateContactHbm() {
        if(!app.contacts().isContactPresent()){
            app.contacts().createContact(new ContactData("id","firstname",  "lastname", "adress", ""));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData().withFirstname("update firstname").withPhoto(CommonFunctions.randomFile("src/test/resources/images"));
        app.contacts().updateContact(oldContacts.get(index), testData);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index,testData.withId(oldContacts.get(index).id()).withPhoto(""));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
}

