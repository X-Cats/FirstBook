package com.xcats.firstbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by aidan on 2/6/16.
 */
public class ViewTeamsActivity extends Activity {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_list);
    }
    public void viewSubTeams (View view){
        Intent viewSubTeams = new Intent(this,ViewSubTeamsActivity.class);
        this.startActivity(viewSubTeams);
    }
}
