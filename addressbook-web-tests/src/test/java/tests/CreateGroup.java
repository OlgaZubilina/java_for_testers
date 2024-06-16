package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class CreateGroup extends TestBase {


    @Test
    public void canCreateGroup() {
        app.groups().createGroup(new GroupData("name", "header", "footer"));

    }

    @Test
    public void canCreateEmptyGroup() {
        app.groups().createGroup(new GroupData());

    }
    @Test
    public void canCreateGroupWithNameOnly() {
        app.groups().createGroup(new GroupData().withName("some name"));

    }
}
