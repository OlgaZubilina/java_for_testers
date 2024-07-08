package ru.stqa.adressbook.manager;

import org.openqa.selenium.support.ui.Select;
import ru.stqa.adressbook.model.ContactData;
import org.openqa.selenium.By;
import ru.stqa.adressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactsHelper extends HelperBase {


    public ContactsHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        openContactCreatePage();
        fillContactForm(contact);
        submitCreateContact();
    }

    public void deleteContact(ContactData contact) {
        openContactsPage();
        selectContact(contact);
        submitDeleteContact();


    }

    public void updateContact(ContactData contact, ContactData updateContact) {
        openContactsPage();
        openContact(contact);
        fillContactForm(updateContact);
        submitUpdateContact();


    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
        ;
    }

    private void submitUpdateContact() {
        click(By.name("update"));
    }

    private void openContact(ContactData contact) {
        click(By.xpath(String.format("//td/a[contains(@href, 'edit.php?id=%s')]", contact.id())));
    }


    private void submitDeleteContact() {
        click(By.cssSelector(".left:nth-child(8) > input"));
    }


    public void openContactsPage() {
        if (!manager.isElementPresent(By.id("MassCB"))) {//переход в раздел контактов при отсутствии чекбокса "выделить все"
            manager.driver.findElement(By.linkText("home")).click();
        }
    }

    public boolean isContactPresent() {
        openContactsPage();
        return manager.isElementPresent(By.name("searchform"));
    }


    private void submitCreateContact() {
        click(By.cssSelector("input:nth-child(75)"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        //type(By.name("middlename"), contact.middlename());
        type(By.name("lastname"), contact.lastname());
        // type(By.name("nickname"), contact.nickname());
        attach(By.name("photo"), contact.photo());
        // type(By.name("title"), contact.title());
        //type(By.name("company"), contact.company());
        type(By.name("address"), contact.address());
        //type(By.name("home"), contact.home());
        //type(By.name("mobile"), contact.mobile());
        //type(By.name("work"), contact.work());
        //type(By.name("fax"), contact.fax());
        //type(By.name("email"), contact.email());
        //type(By.name("email2"), contact.email2());
        // type(By.name("email3"), contact.email3());
        //type(By.name("homepage"), contact.homepage());

    }

    public void openContactCreatePage() {
        if (!manager.isElementPresent(By.name("submit"))) {//переход в раздел контакта при отсутсвии кнопки "enter"
            click(By.linkText("add new"));

        }
    }

    public int getCount() {
        openContactsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getList() {
        openContactsPage();
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.name("entry"));
        for (var tr : trs) {
            var tds = tr.findElements(By.cssSelector("td"));
            for (var td : tds) {
                var check = td.findElement(By.name("selected[]"));
                var id = check.getAttribute("value");
                var lastname = tds.get(1).getText();
                var firstname = tds.get(2).getText();
                contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
                break;
            }


        }
        return contacts;
    }

    public void addToGroup(ContactData contact, GroupData group) {
        openContactsPage();
        selectContact(contact);
        selectGroup(group);
        submitAdding();
    }

    private void submitAdding() {
        click(By.name("add"));
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());

    }
}

