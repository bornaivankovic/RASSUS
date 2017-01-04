package hr.fer.tel.rassus;

import android.os.Bundle;
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
        String hostname=getIntent().getStringExtra("hostname");


        GetAction getAction= (GetAction) new GetAction(new GetAction.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                JsonParser parser=new JsonParser(output);
                ArrayList<String> titles = new ArrayList<String>();
                ArrayList<String> child = new ArrayList<String>();
                for(HashMap<String, String> map : parser.parse()) {
                    titles.add(map.get("title"));
                    String tmp = "";
                    for(String txt : map.keySet()) {
                        tmp = tmp + txt + ":\n" + map.get(txt) + "\n\n";
                    }
                    child.add(tmp);
                }
                ExpandableListView listView=(ExpandableListView) findViewById(R.id.list_view);
                listView.setAdapter(new MyListAdapter(titles, child, getApplicationContext()));

            }
        }).execute("http://"+hostname+"/api/v0.2/projects");
    }

}
