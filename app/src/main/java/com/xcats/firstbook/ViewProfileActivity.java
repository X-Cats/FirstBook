package com.xcats.firstbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by aidan on 2/6/16.
 */
public class ViewProfileActivity extends Activity{
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
    /*public void goHome(View view){
        Intent goHome = new Intent(this,LogInActivity.class);
        this.startActivity(goHome);
    }*/
}
