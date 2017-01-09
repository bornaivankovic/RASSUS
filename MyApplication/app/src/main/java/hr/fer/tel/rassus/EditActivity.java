package hr.fer.tel.rassus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class EditActivity extends AppCompatActivity {

    private JSONObject object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        String stringObject = getIntent().getStringExtra("object");

        EditText editTitle = (EditText) findViewById(R.id.edit_title);
        EditText editDescription = (EditText) findViewById(R.id.edit_description);
        EditText editSize = (EditText) findViewById(R.id.edit_size);
        EditText editMentor = (EditText) findViewById(R.id.edit_mentor);
        EditText editTeam = (EditText) findViewById(R.id.edit_team);

        try {
            object = new JSONObject(stringObject);
            editTitle.setText(object.getString("title"), TextView.BufferType.EDITABLE);
            editDescription.setText(object.getString("description"), TextView.BufferType.EDITABLE);
            editSize.setText(object.getString("size"), TextView.BufferType.EDITABLE);
            editMentor.setText(object.getString("mentor"), TextView.BufferType.EDITABLE);
            editTeam.setText(object.getString("team"), TextView.BufferType.EDITABLE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void send(View view) throws JSONException {
        EditText editTitle = (EditText) findViewById(R.id.edit_title);
        EditText editDescription = (EditText) findViewById(R.id.edit_description);
        EditText editSize = (EditText) findViewById(R.id.edit_size);
        EditText editMentor = (EditText) findViewById(R.id.edit_mentor);
        EditText editTeam = (EditText) findViewById(R.id.edit_team);

        try {
            object.put("title", editTitle.getText());
            object.put("description", editDescription.getText());
            object.put("size", editSize.getText());
            object.put("mentor", editMentor.getText());
            String team = editTeam.getText().toString();
            if(!team.equalsIgnoreCase("")) {
                object.put("taken", "1");
                object.put("team", team);
            }
            else {
                object.put("taken", "0");
                object.put("team", team);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String host=sharedPref.getString("hostname","");
        String port=sharedPref.getString("port","");
        String hostname = host+":"+port;

        String email = ((GlobalVariables) this.getApplication()).getEmail();
        String password = ((GlobalVariables) this.getApplication()).getPassword();
        PutAction putAction= (PutAction) new PutAction(new PutAction.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                finish();
            }
        }).execute("http://"+hostname+"/api/v0.2/projects/" + object.getString("id"), email, password, object.toString());
    }
}
