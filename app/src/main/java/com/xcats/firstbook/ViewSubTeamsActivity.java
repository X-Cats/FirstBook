package com.xcats.firstbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by aidan on 2/6/16.
 */
public class ViewSubTeamsActivity extends Activity{
    String teamNum;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subteam_list);
        Bundle b = getIntent().getExtras();
        teamNum = b.getString("teamNum");
    }
    public void viewMembers (View view){
        Intent viewMembers = new Intent(this,ViewMembersActivity.class);
        viewMembers.putExtra("subteam","App");
        this.startActivity(viewMembers);

    }
}
