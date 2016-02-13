package com.xcats.firstbook;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xcats.firstbook.Database.DirectoryProvider;

import org.w3c.dom.Text;

/**
 * Created by aidan on 2/6/16.
 */
public class CreateProfileActivity2 extends Activity {
    TextView name;
    TextView teamNum;
    TextView subTeam;
    EditText bio;
    EditText age;
    RadioGroup rg;
    RadioButton student;
    RadioButton mentor;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile2);

        name = (TextView) findViewById(R.id.profileName);
        teamNum= (TextView) findViewById(R.id.profileTeam);
        subTeam = (TextView) findViewById(R.id.profilesubTeam);
        bio = (EditText) findViewById(R.id.bio);
        age = (EditText) findViewById(R.id.age);
        rg = (RadioGroup)findViewById(R.id.typeGroup);

        Bundle b = getIntent().getExtras();
        name.setText((String) b.get("name"));
        teamNum.setText((String) b.get("teamNum"));
        subTeam.setText((String) b.get("subTeam"));
    }

    public void addMember(View view) {
        //try to add new person
        directoryWrite(view);

        //then bring them back to main page
        Intent mainPage = new Intent(this,LogInActivity.class);
        this.startActivity(mainPage);
        Toast.makeText(this,"Directory updated",Toast.LENGTH_LONG).show();
    }

    public void directoryWrite(View v){
        // Add a new member record
        ContentValues values = new ContentValues();

        values.put(DirectoryProvider.NAME, name.getText().toString().trim());
        values.put(DirectoryProvider.TEAMNUMBER, teamNum.getText().toString().trim());
        values.put(DirectoryProvider.SUBTEAM, subTeam.getText().toString().trim());

        values.put(DirectoryProvider.BIO, bio.getText().toString().trim());
        values.put(DirectoryProvider.AGE, age.getText().toString().trim());

        int selection = rg.getCheckedRadioButtonId();

        values.put(DirectoryProvider.TYPE, ((RadioButton)findViewById(selection)).getText().toString().trim());

        Uri uri = getContentResolver().insert(
                DirectoryProvider.CONTENT_URI, values);

        Log.i("Firstbook--", "Directory added with member:" + name.getText().toString());

    }
}
