package hr.fer.tel.rassus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ThemeActivity extends AppCompatActivity {
    private JSONObject object;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        try {
            object = new JSONObject(getIntent().getStringExtra("object"));
            TextView themeTitle = (TextView) findViewById(R.id.theme_title);
            TextView themeDescription = (TextView) findViewById(R.id.theme_description);
            TextView themeMentor = (TextView) findViewById(R.id.theme_mentor);
            TextView themeSize = (TextView) findViewById(R.id.theme_size);
            TextView themeTaken = (TextView) findViewById(R.id.theme_taken);
            TextView themeTeam = (TextView) findViewById(R.id.theme_team);
            themeTitle.setText(object.getString("title"));
            themeDescription.setText(object.getString("description"));
            themeMentor.setText("Mentor: " + object.getString("mentor"));
            themeSize.setText("Size: " + object.getString("size"));
            themeTaken.setText("Taken: " + object.getString("taken"));
            themeTeam.setText("Team: " + object.getString("team"));
            id = object.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void edit(View view) {
        Intent intent = new Intent(ThemeActivity.this, EditActivity.class);
        intent.putExtra("object", this.object.toString());
        startActivity(intent);
    }

    public void delete(View view) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String host=sharedPref.getString("hostname","");
        String port=sharedPref.getString("port","");
        String hostname = host+":"+port;
        String email = ((GlobalVariables) this.getApplication()).getEmail();
        String password = ((GlobalVariables) this.getApplication()).getPassword();
        DeleteAction deleteAction= (DeleteAction) new DeleteAction(new DeleteAction.AsyncResponse() {
            @Override
            public void processFinish(String output) {
//                TextView textView = (TextView) findViewById(R.id.theme_deleted);
//                textView.setText("Project deleted!");
                finish();
            }
        }).execute("http://" + hostname + "/api/v0.2/projects/" + id, email, password);
    }
}