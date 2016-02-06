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
        //HELLLO!?!?!
        // Bye!
    }
    public void createProfile(View view){
        Intent createProf = new Intent(this,CreateProfileActivity.class);
        this.startActivity(createProf);
    }
}
