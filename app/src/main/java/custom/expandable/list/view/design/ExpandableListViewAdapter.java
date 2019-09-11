package custom.expandable.list.view.design;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter
{
    private ArrayList<NullPositionModel> nullPositionModelArrayList;
    private ArrayList<GroupModel> groupModelArrayList;
    private LayoutInflater layoutInflater;

    public ExpandableListViewAdapter(Context context, ArrayList<GroupModel> arrayList, ArrayList<NullPositionModel> nullPositionModelArrayList)
    {
        this.groupModelArrayList = arrayList;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.nullPositionModelArrayList=nullPositionModelArrayList;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        ArrayList<ChildModel> childModelArrayList = groupModelArrayList.get(groupPosition).getChildArrayList();
        return childModelArrayList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {
        convertView=null;
        ChildViewHolder childViewHolder;
        ChildModel childModelPosition = (ChildModel) getChild(groupPosition, childPosition);

        if (childModelPosition.getChildName().trim().equals("null"))
        {
            convertView = layoutInflater.inflate(R.layout.child_item_empty, parent, false);
        }
        else
        {
            convertView = layoutInflater.inflate(R.layout.child_item, parent, false);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);

            ((ChildViewHolder) childViewHolder).setData(childModelPosition);
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        ArrayList<ChildModel> childModelArrayList = groupModelArrayList.get(groupPosition).getChildArrayList();
        return childModelArrayList.size();
    }

    @Override
    public Object getGroup(int groupPosition)
    {
        return groupModelArrayList.get(groupPosition);
    }

    @Override
    public int getGroupCount()
    {
        return groupModelArrayList.size();
    }

    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {
        boolean empty_layout=false;
        for (int i = 0; i < nullPositionModelArrayList.size(); i++)
        {
            if(groupPosition==nullPositionModelArrayList.get(i).getNullPosition())
            {
                empty_layout=true;
            }
        }

        convertView=null;
        GroupViewHolder groupViewHolder;
        GroupModel groupModelPosition = (GroupModel) getGroup(groupPosition);

        System.out.println(groupPosition+" Number of Child In "+groupModelPosition.getGroupName()+" "+getChildrenCount(groupPosition));
        if (empty_layout)
        {
            convertView = layoutInflater.inflate(R.layout.group_item_empty, parent, false);
            TextView groupTextView =convertView.findViewById(R.id.group_text_view);
            groupTextView.setTypeface(null, Typeface.BOLD);
           /* if (isExpanded)
            {
                groupTextView.setTypeface(null, Typeface.BOLD);
            }
            else
            {
                groupTextView.setTypeface(null, Typeface.NORMAL);
            }*/
            groupTextView.setText(groupModelPosition.getGroupName());
        }
        else
        {
            convertView = layoutInflater.inflate(R.layout.group_item, parent, false);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);

            ((GroupViewHolder) groupViewHolder).setData(groupModelPosition,isExpanded);
        }
        return convertView;
    }

    @Override
    public boolean hasStableIds()
    {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }

    private class GroupViewHolder
    {
        TextView groupTextView;
        ImageView groupIndicator;

        public GroupViewHolder(View item)
        {
            groupTextView = item.findViewById(R.id.group_text_view);
            groupIndicator = item.findViewById(R.id.group_image_view);
        }

        public void setData(GroupModel itemPosition,boolean isExpanded)
        {
            groupTextView.setText(itemPosition.getGroupName());
            if (isExpanded)
            {
                groupTextView.setTypeface(null, Typeface.BOLD);
                groupIndicator.setImageResource(R.drawable.ic_keyboard_arrow_up_24dp);
            }
            else
            {
                groupTextView.setTypeface(null, Typeface.NORMAL);
                groupIndicator.setImageResource(R.drawable.ic_keyboard_arrow_down_24dp);
            }
        }
    }

    private class ChildViewHolder
    {
        TextView childTextView;

        public ChildViewHolder(View item)
        {
            childTextView = (TextView) item.findViewById(R.id.child_text_view);
        }

        public void setData(ChildModel itemPosition)
        {
            childTextView.setText(itemPosition.getChildName());
        }
    }
}