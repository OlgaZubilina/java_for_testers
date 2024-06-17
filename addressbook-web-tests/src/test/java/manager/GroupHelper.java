package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class GroupHelper {
    private final ApplicationManager manager;

    public GroupHelper(ApplicationManager manager) {
        this.manager = manager;
    }

    public void openGroupsPage() {
        if (!manager.isElementPresent(By.name("new"))) {//переход в раздел группы при отсутствии кнопки "new group"
            manager.driver.findElement(By.linkText("groups")).click();
        }
    }

    public boolean isGroupPresent() {
        openGroupsPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void deleteGroup() {
        openGroupsPage();
        selectGroup();
        manager.driver.findElement(By.name("delete")).click();
        returnToGroupPage();
    }

    public void createGroup(GroupData group) {
        openGroupsPage();
        manager.driver.findElement(By.name("new")).click();
        fillGroupForm(group);
        manager.driver.findElement(By.name("submit")).click();
        returnToGroupPage();
    }

    public void updateGroup(GroupData updateGroup) {
        openGroupsPage();
        selectGroup();
        initGroupUpdate();
        fillGroupForm(updateGroup);
        submitGroupUpdate();
        returnToGroupPage();

    }

    private void returnToGroupPage() {
        manager.driver.findElement(By.linkText("group page")).click();
    }

    private void submitGroupUpdate() {
        manager.driver.findElement(By.name("update")).click();
    }

    private void fillGroupForm(GroupData group) {
        manager.driver.findElement(By.name("group_name")).click();
        manager.driver.findElement(By.name("group_name")).sendKeys(group.name());
        manager.driver.findElement(By.name("group_header")).click();
        manager.driver.findElement(By.name("group_header")).sendKeys(group.header());
        manager.driver.findElement(By.name("group_footer")).click();
        manager.driver.findElement(By.name("group_footer")).sendKeys(group.footer());
    }

    private void initGroupUpdate() {
        manager.driver.findElement(By.name("edit")).click();
    }

    private void selectGroup() {
        manager.driver.findElement(By.name("selected[]")).click();
    }
}

