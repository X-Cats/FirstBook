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

    EditText name;
    EditText teamNum;
    EditText subTeam;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile1);

        name = (EditText) findViewById(R.id.nameCreateProf1);
        teamNum= (EditText) findViewById(R.id.teamNumberCreatProf1);
        subTeam = (EditText) findViewById(R.id.subteamCreateProf1);
    }

    public void nextStep(View view){

        Intent createProfile = new Intent(this,CreateProfileActivity2.class);
        createProfile.putExtra("name", name.getText().toString());
        createProfile.putExtra("teamNum",teamNum.getText().toString());
        createProfile.putExtra("subTeam",subTeam.getText().toString());
        this.startActivity(createProfile);
    }


}
