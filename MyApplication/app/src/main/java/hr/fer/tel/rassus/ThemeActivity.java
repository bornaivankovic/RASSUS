package hr.fer.tel.rassus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.HashMap;

public class ThemeActivity extends AppCompatActivity {
    private HashMap<String, String> theme = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        this.theme = (HashMap<String, String>) getIntent().getSerializableExtra("map");

        TextView textView = (TextView) findViewById(R.id.theme_title);
        TextView textView1 = (TextView) findViewById(R.id.theme_description);
        textView.setText(theme.get("title"));
        String desc = "";
        for(String str : theme.keySet()) {
            desc += str + ":\n" + theme.get(str) + "\n\n";
        }
        textView1.setText(desc);
    }

    public void edit(View view) {

    }

    public void delete(View view) {

    }
}