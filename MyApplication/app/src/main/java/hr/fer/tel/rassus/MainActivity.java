package hr.fer.tel.rassus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void browse(View view){
        Intent intent = new Intent(MainActivity.this,BrowseActivity.class);
        startActivity(intent);
    }

    public void login(View view){
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    public void settings(View view){
        Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
        startActivity(intent);
    }

    public void adminbrowse(View view) {
        Intent intent = new Intent(MainActivity.this, BrowseAdminActivity.class);
        startActivity(intent);
    }
}
