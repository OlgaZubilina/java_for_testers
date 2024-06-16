import model.GroupData;
import org.junit.jupiter.api.Test;

public class CreateGroup extends TestBase {


    @Test
    public void canCreateGroup() {
        openGroupsPage();
        createGroup(new GroupData("name", "header", "footer"));

    }

    @Test
    public void canCreateGroupWithNameOnly() {
        openGroupsPage();
        var emptyGroup = new GroupData();
        var groupWithName = emptyGroup.withName("some name");
        createGroup(groupWithName);

    }
}
