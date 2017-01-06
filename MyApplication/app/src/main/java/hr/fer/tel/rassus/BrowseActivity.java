package hr.fer.tel.rassus;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BrowseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String host=sharedPref.getString("hostname","");
        String port=sharedPref.getString("port","");
        String hostname = host+":"+port;


        GetAction getAction= (GetAction) new GetAction(new GetAction.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                JsonParser parser=new JsonParser(output);
                ExpandableListView listView=(ExpandableListView) findViewById(R.id.list_view);
                listView.setAdapter(new MyListAdapter(parser, getApplicationContext()));

            }
        }).execute("http://"+hostname+"/api/v0.2/projects");
    }

    @Override
    protected void onResume() {
        super.onResume();
        String hostname = ((GlobalVariables) this.getApplication()).getHost();
        GetAction getAction= (GetAction) new GetAction(new GetAction.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                JsonParser parser=new JsonParser(output);
                ExpandableListView listView=(ExpandableListView) findViewById(R.id.list_view);
                listView.setAdapter(new MyListAdapter(parser, getApplicationContext()));

            }
        }).execute("http://"+hostname+"/api/v0.2/projects");
    }
}
