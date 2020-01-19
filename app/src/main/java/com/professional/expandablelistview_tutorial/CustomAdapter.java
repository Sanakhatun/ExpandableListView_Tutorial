package com.professional.expandablelistview_tutorial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sana on 26/7/18.
 */

/*
* Note:
* 1. Implementation of this interface will provide the data for the children
* and also initiate the views for the children and groups.
*
* 2. For customization of list we need to implement ExpandableListAdapter in our custom adapter
*/


public class CustomAdapter extends BaseExpandableListAdapter{
    private Context context;
    private ArrayList<ParentItem> parentItemArrayList;
    private ArrayList<ChildItem> childItemArrayList;
    private TextView tv_parentItem, tv_childItem;
    private ImageView iv_groupIndicator;

    public CustomAdapter(Context context, ArrayList<ParentItem> parentItemArrayList)
    {
        this.context = context;
        this.parentItemArrayList = parentItemArrayList;
    }

    @Override
    public int getGroupCount() {
        return parentItemArrayList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        childItemArrayList = parentItemArrayList.get(i).getChildList();
        return childItemArrayList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentItemArrayList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        childItemArrayList = parentItemArrayList.get(groupPosition).getChildList();
        return childItemArrayList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        ParentItem parentItemInfo = (ParentItem) getGroup(groupPosition);
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.group_items,null);
        }
        tv_parentItem = (TextView) view.findViewById(R.id.tv_parentItem);
        iv_groupIndicator = (ImageView) view.findViewById(R.id.iv_groupIndicator);
        tv_parentItem.setText(parentItemInfo.getName());

        /*if(isExpanded){
            iv_groupIndicator.setImageResource(R.drawable.clear);
        }else{
            iv_groupIndicator.setImageResource(R.drawable.add);
        }*/

        iv_groupIndicator.setSelected(isExpanded);

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {
        ChildItem childItemInfo = (ChildItem) getChild(groupPosition,childPosition);
        if(view == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.child_items,null);
        }

        tv_childItem = (TextView)view.findViewById(R.id.tv_childItem);
        tv_childItem.setText(childItemInfo.getName());

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
