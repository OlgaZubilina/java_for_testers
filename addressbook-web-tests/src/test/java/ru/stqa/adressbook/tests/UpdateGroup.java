package ru.stqa.adressbook.tests;

import ru.stqa.adressbook.common.CommonFunctions;
import ru.stqa.adressbook.model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;

public class UpdateGroup extends TestBase{
    @Test
    void canUpdateGroup(){
        if (app.groups().getCount()==0){
            app.groups().createGroup(new GroupData("", "group name","group header","group footer"));
        }
        var oldGroups = app.groups().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        var testData = new GroupData().withName(CommonFunctions.randomString(10));
        app.groups().updateGroup(oldGroups.get(index), testData);
        var newGroups = app.groups().getList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index,testData.withId(oldGroups.get(index).id()));

        Assertions.assertEquals(Set.copyOf(newGroups),Set.copyOf(expectedList));

}
@Test
    void canUpdateGroupHbm() {
    if (app.hbm().getGroupList().size()==0){
        app.groups().createGroup(new GroupData("", "group name","group header","group footer"));
    }
    var oldGroups = app.hbm().getGroupList();
    var rnd = new Random();
    var index = rnd.nextInt(oldGroups.size());
    var testData = new GroupData().withName("update name");
    app.groups().updateGroup(oldGroups.get(index), testData);
    var newGroups = app.hbm().getGroupList();
    var expectedList = new ArrayList<>(oldGroups);
    expectedList.set(index,testData.withId(oldGroups.get(index).id()));
    Comparator<GroupData> compareById = (o1, o2) -> {
        return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
    };
    newGroups.sort(compareById);
    expectedList.sort(compareById);
    Assertions.assertEquals(newGroups, expectedList);

}
}

