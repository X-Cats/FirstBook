package com.xcats.firstbook;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xcats.firstbook.Database.DirectoryProvider;

/**
 * Created by aidan on 2/6/16.
 */
public class ViewMembersActivity extends Activity{
    String subteam;
    TextView name1;
    TextView name2;
    TextView name3;
    TextView name4;
    TextView name5;
    TextView name6;
    String memberName;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);

        name1 = (TextView)findViewById(R.id.name1);
        name2 = (TextView)findViewById(R.id.name2);
        name3 = (TextView)findViewById(R.id.name3);
        name4 = (TextView)findViewById(R.id.name4);
        name5 = (TextView)findViewById(R.id.name5);
        name6 = (TextView)findViewById(R.id.name6);

        Bundle b = getIntent().getExtras();
        subteam = b.getString("subteam");

        showSubteamInfo();
    }
    public void viewProfile(View view){


        Intent viewProfile = new Intent(this,ViewProfileActivity.class);
        viewProfile.putExtra("name", memberName);
        this.startActivity(viewProfile);


    }
    public void showSubteamInfo() {
        //Show user info based on name passed down from ViewMembersActivity

        Uri directory = DirectoryProvider.CONTENT_URI; //Set source of database
        Cursor c = getContentResolver().query(directory, null, "subteam = ?", new String[] {subteam}, "id"); //query the database for info

        String result = "Team member results:";
        if (!c.moveToFirst()) {
            Toast.makeText(this, result + " no content yet!", Toast.LENGTH_LONG).show(); //If there isn't anything indicate so to the user
        }else{
            name1.setText(c.getString(c.getColumnIndex(DirectoryProvider.NAME)));
            c.moveToNext();
            name2.setText(c.getString(c.getColumnIndex(DirectoryProvider.NAME)));
            c.moveToNext();
            name3.setText(c.getString(c.getColumnIndex(DirectoryProvider.NAME)));
            c.moveToNext();
        }

    }

}
