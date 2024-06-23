package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class DeleteGroup extends TestBase {


    @Test
    public void canRemoveGroup() {

        if (app.groups().getCount() == 0) {//создание новой группы при отсутствии созданных групп
            app.groups().createGroup(new GroupData("name", "header", "footer"));
        }
        int groupCount = app.groups().getCount();
        app.groups().deleteGroup();
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount - 1, newGroupCount);
    }

    @Test
    void canRemoveAllGroups() {
        if (app.groups().getCount() == 0) {//создание новой группы при отсутствии созданных групп
            app.groups().createGroup(new GroupData("name", "header", "footer"));
        }
        app.groups().deleteAllGroups();
        int groupCount = app.groups().getCount();
        Assertions.assertEquals(0, groupCount);
    }
}