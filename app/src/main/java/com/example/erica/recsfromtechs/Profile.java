package com.example.erica.recsfromtechs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


public class Profile extends AppCompatActivity {
    SharedPreferences userInfo;
    SharedPreferences.Editor editUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userInfo = getSharedPreferences("AnotherPref", MODE_PRIVATE);
        editUserInfo = userInfo.edit();
        //Intent oldIntent = getIntent();
        //String userName = oldIntent.getStringExtra("userName");
        //String userEmail = oldIntent.getStringExtra("userEmail");
        //String userMajor = oldIntent.getStringExtra("userMajor");
        String userName = userInfo.getString("name", null);
        TextView nameText = (TextView)findViewById(R.id.name);
        nameText.setText(userName);
        String userEmail = userInfo.getString("email", null);
        TextView emailText = (TextView)findViewById(R.id.email);
        emailText.setText(userEmail);
        String userMajor = userInfo.getString("major", null);
        TextView majorText = (TextView)findViewById(R.id.major);
        majorText.setText(userMajor);

    }

    /**
     * Allows the user to change their name
     * @param view The current layout with all the Android widgets
     */
    public void editName(View view) {
        TextView nameText = (TextView)findViewById(R.id.name);
        EditText changedName = (EditText)findViewById(R.id.newName);
        nameText.setText(changedName.getText().toString());
        editUserInfo.putString("name", changedName.getText().toString());
        editUserInfo.commit();
    }

    /**
     * Allows the user to change their email
     * @param view The current layout with all the Android widgets
     */
    public void editEmail(View view) {
        TextView emailText = (TextView)findViewById(R.id.email);
        EditText changedEmail = (EditText)findViewById(R.id.newEmail);
        emailText.setText(changedEmail.getText().toString());
        editUserInfo.putString("email", changedEmail.getText().toString());
        editUserInfo.commit();
    }

    /**
     * Allows the user to change their major
     * @param view The current layout with all the Android widgets
     */
    public void editMajor(View view) {
        TextView majorText = (TextView) findViewById(R.id.major);
        EditText changedMajor = (EditText)findViewById(R.id.newMajor);
        majorText.setText(changedMajor.getText().toString());
        editUserInfo.putString("major", changedMajor.getText().toString());
        editUserInfo.commit();
    }

    /**
     * Allows the user to go back to the dashboard
     * @param view The current layout with all the Android widgets
     */
    public void backToDashboard(View view) {
        Intent intent = new Intent(this, dashboard.class);
        startActivity(intent);
    }



}
