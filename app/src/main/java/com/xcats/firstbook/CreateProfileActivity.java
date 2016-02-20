package com.xcats.firstbook;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.xcats.firstbook.Database.DirectoryProvider;

/**
 * Created by aidan on 2/6/16.
 */
public class CreateProfileActivity extends Activity {

    EditText nameEdit;
    EditText teamNumEdit;
    EditText subTeamEdit;

    String name;
    String teamNum;
    String subTeam;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile1);

        nameEdit = (EditText) findViewById(R.id.nameCreateProf1);
        teamNumEdit = (EditText) findViewById(R.id.teamNumberCreatProf1);
        subTeamEdit = (EditText) findViewById(R.id.subteamCreateProf1);
    }

    public void nextStep(View view) {

        name = nameEdit.getText().toString();
        teamNum = teamNumEdit.getText().toString();
        subTeam = subTeamEdit.getText().toString();

        if(!name.isEmpty() && !teamNum.isEmpty() && !subTeam.isEmpty()){
            Intent createProfile = new Intent(this, CreateProfileActivity2.class);
            createProfile.putExtra("name", name);
            createProfile.putExtra("teamNum", teamNum);
            createProfile.putExtra("subTeam", subTeam);
            this.startActivity(createProfile);
        }

    }

}
