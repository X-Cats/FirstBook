package com.xcats.firstbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by aidan on 2/6/16.
 */
public class CreateProfileActivity extends Activity {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile1);
    }
    void submitInfo(View view){
        Intent createProfile = new Intent(this,CreateProfileActivity2.class);
        this.startActivity(createProfile);
    }
}
