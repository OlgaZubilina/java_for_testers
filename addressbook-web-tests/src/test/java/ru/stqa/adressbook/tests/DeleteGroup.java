package ru.stqa.adressbook.tests;

import ru.stqa.adressbook.model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;


public class DeleteGroup extends TestBase {


    @Test
    public void canRemoveGroup() {

        if (app.groups().getCount() == 0) {//создание новой группы при отсутствии созданных групп
            app.groups().createGroup(new GroupData("", "name", "header", "footer"));
        }
        //int groupCount = app.groups().getCount();
        var oldGroups = app.groups().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().deleteGroup(oldGroups.get(index));
        var newGroups = app.groups().getList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);
    }

    @Test

    public void canRemoveGroupHbm() {
        if (app.groups().getCount() == 0) {//создание новой группы при отсутствии созданных групп
            app.groups().createGroup(new GroupData("", "name", "header", "footer"));
        }
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().deleteGroup(oldGroups.get(index));
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);

    }
    @Test
    void canRemoveAllGroups() {
        if (app.groups().getCount() == 0) {//создание новой группы при отсутствии созданных групп
            app.groups().createGroup(new GroupData("", "name", "header", "footer"));
        }
        app.groups().deleteAllGroups();
        int groupCount = app.groups().getCount();
        Assertions.assertEquals(0, groupCount);
    }

    @Test
    public void canRemoveAllGroupsHbm() {
        if (app.groups().getCount() == 0) {//создание новой группы при отсутствии созданных групп
            app.groups().createGroup(new GroupData("", "name", "header", "footer"));
        }
        app.groups().deleteAllGroups();
        var groupCount = app.hbm().getGroupList().size();
        Assertions.assertEquals(0, groupCount);

    }
}