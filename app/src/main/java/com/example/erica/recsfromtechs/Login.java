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
     * Allows the User to cancel their login and go back to the welcome page
     * @param view The current layout with all the Android widgets
     */
    public void cancel(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    /**
     * When the user types in a correct username and password, it will take them to the dashboard
     * If not, then it will allow the user to know that one of the two is incorrect
     * @param view The current layout with all the Android widgets
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
            Intent oldIntent = getIntent();
            String userName = oldIntent.getStringExtra("userName");
            String userEmail = oldIntent.getStringExtra("userEmail");
            String userMajor = oldIntent.getStringExtra("userMajor");
            Bundle bundle = new Bundle();
            bundle.putString("userName",userName);
            bundle.putString("userEmail", userEmail);
            bundle.putString("userMajor", userMajor);
            bundle.putString("user", usernameText.getText().toString());
            intent.putExtras(bundle);
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
