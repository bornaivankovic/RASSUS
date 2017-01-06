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
    private HashMap<String, String> theme = new HashMap<String, String>();
    private JSONObject object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        this.theme = (HashMap<String, String>) getIntent().getSerializableExtra("map");
        try {
            object = new JSONObject(getIntent().getStringExtra("object"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String host=sharedPref.getString("hostname","");
        String port=sharedPref.getString("port","");
        String hostname = host+":"+port;
        String email = ((GlobalVariables) this.getApplication()).getEmail();
        String password = ((GlobalVariables) this.getApplication()).getPassword();
        DeleteAction deleteAction= (DeleteAction) new DeleteAction(new DeleteAction.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                finish();
            }
        }).execute("http://" + hostname + "/api/v0.2/projects/" + theme.get("id"), email, password);
    }
}