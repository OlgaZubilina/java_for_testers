package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;


public class CreateContact extends TestBase {


    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstname : List.of("","firstname")){
            for (var lastname : List.of("","lastname")){
                for (var address : List.of("","address")){
                    result.add(new ContactData()
                            .withFirstname(firstname)
                            .withLastname(lastname)
                            .withAdress(address));
                }
            }
        }
        for (int i=0;i<5;i++){
            result.add(new ContactData()
                    .withFirstname(randomString(i*10))
                    .withLastname(randomString(i*10))
                    .withAdress(randomString(i*10)));
        }
        return result;

    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateContact(ContactData contact) {
    int contactsCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactsCount = app.contacts().getCount();
        Assertions.assertEquals(newContactsCount,contactsCount+1);

    }


}
