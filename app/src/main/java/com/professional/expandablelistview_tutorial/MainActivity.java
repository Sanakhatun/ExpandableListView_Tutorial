package com.professional.expandablelistview_tutorial;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity {

    private HashMap<String,ParentItem> subjects;
    private ArrayList<ParentItem> parentItemsList;
    private ArrayList<ChildItem> childItemsList;
    private CustomAdapter customAdapter;
    private ExpandableListView elvSimple;
    private ImageView iv_groupIndicator;
    private int previousGroup = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();
        loadData();
        collapseAll();
    }
    public void initializeView()
    {
        subjects = new HashMap<>();
        parentItemsList = new ArrayList<>();
        childItemsList = new ArrayList<>();
        customAdapter = new CustomAdapter(MainActivity.this,parentItemsList);
        elvSimple = findViewById(R.id.elvSimple);
        elvSimple.setAdapter(customAdapter);
        iv_groupIndicator = findViewById(R.id.iv_groupIndicator);

        elvSimple.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (elvSimple.isGroupExpanded(groupPosition)) {
                    elvSimple.collapseGroup(groupPosition);
                    previousGroup=-1;
                } else {
                    elvSimple.expandGroup(groupPosition);
                    if(previousGroup!=-1){
                        elvSimple.collapseGroup(previousGroup);
                    }
                    previousGroup=groupPosition;
                }

                return true;
            }
        });

        elvSimple.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                /* Auto Scrolling */
                elvSimple.setSelectedGroup(groupPosition);
            }
        });
    }

    private void loadData()
    {
        addItem("Android","ListView");
        addItem("Android","ExpandableListView");
        addItem("Android","GridView");

        addItem("Java","Polymorphism");
        addItem("Java","Collections");
    }

    private int addItem(String parentItemName, String childItemName)
    {
        int groupPosition = 0;

        //check the hash map if the group already exists
        ParentItem parentItemObj = subjects.get(parentItemName);

        //add the group if doesn't exists
        if(parentItemObj == null)
        {
            parentItemObj = new ParentItem();
            parentItemObj.setName(parentItemName);
            subjects.put(parentItemName,parentItemObj);

            parentItemsList.add(parentItemObj);

        }

        //get the children for the group
         childItemsList = parentItemObj.getChildList();

        //size of the children list
        int listSize = childItemsList.size();

        //add to the counter
        listSize++;

        //create a new child and add that to the group

        ChildItem childItemObj = new ChildItem();
        childItemObj.setName(childItemName);
        childItemsList.add(childItemObj);
        parentItemObj.setChildList(childItemsList);

        //find the group position inside the list
        groupPosition = parentItemsList.indexOf(parentItemObj);
        return groupPosition;
    }

    //method to expand all groups
    private void expandAll()
    {
        int count = customAdapter.getGroupCount();
        for(int i = 0; i < count; i++)
        {
            elvSimple.expandGroup(i);
        }
    }

    //method to collapse all groups
    private void collapseAll()
    {
        int count = customAdapter.getGroupCount();
        for(int i = 0; i < count; i++)
        {
            elvSimple.collapseGroup(i);
        }
    }
}
