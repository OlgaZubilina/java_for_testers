package ru.stqa.adressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.stqa.adressbook.common.CommonFunctions;
import ru.stqa.adressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.adressbook.model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;


public class CreateContact extends TestBase {


    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
        for (var firstname : List.of("", "firstname")) {
            for (var lastname : List.of("", "lastname")) {
                for (var address : List.of("", "address")) {
                    result.add(new ContactData()
                            .withFirstname(firstname)
                            .withLastname(lastname)
                            .withAdress(address)
                            .withPhoto(CommonFunctions.randomFile("src/test/resources/images")));
                }
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {
        });
        result.addAll(value);
        return result;
    }


    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateContacts(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()).withAdress("").withPhoto(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);


    }

    public static List<ContactData> ContactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
        for (var firstname : List.of("", "firstname")) {
            for (var lastname : List.of("", "lastname")) {
                for (var address : List.of("", "address")) {
                    result.add(new ContactData()
                            .withFirstname(firstname)
                            .withLastname(lastname)
                            .withAdress(address)
                            .withPhoto(CommonFunctions.randomFile("src/test/resources/images")));
                }
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {
        });
        result.addAll(value);
        return result;
    }


    @ParameterizedTest
    @MethodSource("randomContactProvider")
    public void canCreateContact(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()).withPhoto(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);


    }

    public static Stream <ContactData> randomContactProvider() {
        Supplier<ContactData> randomContact = () -> new ContactData()
                .withFirstname(CommonFunctions.randomString(10))
                .withLastname(CommonFunctions.randomString(10))
                .withAdress(CommonFunctions.randomString(10))
                .withPhoto(CommonFunctions.randomFile("src/test/resources/images"))
                .withHome(CommonFunctions.randomInteger(10))
                .withMobile(CommonFunctions.randomInteger(10))
                .withWork(CommonFunctions.randomInteger(10))
                .withEmail(CommonFunctions.randomString(8))
                .withEmail2(CommonFunctions.randomString(8))
                .withEmail3(CommonFunctions.randomString(8));
        return Stream.generate(randomContact).limit(5);
    }
}
