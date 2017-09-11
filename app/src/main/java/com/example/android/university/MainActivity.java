package com.example.android.university;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public static final String KEEP_ME_LOGIN_KEY = "keepMeLogin";
    public static final String USER_NAME_KEY = "userName";
    public static final String PASSWORD_KEY = "password";

    public static final String LOGIN_SHARED_PREFS_FILE = "LoginPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getSharedPreferences(LOGIN_SHARED_PREFS_FILE, MODE_PRIVATE);
                Intent intent = null;
                if (preferences.getBoolean(KEEP_ME_LOGIN_KEY, false)) {
                    // Home
                    intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra(USER_NAME_KEY, preferences.getString(USER_NAME_KEY, ""));

                } else {
                    // Login
                    intent = new Intent(MainActivity.this,login.class);

                }
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
