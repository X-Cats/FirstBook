package com.xcats.firstbook;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xcats.firstbook.Database.DirectoryProvider;

/**
 * Created by aidan on 2/6/16.
 */
public class ViewProfileActivity extends Activity{
    TextView profileName;
    TextView profileTeamNumber;
    TextView profileSubTeam;
    TextView profileAge;
    TextView profileType;
    TextView profileBio;
    String directoryName;
    String directoryTeamNumber;
    String directorySubTeam;
    String directoryAge;
    String directoryType;
    String directoryBio;

    String name;
    String teamNum;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileName = (TextView)findViewById(R.id.profileName);
        profileTeamNumber = (TextView)findViewById(R.id.profileTeamNumber);
        profileSubTeam = (TextView)findViewById(R.id.profileSubTeam);
        profileAge = (TextView)findViewById(R.id.profileAge);
        profileType = (TextView)findViewById(R.id.profileType);
        profileBio = (TextView)findViewById(R.id.profileBio);

        Bundle b = getIntent().getExtras();
        name= b.getString("name");
        teamNum=b.getString("teamNum");

        showMemberInfo();
    }
    public void showMemberInfo() {
        //Show user info based on name passed down from ViewMembersActivity

        Uri directory = DirectoryProvider.CONTENT_URI; //Set source of database
        Cursor c = getContentResolver().query(directory, null, "name = ? AND teamnumber = ?", new String[] {name, teamNum}, "id"); //query the database for info

        String result = "Team member results:";
        if (!c.moveToFirst()) {
            Toast.makeText(this, result + " no content yet!", Toast.LENGTH_LONG).show(); //If there isn't anything indicate so to the user
        }else{
            do{ //Get specific fields from database and set the text fields on the page equal to the datas
                directoryName = c.getString(c.getColumnIndex(DirectoryProvider.NAME));
                directoryTeamNumber = c.getString(c.getColumnIndex(DirectoryProvider.TEAMNUMBER));
                directorySubTeam = c.getString(c.getColumnIndex(DirectoryProvider.SUBTEAM));
                directoryAge = c.getString(c.getColumnIndex(DirectoryProvider.AGE));
                directoryType = c.getString(c.getColumnIndex(DirectoryProvider.TYPE));
                directoryBio = c.getString(c.getColumnIndex(DirectoryProvider.BIO));
            } while (c.moveToNext());
            profileName.setText(directoryName);
            profileTeamNumber.setText(directoryTeamNumber);
            profileSubTeam.setText(directorySubTeam);
            profileAge.setText(directoryAge);
            profileType.setText(directoryType);
            profileBio.setText(directoryBio);
        }

    }
}
