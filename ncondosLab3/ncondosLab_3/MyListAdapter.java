package com.example.nick.ncondoslab3;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class MyListAdapter extends BaseExpandableListAdapter implements View.OnClickListener
{

    private Activity activity;
    private ArrayList<Manufacturer> manufacturers;

    public MyListAdapter(Activity act, ArrayList<Manufacturer> manufacturers)
    {
        this.activity = act;
        this.manufacturers = manufacturers;
    }

    @Override
    public int getGroupCount() {
        return manufacturers.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return manufacturers.get(groupPosition).numberOfModels();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return manufacturers.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return manufacturers.get(groupPosition).getModel(childPosition);
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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {

            convertView = activity.getLayoutInflater().inflate(R.layout.group_layout, null);
        }
        TextView view = convertView.findViewById(R.id.group);
        view.setText(manufacturers.get(groupPosition).getName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
           convertView = activity.getLayoutInflater().inflate(R.layout.child_layout, null);
        }

        TextView txt = convertView.findViewById(R.id.childText);
        txt.setText(manufacturers.get(groupPosition).getModel(childPosition));

        ImageView view = convertView.findViewById(R.id.childImage);
        view.setTag(R.id.group_num, groupPosition);
        view.setTag(R.id.posn_num, childPosition);
        view.setOnClickListener(this);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void onClick(View v) {
        View view = activity.findViewById(R.id.linearView);

        final int group = (int) v.getTag(R.id.group_num);
        final int child = (int) v.getTag(R.id.posn_num);

        Snackbar snackbar = Snackbar.make(view, "Do you want to delete?", Snackbar.LENGTH_LONG)
                .setAction("Delete", new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        manufacturers.get(group).deleteModel(child);
                        notifyDataSetChanged();
                    }
                });
        snackbar.show();



    }

}
