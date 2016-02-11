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
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile1);
    }

    void nextStep(View view){
        EditText name = (EditText) view.findViewById(R.id.name);
        EditText teamNum= (EditText) view.findViewById(R.id.teamNumber);
        EditText subTeam = (EditText) view.findViewById(R.id.subteam);

        Intent createProfile = new Intent(this,CreateProfileActivity2.class);
        createProfile.putExtra("name", name.getText());
        createProfile.putExtra("teamNum",teamNum.getText());
        createProfile.putExtra("subTeam",subTeam.getText());
        this.startActivity(createProfile);
    }


}
