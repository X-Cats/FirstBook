package com.xcats.firstbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by aidan on 2/6/16.
 */
public class ViewMembersActivity extends Activity{
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
    }
    public void viewProfile(View view){
        Intent viewProfile = new Intent(this,ViewProfileActivity.class);
        this.startActivity(viewProfile);
    }
}
