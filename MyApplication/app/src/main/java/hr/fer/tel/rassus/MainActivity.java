package hr.fer.tel.rassus;

import android.content.Intent;
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
    }

    public void browse(View view){
        Intent intent = new Intent(MainActivity.this,BrowseActivity.class);
        EditText t1=(EditText) findViewById(R.id.hostIP);
        EditText t2=(EditText) findViewById(R.id.hostPort);
        String hostname=t1.getText()+":"+t2.getText();
        intent.putExtra("hostname",hostname);
        startActivity(intent);
    }

    public void login(View view){
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
    }




}
