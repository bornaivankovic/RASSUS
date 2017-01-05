package hr.fer.tel.rassus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ThemeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        String childTitle = getIntent().getStringExtra("title");
        String child = getIntent().getStringExtra("child");

        TextView textView = (TextView) findViewById(R.id.theme_title);
        TextView textView1 = (TextView) findViewById(R.id.theme_description);
        textView.setText(childTitle);
        textView1.setText(child);
    }
}