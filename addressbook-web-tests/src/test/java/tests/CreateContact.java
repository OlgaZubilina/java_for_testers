package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
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
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()).withAdress(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);



    }


}
