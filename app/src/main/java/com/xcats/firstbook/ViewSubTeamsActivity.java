package com.xcats.firstbook;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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
public class ViewSubTeamsActivity extends Activity{

    String [] subTeamList;
    TableLayout tl;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subteam_list);

        queryDB();
        generateSubTeams();
    }
    public void viewMembers (View view){
        Intent viewMembers = new Intent(this,ViewMembersActivity.class);
        viewMembers.putExtra("subteam","App");
        this.startActivity(viewMembers);

    }
    public void queryDB() {
        Uri directory = DirectoryProvider.CONTENT_URI;

        int count = numSubTeams(directory);

        Cursor c = getContentResolver().query(directory, new String[]{"DISTINCT subteam"}, null, null, "id");

        subTeamList = new String[count];

        if (!c.moveToFirst()) {
            Toast.makeText(this, " no content yet!", Toast.LENGTH_LONG).show();
        } else {
            for(int i=0;i<count;i++){
                subTeamList[i] = c.getString(c.getColumnIndex("subteam"));
                Log.e("Firstbook", subTeamList[i]);
                c.moveToNext();
            }
        }
    }

    public int numSubTeams(Uri directory){
        Cursor c = getContentResolver().query(directory, new String[]{"count(DISTINCT subteam)"}, null, null, "id");

        if (!c.moveToFirst()) {
        }else {
            String temp = c.getString(c.getColumnIndex("count(DISTINCT subteam)"));
            Log.e("Firstbook --", "There are distinct number of subteams: " + temp );

            return Integer.parseInt(temp);
        }
        return 0;
    }

    public void generateSubTeams() {
        for(int j=0;j<subTeamList.length;j++)
        {
            // Inflate your row "template" and fill out the fields.
            TableRow row = (TableRow) LayoutInflater.from(this).inflate(R.layout.attrib_row, null);
            final String teamNum = subTeamList[j];
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
