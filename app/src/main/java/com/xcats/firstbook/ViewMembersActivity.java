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
public class ViewMembersActivity extends Activity{

    String teamNum;
    String subteam;
    TableLayout tl;
    String[] memberList;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
        Bundle b = getIntent().getExtras();
        teamNum = b.getString("teamNum");
        subteam = b.getString("subteam");


        tl = (TableLayout) findViewById(R.id.memberViewTableLayout);

        //generates a list of teams
        queryDB();

        generateTeams();
    }

    public void queryDB() {
        Uri directory = DirectoryProvider.CONTENT_URI;

        int count = numMembers(directory);

        Cursor c = getContentResolver().query(directory, new String[]{"DISTINCT name"}, "teamnumber = ?  AND subteam = ?", new String[] {teamNum,subteam}, "id");

        memberList = new String[count];

        if (!c.moveToFirst()) {
            Toast.makeText(this, " no content yet!", Toast.LENGTH_LONG).show();
        } else {
            for(int i=0;i<count;i++){
                memberList[i] = c.getString(c.getColumnIndex("name"));
                Log.e("Firstbook", memberList[i]);
                c.moveToNext();
            }
        }
    }

    public int numMembers(Uri directory){
        Cursor c = getContentResolver().query(directory, new String[]{"count(DISTINCT name)"}, "teamnumber = ? AND subteam = ?", new String[] {teamNum,subteam}, "id");

        if (!c.moveToFirst()) {
        }else {
            String temp = c.getString(c.getColumnIndex("count(DISTINCT name)"));
            Log.e("Firstbook --", "There are distinct number of members: " + temp );

            return Integer.parseInt(temp);
        }
        c.close();
        return 0;
    }

    public void generateTeams() {
        for(int j=0;j<memberList.length;j++)
        {
            // Inflate your row "template" and fill out the fields.
            TableRow row = (TableRow) LayoutInflater.from(this).inflate(R.layout.attrib_row, null);
            final String name = memberList[j];
            ((TextView)row.findViewById(R.id.attrib_teamName)).setText(name);

            ((ImageView)row.findViewById(R.id.attrib_teamImg)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    viewProfile(v, teamNum, subteam, name);
                }
            });;
            tl.addView(row);
        }
        tl.requestLayout();     // Not sure if this is needed.
    }
    public void viewProfile(View view, String teamNum, String subteam, String name){

        Intent viewProfile = new Intent(this,ViewProfileActivity.class);
        viewProfile.putExtra("teamNum", teamNum);
        viewProfile.putExtra("subteam", subteam);
        viewProfile.putExtra("name", name);
        this.startActivity(viewProfile);


    }

}
