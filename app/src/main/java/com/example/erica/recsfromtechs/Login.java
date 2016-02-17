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
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;

import java.util.HashMap;

public class Login extends AppCompatActivity {

    SharedPreferences passwords;
    SharedPreferences.Editor editPasswords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        passwords  = getSharedPreferences("MyPref", MODE_PRIVATE);
        editPasswords = passwords.edit();
    }

    /**
     * This method is used for the cancel button and returns the user to the main activity screen
     *
     * @param view the view of the current page
     */
    public void cancel(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    /**
     * This method checks to see whether the login works or not when the login button is pressed.
     * If the login works, then it redirects to the dashboard page, if not it pops up a toast.
     *
     * @param view the view of the current page
     */
    public void loginSuccessful(View view) {
        EditText usernameText = (EditText) findViewById(R.id.username);
        EditText passwordText = (EditText) findViewById(R.id.password);

        String storedPassword = passwords.getString(usernameText.getText().toString(), null);
        Log.d("password", "password: " + storedPassword);

        if (storedPassword != null && storedPassword.equals(passwordText.getText().toString())) {
            Intent intent = new Intent(this,dashboard.class);
            //this sends the user name to the dashboard
            //called "extra", it is essentially a map
            //"user" is our key
            intent.putExtra("user", usernameText.getText());
            Log.v("welcome", "input" + usernameText.getText());
            startActivity(intent);
        } else {
            int duration = Toast.LENGTH_SHORT;
            Context context = getApplicationContext();
            CharSequence text = "Username or Password Incorrect";
            Toast toast = Toast.makeText(context,text,duration);
            toast.show();
        }

    }

}
