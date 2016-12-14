package hr.fer.tel.rassus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetAction getAction= (GetAction) new GetAction(new GetAction.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                JsonParser parser=new JsonParser(output);
                ExpandableListView listView=(ExpandableListView) findViewById(R.id.list_view);
                listView.setAdapter(new MyListAdapter(parser.parse(),getApplicationContext()));

            }
        }).execute("https://jsonplaceholder.typicode.com/posts/1/comments");
    }




}
