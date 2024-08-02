package ru.stqa.adressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.adressbook.common.CommonFunctions;
import ru.stqa.adressbook.model.ContactData;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase{


    @Test
    void testEmails(){
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var emails = app.contacts().getEmails(contact);
        var expected = Stream.of(contact.email(),contact.email2(),contact.email3())
                .filter(s->s!=null && ! "".equals(s))
                .collect(Collectors.joining("\n"));

        Assertions.assertEquals(expected,emails);
    }

    @Test
    void testPhones(){
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var phones = app.contacts().getPhones(contact);
        var expected = Stream.of(contact.home(),contact.mobile(),contact.work())
                .filter(s->s!=null && ! "".equals(s))
                .collect(Collectors.joining("\n"));

        Assertions.assertEquals(expected,phones);
    }


    /*@Test
    void testInfo(){
        if (app.contacts().getList().size() == 0) {//создание нового контакта  при отсутствии
            var contact = new ContactData()
                    .withFirstname(CommonFunctions.randomString(6))
                    .withLastname(CommonFunctions.randomString(6))
                    .withAdress(CommonFunctions.randomString(6))
                    .withPhoto(CommonFunctions.randomFile("src/test/resources/images"))
                    .withHome(CommonFunctions.randomInteger(10))
                    .withMobile(CommonFunctions.randomInteger(10))
                    .withWork(CommonFunctions.randomInteger(10))
                    .withEmail(CommonFunctions.randomString(8))
                    .withEmail2(CommonFunctions.randomString(8))
                    .withEmail3(CommonFunctions.randomString(8));
            app.contacts().createContact(contact);}
            var contacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(contacts.size());
        var contact = contacts.get(index);
        var contactInfo = app.contacts().getAddress(contact)+"\n"+app.contacts().getEmails(contact)+"\n"+app.contacts().getPhones(contact);
        var expected = app.contacts().getInfoFromContactPage(contact);
        Assertions.assertEquals(expected,contactInfo);
    }*/

}
