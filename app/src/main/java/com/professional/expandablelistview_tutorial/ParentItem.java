package com.professional.expandablelistview_tutorial;

import java.util.ArrayList;

/**
 * Created by sana on 26/7/18.
 */

public class ParentItem {
    private String name;
    private ArrayList<ChildItem> childList = new ArrayList<ChildItem>();

    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }

    public void setChildList(ArrayList<ChildItem> childList)
    {
        this.childList = childList;
    }
    public ArrayList<ChildItem> getChildList()
    {
        return childList;
    }
}
