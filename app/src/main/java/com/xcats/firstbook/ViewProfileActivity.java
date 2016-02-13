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
public class ViewProfileActivity extends Activity{
    TextView bio;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        bio = (TextView)findViewById(R.id.profileBio);
        showAllMemberInfo();
    }
    public void showAllMemberInfo() {
        // Show all the birthdays sorted by friend's name
        Uri directory = DirectoryProvider.CONTENT_URI;
        Cursor c = getContentResolver().query(directory, null, null, null, "id");
        String result = "Team member results:";

        if (!c.moveToFirst()) {
            Toast.makeText(this, result + " no content yet!", Toast.LENGTH_LONG).show();
        }else{
            do{
                result = result + "\n" + c.getString(c.getColumnIndex(DirectoryProvider.NAME)) +
                        " with id " +  c.getString(c.getColumnIndex(DirectoryProvider.ID)) +
                        " belongs to team: " + c.getString(c.getColumnIndex(DirectoryProvider.TEAMNUMBER)) +
                        " is: " + c.getString(c.getColumnIndex(DirectoryProvider.AGE)) + "years old" +
                        " and is on " + c.getString(c.getColumnIndex(DirectoryProvider.SUBTEAM)) + " subteam" +
                        " and is a" + c.getString(c.getColumnIndex(DirectoryProvider.TYPE))+
                        " whose bio looks like: " + c.getString(c.getColumnIndex(DirectoryProvider.BIO));

            } while (c.moveToNext());
            bio.setText(result);
        }

    }
    /*public void goHome(View view){
        Intent goHome = new Intent(this,LogInActivity.class);
        this.startActivity(goHome);
    }*/
}
