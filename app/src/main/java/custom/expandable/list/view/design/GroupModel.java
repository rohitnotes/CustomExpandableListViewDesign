package custom.expandable.list.view.design;

import java.util.ArrayList;

public class GroupModel {

    private String groupName;
    private ArrayList<ChildModel> childArrayList = new ArrayList<ChildModel>();

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ArrayList<ChildModel> getChildArrayList() {
        return childArrayList;
    }

    public void setChildArrayList(ArrayList<ChildModel> childArrayList) {
        this.childArrayList = childArrayList;
    }
}
