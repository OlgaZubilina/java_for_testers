package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class GroupHelper extends HelperBase{

    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createGroup(GroupData group) {
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
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

    public void deleteGroup() {
        openGroupsPage();
        selectGroup();
        removeSelectedGroup();
        returnToGroupPage();
    }


    public void openGroupsPage() {
        if (!manager.isElementPresent(By.name("new"))) {//переход в раздел группы при отсутствии кнопки "new group"
            click(By.linkText("groups"));
        }
    }

    public boolean isGroupPresent() {
        openGroupsPage();
        return manager.isElementPresent(By.name("selected[]"));
    }


    private void removeSelectedGroup() {
        click(By.name("delete"));
    }


    private void initGroupCreation() {
        click(By.name("new"));
    }

    private void submitGroupCreation() {
        click(By.name("submit"));
    }


    private void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    private void submitGroupUpdate() {
        click(By.name("update"));
    }

    private void fillGroupForm(GroupData group) {
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());
    }


    private void initGroupUpdate() {
        click(By.name("edit"));
    }

    private void selectGroup() {
        click(By.name("selected[]"));
    }

}
