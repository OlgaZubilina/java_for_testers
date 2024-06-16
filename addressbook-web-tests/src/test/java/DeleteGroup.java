import model.GroupData;
import org.junit.jupiter.api.Test;


public class DeleteGroup extends TestBase
        {


    @Test
    public void canRemoveGroup() {
        openGroupsPage();
        if (!isGroupPresent()) {//создание новой группы при отсутствии созданных групп
            createGroup(new GroupData("name", "header", "footer"));
        }
        deleteGroup();

    }


        }
