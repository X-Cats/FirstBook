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

        int count = numTeams(directory);

        Cursor c = getContentResolver().query(directory, new String[]{"DISTINCT teamnumber"}, null, null, "id");

        teamList = new String[count];

        if (!c.moveToFirst()) {
            Toast.makeText(this, " no content yet!", Toast.LENGTH_LONG).show();
        } else {
            for(int i=0;i<count;i++){
                teamList[i] = c.getString(c.getColumnIndex("teamnumber"));
                Log.e("Firstbook", teamList[i]);
                c.moveToNext();
            }
        }
    }

    public int numTeams(Uri directory){
        Cursor c = getContentResolver().query(directory, new String[]{"count(DISTINCT teamnumber)"}, null, null, "id");

        if (!c.moveToFirst()) {
        }else {
            String temp = c.getString(c.getColumnIndex("count(DISTINCT teamnumber)"));
            Log.e("Firstbook --", "There are distinct number of teams: " + temp );

            return Integer.parseInt(temp);
        }
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
        this.startActivity(viewSubTeams);
    }
}
