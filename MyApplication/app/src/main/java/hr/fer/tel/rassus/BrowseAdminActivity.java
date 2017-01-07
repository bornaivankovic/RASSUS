package hr.fer.tel.rassus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

public class BrowseAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_admin);
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

    public void post(View view) {
        Intent intent = new Intent(BrowseAdminActivity.this, PostActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
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
}
