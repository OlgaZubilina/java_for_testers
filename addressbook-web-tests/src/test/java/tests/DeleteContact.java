package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Test;

public class DeleteContact extends TestBase {

    @Test
    public void canRemoveContact() {
        if (!app.contacts().isContactPresent()) {//создание нового контакта  при отсутствии
            app.contacts().createContact(new ContactData());
        }
        app.contacts().deleteContact();

    }




}
