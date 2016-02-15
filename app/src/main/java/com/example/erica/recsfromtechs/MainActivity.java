package com.example.erica.recsfromtechs;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    SharedPreferences passwords;
    SharedPreferences.Editor editPasswords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        passwords  = getSharedPreferences("MyPref", MODE_PRIVATE);
        editPasswords = passwords.edit();
        editPasswords.putString("bob", "password");
        editPasswords.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This method is for the login in button on the main page and
     * it changes the view to the Login screen
     *
     * @param view the view of the current page
     */
    public void login(View view) {
        Intent intent = new Intent(this, Login.class);


        startActivity(intent);
    }

    /**
     * This method is used for the register button and changes the view to the register page
     *
     * @param view the view of the current page
     */
    public void register(View view) {
        Intent intent = new Intent(this, Register.class);

        startActivity(intent);
    }

}
