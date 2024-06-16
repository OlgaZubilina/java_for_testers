package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactsHelper {
    private final ApplicationManager manager;

    public ContactsHelper (ApplicationManager manager){
        this.manager = manager;
    }
    public void openContactsPage() {
        if (!manager.isElementPresent(By.id("MassCB"))) {//переход в раздел контактов при отсутствии чекбокса "выделить все"
            manager.driver.findElement(By.linkText("home")).click();
        }
    }
    public boolean isContactPresent() {
        openContactsPage();
        return manager.isElementPresent(By.name("selected[]"));
    }
    public void deleteContact() {
        openContactsPage();
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.cssSelector(".left:nth-child(8) > input")).click();


    }

    public  void createContact(ContactData contact) {
        openContactCreatePage();
        manager.driver.findElement(By.name("firstname")).click();
        manager.driver.findElement(By.name("firstname")).sendKeys(contact.firstname());
        manager.driver.findElement(By.name("middlename")).click();
        manager.driver.findElement(By.name("middlename")).sendKeys(contact.middlename());
        manager.driver.findElement(By.name("lastname")).click();
        manager.driver.findElement(By.name("lastname")).sendKeys(contact.lastname());
        manager.driver.findElement(By.name("nickname")).click();
        manager.driver.findElement(By.name("nickname")).sendKeys(contact.nickname());
        //driver.findElement(By.name("photo")).click();
        // driver.findElement(By.name("photo")).sendKeys("C:\Users\Work\Desktop\скрины\Screenshot_1.png");
        manager.driver.findElement(By.name("title")).click();
        manager.driver.findElement(By.name("title")).sendKeys(contact.title());
        manager.driver.findElement(By.name("company")).click();
        manager.driver.findElement(By.name("company")).sendKeys(contact.company());
        manager.driver.findElement(By.name("address")).click();
        manager.driver.findElement(By.name("address")).sendKeys(contact.address());
        manager.driver.findElement(By.name("home")).click();
        manager.driver.findElement(By.name("home")).sendKeys(contact.home());
        manager.driver.findElement(By.name("mobile")).click();
        manager.driver.findElement(By.name("mobile")).sendKeys(contact.mobile());
        manager.driver.findElement(By.name("work")).click();
        manager.driver.findElement(By.name("work")).sendKeys(contact.work());
        manager.driver.findElement(By.name("fax")).click();
        manager.driver.findElement(By.name("fax")).sendKeys(contact.fax());
        manager.driver.findElement(By.name("email")).click();
        manager.driver.findElement(By.name("email")).sendKeys(contact.email());
        manager.driver.findElement(By.name("email2")).click();
        manager.driver.findElement(By.name("email2")).sendKeys(contact.email2());
        manager.driver.findElement(By.name("email3")).click();
        manager.driver.findElement(By.name("email3")).sendKeys(contact.email3());
        manager.driver.findElement(By.name("homepage")).click();
        manager.driver.findElement(By.name("homepage")).sendKeys(contact.homepage());
        /*driver.findElement(By.name("bday")).click();
        {
            WebElement dropdown = driver.findElement(By.name("bday"));
            dropdown.findElement(By.xpath("//option[. = '4']")).click();
        }
        // driver.findElement(By.name("bmonth")).click();
        {
            WebElement dropdown = driver.findElement(By.name("bmonth"));
            dropdown.findElement(By.xpath("//option[. = 'February']")).click();
        }
        // driver.findElement(By.name("byear")).click();
        //driver.findElement(By.name("byear")).sendKeys("123");
        //driver.findElement(By.name("aday")).click();
        {
            WebElement dropdown = driver.findElement(By.name("aday"));
            dropdown.findElement(By.xpath("//option[. = '1']")).click();
        }
        //driver.findElement(By.name("amonth")).click();
        {
            WebElement dropdown = driver.findElement(By.name("amonth"));
            dropdown.findElement(By.xpath("//option[. = 'April']")).click();
        }
        //driver.findElement(By.name("ayear")).click();
        driver.findElement(By.name("ayear")).sendKeys("123");*/

        manager.driver.findElement(By.cssSelector("input:nth-child(75)")).click();
    }

    public void openContactCreatePage() {
        if (!manager.isElementPresent(By.name("submit"))) {//переход в раздел контакта при отсутсвии кнопки "enter"
            manager.driver.findElement(By.linkText("add new")).click();
        }
    }
}
