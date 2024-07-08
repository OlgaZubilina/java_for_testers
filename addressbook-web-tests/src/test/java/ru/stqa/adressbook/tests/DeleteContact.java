package ru.stqa.adressbook.tests;

import ru.stqa.adressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeleteContact extends TestBase {

    @Test
    public void canRemoveContact() {
        if (!app.contacts().isContactPresent()) {//создание нового контакта  при отсутствии
            app.contacts().createContact(new ContactData());
        }

        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().deleteContact(oldContacts.get(index));
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);

    }

@Test
    public void canRemoveContactHbm(){

    if (!app.contacts().isContactPresent()) {//создание нового контакта  при отсутствии
        app.contacts().createContact(new ContactData());
    }

    var oldContacts = app.hbm().getContactList();
    var rnd = new Random();
    var index = rnd.nextInt(oldContacts.size());
    app.contacts().deleteContact(oldContacts.get(index));
    var newContacts = app.hbm().getContactList();
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.remove(index);
    Assertions.assertEquals(newContacts, expectedList);

}
}



