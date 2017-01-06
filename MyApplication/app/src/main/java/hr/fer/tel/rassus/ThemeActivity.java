package hr.fer.tel.rassus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
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
        String host = ((GlobalVariables) this.getApplication()).getHost();
        String email = ((GlobalVariables) this.getApplication()).getEmail();
        String password = ((GlobalVariables) this.getApplication()).getPassword();
        DeleteAction deleteAction= (DeleteAction) new DeleteAction(new DeleteAction.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                finish();
            }
        }).execute("http://" + host + "/api/v0.2/projects/" + theme.get("id"), email, password);
    }
}