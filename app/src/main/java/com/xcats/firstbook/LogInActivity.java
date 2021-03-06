package com.xcats.firstbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LogInActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }
    public void createProfile(View view){
        Intent createProf = new Intent(this,CreateProfileActivity.class);
        this.startActivity(createProf);
    }
    public void viewTeams(View view){
        Intent viewTeams = new Intent(this,ViewTeamsActivity.class);
        this.startActivity(viewTeams);
    }
    public void search(View view){
        Intent search = new Intent(this, SearchActivity.class);
        this.startActivity(search);
    }
}
