package hr.fer.tel.rassus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class TeamSelectActivity extends AppCompatActivity {
    private ArrayList<EditText> editTexts=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_select);
        int n=Integer.parseInt(getIntent().getStringExtra("number"));
        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.names);
        for(int i=1;i<=n;i++){
            EditText editText=new EditText(this);
            editText.setId(View.generateViewId());
            editText.setMaxLines(1);
            editText.setHint("Ime"+Integer.toString(i));
            editText.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(editText);
            editTexts.add(editText);
        }
    }

    public void done(View view){
        String team="";
        for(EditText e : editTexts){
            team+=e.getText()+", ";
        }
        team=team.substring(0,team.length()-2);
        Intent intent=new Intent();
        intent.putExtra("team",team);
        setResult(RESULT_OK,intent);
        finish();
    }
}
