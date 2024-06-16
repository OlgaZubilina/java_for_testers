package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;


public class DeleteGroup extends TestBase
        {


    @Test
    public void canRemoveGroup() {
        if (!app.groups().isGroupPresent()) {//создание новой группы при отсутствии созданных групп
            app.groups().createGroup(new GroupData("name", "header", "footer"));
        }
        app.groups().deleteGroup();

    }


        }
