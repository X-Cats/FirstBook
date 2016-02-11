package com.xcats.firstbook;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
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
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile2);

        TextView name = (TextView) findViewById(R.id.profileName);
        TextView teamNum= (TextView) findViewById(R.id.profileTeam);
        TextView subTeam = (TextView) findViewById(R.id.profilesubTeam);

        Bundle b = getIntent().getExtras();
        name.setText((String) b.get("name"));
        teamNum.setText((String) b.get("teamNum"));
        subTeam.setText((String) b.get("subTeam"));
    }

    public void addMember(View view) {
        // Add a new member record
        ContentValues values = new ContentValues();

        values.put(DirectoryProvider.NAME,
                ((EditText)findViewById(R.id.name)).getText().toString());

        values.put(DirectoryProvider.TEAMNUMBER,
                ((EditText)findViewById(R.id.teamNumber)).getText().toString());
        values.put(DirectoryProvider.SUBTEAM,
                ((EditText)findViewById(R.id.subteam)).getText().toString());
        values.put(DirectoryProvider.BIO,
                ((EditText)findViewById(R.id.bio)).getText().toString());
        values.put(DirectoryProvider.AGE,
                ((EditText)findViewById(R.id.age)).getText().toString());

        RadioGroup rg = (RadioGroup)findViewById(R.id.typeGroup);
        int selection = rg.getCheckedRadioButtonId();

        values.put(DirectoryProvider.TYPE,
                ((RadioButton)findViewById(selection)).getText().toString());



        Uri uri = getContentResolver().insert(
                DirectoryProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                "Xcats: " + uri.toString() + " inserted!", Toast.LENGTH_LONG).show();
    }
}
