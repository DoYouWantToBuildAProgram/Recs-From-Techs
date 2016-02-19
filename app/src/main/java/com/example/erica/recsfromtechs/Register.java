package com.example.erica.recsfromtechs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Register extends AppCompatActivity {
    SharedPreferences passwords;
    SharedPreferences.Editor editPasswords;
    SharedPreferences userInfo;
    SharedPreferences.Editor editUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        passwords  = getSharedPreferences("MyPref", MODE_PRIVATE);
        editPasswords = passwords.edit();
        userInfo = getSharedPreferences("AnotherPref", MODE_PRIVATE);
        editUserInfo = userInfo.edit();



    }

    /**
     * Creates a user object based off of the information provided from the user such as username,
     * password, name, major, and email.
     * @param view The current layout with all the Android widgets
     */
    public void addUser(View view) {
        EditText usernameText = (EditText) findViewById(R.id.username);
        EditText passwordText = (EditText) findViewById(R.id.password);
        EditText emailText = (EditText) findViewById(R.id.email);
        EditText nameText = (EditText) findViewById(R.id.name);
        EditText majorText = (EditText) findViewById(R.id.major);
        User currentUser = new User(nameText.getText().toString(),emailText.getText().toString(),majorText.getText().toString());

        editPasswords.putString(usernameText.getText().toString(), passwordText.getText().toString());
        editPasswords.commit();
        editUserInfo.putString("name", nameText.getText().toString());
        editUserInfo.commit();
        editUserInfo.putString("email", emailText.getText().toString());
        editUserInfo.commit();
        editUserInfo.putString("major",majorText.getText().toString());
        editUserInfo.commit();
        Intent intent = new Intent(this,Login.class);
        Bundle bundle = new Bundle();
        bundle.putString("userName",currentUser.getName());
        bundle.putString("userEmail", currentUser.getEmail());
        bundle.putString("userMajor", currentUser.getMajor());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * Allows the user to cancel their registration and takes them back to the welcome screen
     * @param view The current layout with all the Android widgets
     */
    public void cancel(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
