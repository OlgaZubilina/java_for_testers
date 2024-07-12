package ru.stqa.adressbook.manager;

import ru.stqa.adressbook.model.ContactData;
import ru.stqa.adressbook.model.GroupData;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JdbcHelper extends HelperBase {
    public JdbcHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<GroupData> getGroupList() {
        var groups = new ArrayList<GroupData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list")) {

            while (result.next()) {

                groups.add(new GroupData()
                        .withId(result.getString("group_id"))
                        .withName(result.getString("group_name"))
                        .withHeader(result.getString("group_header"))
                        .withFooter(result.getString("group_footer")));

            }

            return groups;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        public List<ContactData> getContactList() {
            var contacts = new ArrayList<ContactData>();
            try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
                 var statement = conn.createStatement();
                 var result = statement.executeQuery("SELECT id, firstname, lastname, address FROM addressbook")) {

                while (result.next()) {

                    contacts.add(new ContactData()
                            .withId(result.getString("id"))
                            .withFirstname(result.getString("firstname"))
                            .withLastname(result.getString("lastname"))
                            .withAdress(result.getString("address")));

                }
                return contacts;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

  public List <ContactData> getListContactsInGroup() { var contacts = new ArrayList<ContactData>();
      try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
           var statement = conn.createStatement();
           var result = statement.executeQuery("SELECT ab.id, ab.firstname, ab.lastname FROM addressbook ab INNER JOIN address_in_groups ag ON ab.id = ag.id")) {

          while (result.next()) {

              contacts.add(new ContactData()
                      .withId(result.getString("id"))
                      .withFirstname(result.getString("firstname"))
                      .withLastname(result.getString("lastname")));

          }
          return contacts;
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
}

    public List<ContactData> getContactListWithoutGroup() {var contacts = new ArrayList<ContactData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT ab.id, ab.firstname, ab.lastname FROM addressbook ab LEFT JOIN address_in_groups ag ON ab.id = ag.id WHERE ag.id IS NULL")) {

            while (result.next()) {

                contacts.add(new ContactData()
                        .withId(result.getString("id"))
                        .withFirstname(result.getString("firstname"))
                        .withLastname(result.getString("lastname")));

            }
            return contacts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }    }

    /*public List<ContactData> getContactListWithoutGroupTest(GroupData group) {var contacts = new ArrayList<ContactData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             String list = ("SELECT ab.id, ab.firstname, ab.lastname FROM addressbook ab LEFT JOIN address_in_groups ag ON ag.group_id!="+ group.id().toString());
             var result = statement.executeQuery(list)) {

            while (result.next()) {

                contacts.add(new ContactData()
                        .withId(result.getString("id"))
                        .withFirstname(result.getString("firstname"))
                        .withLastname(result.getString("lastname"))
                        .withAdress(result.getString("address")));

            }
            return contacts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }    }*/
}
