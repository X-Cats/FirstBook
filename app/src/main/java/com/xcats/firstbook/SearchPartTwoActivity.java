package com.xcats.firstbook;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.xcats.firstbook.Database.DirectoryProvider;

/**
 * Created by alfs on 2/20/16.
 */
public class SearchPartTwoActivity extends Activity {
    String name;
    String teamNum;
    String type;
    String subTeam;

    TextView profileName;
    TextView profileTeamNumber;
    TextView profileSubTeam;
    TextView profileAge;
    TextView profileType;
    TextView profileBio;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Bundle b = getIntent().getExtras();

        profileName = (TextView)findViewById(R.id.profileName);
        profileTeamNumber = (TextView)findViewById(R.id.profileTeamNumber);
        profileSubTeam = (TextView)findViewById(R.id.profileSubTeam);
        profileAge = (TextView)findViewById(R.id.profileAge);
        profileType = (TextView)findViewById(R.id.profileType);
        profileBio = (TextView)findViewById(R.id.profileBio);

        name = (String) b.get("name");
        teamNum = (String) b.get("teamNum");
        subTeam = (String) b.get("subTeam");
        type = (String) b.get("type");

        Uri directory = DirectoryProvider.CONTENT_URI; //Set source of database
        String call = "";
        int length = 0;
        boolean[] values = new boolean[4];
        String idkWhatToCallThis[] = new String[]{name, teamNum, subTeam, type};
        if(!name.equalsIgnoreCase("")) {
            call += "name = ?";
            length += 1;
            values[0] = true;
        }
        if(!teamNum.equalsIgnoreCase("")){
            if(length > 0) {
                call += " AND ";
            }
            call += "teamnumber = ?";
            length += 1;
            values[1] = true;
        }
        if(!subTeam.equalsIgnoreCase("none")){
            if(length > 0) {
                call += " AND ";
            }
            call += "subteam = ?";
            length += 1;
            values[2] = true;
        }
        if(!type.equalsIgnoreCase("unknown")){
            if(length > 0) {
                call += " AND ";
            }
            call += "type = ?";
            length += 1;
            values[3] = true;
        }
        String[] input = new String[length];
        int j = 0;
        for(int i = 0; i < values.length; i++){
            if(values[i]){
                input[j] = idkWhatToCallThis[i];
                j+=1;
            }
        }
        Cursor c = getContentResolver().query(directory, null, call, input, "id"); //query the database for info

        String result = "Team member results:";
        if (!c.moveToFirst()) {
            Toast.makeText(this, "No results found", Toast.LENGTH_LONG).show(); //If there isn't anything indicate so to the user
            Intent goBack = new Intent(this, SearchActivity.class);
            this.startActivity(goBack);
        }else{
            String age;
            String bio;
            do { //Get specific fields from database and set the text fields on the page equal to the datas
                name = c.getString(c.getColumnIndex(DirectoryProvider.NAME));
                teamNum = c.getString(c.getColumnIndex(DirectoryProvider.TEAMNUMBER));
                subTeam = c.getString(c.getColumnIndex(DirectoryProvider.SUBTEAM));
                age = c.getString(c.getColumnIndex(DirectoryProvider.AGE));
                type = c.getString(c.getColumnIndex(DirectoryProvider.TYPE));
                bio = c.getString(c.getColumnIndex(DirectoryProvider.BIO));
            } while (c.moveToNext());
            profileName.setText(name);
            profileTeamNumber.setText(teamNum);
            profileSubTeam.setText(subTeam);
            profileAge.setText(age);
            profileType.setText(type);
            profileBio.setText(bio);
        }
        c.close();
    }

}
