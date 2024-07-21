package ru.stqa.adressbook.manager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import ru.stqa.adressbook.manager.hbm.ContactRecord;
import ru.stqa.adressbook.manager.hbm.GroupRecord;
import ru.stqa.adressbook.model.ContactData;
import ru.stqa.adressbook.model.GroupData;

import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase {
    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory =
                new Configuration()
                        //.addAnnotatedClass(Book.class)
                        .addAnnotatedClass(GroupRecord.class)
                        .addAnnotatedClass(ContactRecord.class)
                        .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook")
                        .setProperty(AvailableSettings.USER, "root")
                        .setProperty(AvailableSettings.PASS, "")
                        .buildSessionFactory();
    }

    static List<GroupData> convertList(List<GroupRecord> records) {
       return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    public List<GroupData> getGroupList() {
        return convertList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }


    static List<ContactData> convertListContacts(List<ContactRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    private static ContactData convert(ContactRecord record) {
        return new ContactData().withId(""+record.id)
                .withFirstname(record.firstname)
                .withLastname(record.lastname)
                .withAdress(record.address)
                .withHome(record.home)
                .withMobile(record.mobile)
                .withWork(record.work)
                .withEmail(record.email)
                .withEmail2(record.email2)
                .withEmail3(record.email3);
    }

    public List<ContactData> getContactList() {
        return convertListContacts(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }
}




