package com.xcats.firstbook;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.xcats.firstbook.Database.DirectoryProvider;

/**
 * Created by aidan on 2/6/16.
 */
public class CreateProfileActivity extends Activity {

    EditText nameEdit;
    EditText teamNumEdit;
    TextView subTeamEdit;

    String name;
    String teamNum;
    String subTeam = "App";

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile1);

        nameEdit = (EditText) findViewById(R.id.nameCreateProf1);
        teamNumEdit = (EditText) findViewById(R.id.teamNumberCreatProf1);
        subTeamEdit = (TextView) findViewById(R.id.subteamCreateProf1);
        spinner = (Spinner)findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.subteam_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subTeam = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void nextStep(View view) {

        name = nameEdit.getText().toString();
        teamNum = teamNumEdit.getText().toString();
        //subTeam = subTeamEdit.getText().toString();

        if(!name.isEmpty() && !teamNum.isEmpty() && !subTeam.isEmpty()){

            Intent createProfile = new Intent(this, CreateProfileActivity2.class);
            createProfile.putExtra("name", name);
            createProfile.putExtra("teamNum", teamNum);
            createProfile.putExtra("subTeam", subTeam);
            this.startActivity(createProfile);
        }

    }

}
