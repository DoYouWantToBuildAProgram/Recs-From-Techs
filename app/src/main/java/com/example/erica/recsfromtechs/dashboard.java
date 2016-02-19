package com.example.erica.recsfromtechs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

public class dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        String usernameText = "";
        if(savedInstanceState == null) {
            //the user name has been passed to the dashboard as an "extra"
            //works like a map, "user" is our key
            //Log.v("welcome", "saved instance state null");
            Intent oldIntent = getIntent();
            String user = oldIntent.getStringExtra("user");
            usernameText = "" + user;
        } else {
            //Log.v("welcome", "saved instance state not null");
            usernameText = (String) savedInstanceState.getSerializable("user");
        }
        //Log.v("welcome", "username is found as "+usernameText);


        //This is changing the text at the top of our dashboard to welcome our specific user
        TextView welcomeTextView =new TextView(this);
        //welcomeTextView =(TextView)findViewById(R.id.welcome);
        //welcomeTextView.setText("Welcome "+usernameText);

    }

    /**
     * Logs the user out of the dashboard and brings them back to the welcome page
     * @param view The current layout with all the Android widgets
     */
    public void logout(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }

    /**
     * Allows the user to change their account information, such as name, email, and major
     * @param view The current layout with all the Android widgets
     */
    public void editProfile(View view) {
        Intent intent = new Intent(this, Profile.class);
        Intent oldIntent = getIntent();
        String userName = oldIntent.getStringExtra("userName");
        String userEmail = oldIntent.getStringExtra("userEmail");
        String userMajor = oldIntent.getStringExtra("userMajor");
        Bundle bundle = new Bundle();
        bundle.putString("userName",userName);
        bundle.putString("userEmail", userEmail);
        bundle.putString("userMajor", userMajor);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
