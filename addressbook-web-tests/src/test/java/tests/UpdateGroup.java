package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class UpdateGroup extends TestBase{
    @Test
    void canUpdateGroup(){
        if (app.groups().getCount()==0){
            app.groups().createGroup(new GroupData("group name","group header","group footer"));
        }
        app.groups().updateGroup(new GroupData().withName("update name"));
    }
}
