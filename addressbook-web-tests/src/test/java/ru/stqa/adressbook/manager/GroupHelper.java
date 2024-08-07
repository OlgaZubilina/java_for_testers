package ru.stqa.adressbook.manager;

import org.openqa.selenium.WebElement;
import ru.stqa.adressbook.model.GroupData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void updateGroup(GroupData group, GroupData updateGroup) {
        openGroupsPage();
        selectGroup(group);
        initGroupUpdate();
        fillGroupForm(updateGroup);
        submitGroupUpdate();
        returnToGroupPage();

    }

    public void deleteGroup(GroupData group) {
        openGroupsPage();
        selectGroup(group);
        removeSelectedGroups();
        returnToGroupPage();
    }


    public void openGroupsPage() {
        if (!manager.isElementPresent(By.name("new"))) {//переход в раздел группы при отсутствии кнопки "new group"
            click(By.linkText("groups"));
        }
    }

    private void removeSelectedGroups() {
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

    private void selectGroup(GroupData group) {
        click(By.cssSelector(String.format("input[value='%s']",group.id())));
    }

    public int getCount() {
        openGroupsPage();
       return manager.driver.findElements(By.name("selected[]")).size();

    }

    public void deleteAllGroups() {
        openGroupsPage();
        selectAllGroups();
        removeSelectedGroups();
        returnToGroupPage();
    }

    private void  selectAllGroups(){
        manager.driver.findElements(By.name("selected[]")).forEach(WebElement::click);
    }

    public List<GroupData> getList() {
        openGroupsPage();
        var spans = manager.driver.findElements(By.cssSelector("span.group"));
        return spans.stream()
                .map(span ->{
                    var name = span.getText();
                    var checkbox = span.findElement(By.name("selected[]"));
                    var id = checkbox.getAttribute("value");
                   return new GroupData().withId(id).withName(name);
                }).collect(Collectors.toList());

    }
}

