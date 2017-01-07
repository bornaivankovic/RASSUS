package hr.fer.tel.rassus;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class PostActivity extends AppCompatActivity {

    private JSONObject object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        object = new JSONObject();
    }

    public void send(View view) throws JSONException {
        EditText postTitle = (EditText) findViewById(R.id.post_title);
        EditText postDescription = (EditText) findViewById(R.id.post_description);
        EditText postSize = (EditText) findViewById(R.id.post_size);
        EditText postTaken = (EditText) findViewById(R.id.post_taken);
        EditText postMentor = (EditText) findViewById(R.id.post_mentor);

        try {
            object.put("title", postTitle.getText());
            object.put("description", postDescription.getText());
            object.put("size", postSize.getText());
            object.put("taken", postTaken.getText());
            object.put("mentor", postMentor.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String host=sharedPref.getString("hostname","");
        String port=sharedPref.getString("port","");
        String hostname = host+":"+port;

        String email = ((GlobalVariables) this.getApplication()).getEmail();
        String password = ((GlobalVariables) this.getApplication()).getPassword();
        PostAction postAction= (PostAction) new PostAction(new PostAction.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                finish();
            }
        }).execute("http://"+hostname+"/api/v0.2/projects/", email, password, object.toString());
    }
}
