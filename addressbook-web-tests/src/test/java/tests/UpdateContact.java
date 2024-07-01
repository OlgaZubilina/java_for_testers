package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class UpdateContact extends TestBase {

    @Test
    void canUpdateContact() {
   if(!app.contacts().isContactPresent()){
    app.contacts().createContact(new ContactData("id","firstname", "middlename", "lastname", "nickname", "title", "company", "adress", "home", "mobile", "work", "fax", "email", "email2", "email3", "homepage"));
        }
   app.contacts().updateContact(new ContactData().withAdress("updated address"));
   }
}
