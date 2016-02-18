package com.xcats.firstbook;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.xcats.firstbook.Database.DirectoryProvider;

/**
 * Created by aidan on 2/6/16.
 */
public class ViewTeamsActivity extends Activity {
    TableLayout tl;

    String[] teamList;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_list);

        tl = (TableLayout) findViewById(R.id.teamViewTableLayout);

        //generates a list of teams
        queryDB();

        generateTeams();
    }

    public void queryDB() {
        Uri directory = DirectoryProvider.CONTENT_URI;

        //Make a new variable equal to the number of teams from the numTeams() function
        int count = numTeams(directory);

        //Find distinct teamnumbers to list
        Cursor c = getContentResolver().query(directory, new String[]{"DISTINCT teamnumber"}, null, null, "id");

        //Make an array of the teamnumbers that is equal in length to the number of teams.
        teamList = new String[count];
        //If the cursor doesn't find anything tell the user that nothing is in the database.
        if (!c.moveToFirst()) {
            Toast.makeText(this, " no content yet!", Toast.LENGTH_LONG).show();
        } else {
            //Otherwise, for each team in the database add their number to the teamList[] array
            for(int i=0;i<count;i++){
                teamList[i] = c.getString(c.getColumnIndex("teamnumber"));
                Log.e("Firstbook", teamList[i]);
                c.moveToNext();
            }
        }
        //Close the cursor so it doesn't use a lot of RAM
        c.close();
    }

    public int numTeams(Uri directory){
        //Look through team numbers. If a team number found is identical to a team number found before ignore it
        Cursor c = getContentResolver().query(directory, new String[]{"count(DISTINCT teamnumber)"}, null, null, "id");
        //If there is nothing there do nothing
        if (!c.moveToFirst()) {
        }else { //Otherwise return the number of teamnumbers.
            String temp = c.getString(c.getColumnIndex("count(DISTINCT teamnumber)"));
            Log.e("Firstbook --", "There are distinct number of teams: " + temp );

            //Returns number of teams in database
            return Integer.parseInt(temp);
        }
        c.close();
        return 0;
    }

    public void generateTeams() {
        for(int j=0;j<teamList.length;j++)
        {
            // Inflate your row "template" and fill out the fields.
            TableRow row = (TableRow) LayoutInflater.from(this).inflate(R.layout.attrib_row, null);
            final String teamNum = teamList[j];
            ((TextView)row.findViewById(R.id.attrib_teamName)).setText(teamNum);

            ((ImageView)row.findViewById(R.id.attrib_teamImg)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    viewSubTeams(v,teamNum);
                }
            });;
            tl.addView(row);
        }
        tl.requestLayout();     // Not sure if this is needed.
    }

    public void viewSubTeams(View view, String teamNum) {
        Intent viewSubTeams = new Intent(this, ViewSubTeamsActivity.class);
        viewSubTeams.putExtra("teamNum",teamNum);
        this.startActivity(viewSubTeams);
    }
}
