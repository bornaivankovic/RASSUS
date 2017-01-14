package hr.fer.tel.rassus;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Borna Ivankovic on 14.12.2016..
 */

public class MyListAdapter extends BaseExpandableListAdapter {

    private final Context mContext;
    private JSONArray array;

    public MyListAdapter() {
        this.mContext = null;
        this.array = new JSONArray();
    }

    public MyListAdapter(String stringArray, Context context) {
        this.mContext = context;
        this.array = null;
        try {
            this.array = new JSONArray(stringArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getGroupCount() {
        return array.length();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        JSONObject object;
        String title = "";
        try {
            object = (JSONObject) array.get(i);
            title = object.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return title;
    }

    @Override
    public Object getChild(int i, int i1) {
        String str = "";
        JSONObject object = null;
        String desc = "", size = "", team = "", taken = "", mentor = "";
        try {
            object = (JSONObject) array.get(i);
            desc = object.getString("description");
            size = object.getString("size");
            team = object.getString("team");
            if(!object.getString("taken").equals("0")) taken = "Yes";
            else taken = "No";
            mentor = object.getString("mentor");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        str = "Description:\n" + desc + "\n\nMentor:\n" + mentor + "\n\nSize:\n" + size + "\n\nTaken:\n" + taken + "\n\nTeam:\n" + team;
        return str;
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText);
        String role=((GlobalVariables)mContext).getRole();

        Button settings = (Button) convertView.findViewById(R.id.settings_button);
        if(role.equals("guest")){
            settings.setVisibility(View.GONE);
        }
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String objectString = null;
                try {
                    objectString = array.get(groupPosition).toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(mContext, ThemeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("object", objectString);
                mContext.startActivity(intent);

            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}

