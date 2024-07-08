package ru.stqa.adressbook.tests;

import ru.stqa.adressbook.common.CommonFunctions;
import ru.stqa.adressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeleteContact extends TestBase {

    @Test
    public void canRemoveContact() {
        if (app.jdbc().getContactList().size() == 0) {//создание нового контакта  при отсутствии
            var contact = new ContactData()
                    .withFirstname(CommonFunctions.randomString(6))
                    .withLastname(CommonFunctions.randomString(6))
                    .withAdress(CommonFunctions.randomString(6))
                    .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));
            app.contacts().createContact(contact);}

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
    if (app.hbm().getContactList().size() == 0) {//создание нового контакта  при отсутствии
        var contact = new ContactData()
                .withFirstname(CommonFunctions.randomString(6))
                .withLastname(CommonFunctions.randomString(6))
                .withAdress(CommonFunctions.randomString(6))
                .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));
        app.contacts().createContact(contact);}

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



