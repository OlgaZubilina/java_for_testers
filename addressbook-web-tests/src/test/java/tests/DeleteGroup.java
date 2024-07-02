package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class DeleteGroup extends TestBase {


    @Test
    public void canRemoveGroup() {

        if (app.groups().getCount() == 0) {//создание новой группы при отсутствии созданных групп
            app.groups().createGroup(new GroupData("", "name", "header", "footer"));
        }
        int groupCount = app.groups().getCount();
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
    void canRemoveAllGroups() {
        if (app.groups().getCount() == 0) {//создание новой группы при отсутствии созданных групп
            app.groups().createGroup(new GroupData("", "name", "header", "footer"));
        }
        app.groups().deleteAllGroups();
        int groupCount = app.groups().getCount();
        Assertions.assertEquals(0, groupCount);
    }
}