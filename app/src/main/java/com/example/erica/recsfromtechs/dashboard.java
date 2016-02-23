package com.example.erica.recsfromtechs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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
            Log.v("welcome", "saved instance state null");
            Bundle extras = getIntent().getExtras();
            usernameText = "" + extras.get("user");
        } else {
            Log.v("welcome", "saved instance state not null");
            usernameText = (String) savedInstanceState.getSerializable("user");
        }
        Log.v("welcome", "username is found as "+usernameText);


        //This is changing the text at the top of our dashboard to welcome our specific user
        TextView welcomeTextView = new TextView(this);
        welcomeTextView =(TextView)findViewById(R.id.welcome);
        welcomeTextView.setText("Welcome "+usernameText);

    }

    /**
     * This method is used for the logout button and returns the user to the main activity screen
     *
     * @param view the view of the current page
     */
    public void logout(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }

    public void movieSearch(View view) {
        Intent intent = new Intent(this,searchScreen.class);
        startActivity(intent);

    }

}
