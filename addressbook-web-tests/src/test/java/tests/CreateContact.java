package tests;

import manager.ContactsHelper;
import model.ContactData;
import org.junit.jupiter.api.Test;


public class CreateContact extends TestBase {

    @Test
    public void canCreateContact() {

        app.contacts().createContact(new ContactData("firstname", "middlename", "lastname", "nickname", "title", "company", "adress", "home", "mobile", "work", "fax", "email", "email2", "email3", "homepage"));


    }

    @Test
    public void canCreateEmptyContact() {

        app.contacts().createContact(new ContactData());
    }

    @Test
    public void canCreateContactWidthAdress() {

        app.contacts().createContact(new ContactData().withAdresss("adress1"));
    }
}
