package com.example.android.university;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class registration extends AppCompatActivity implements View.OnClickListener {
    public static final String DB_NAME="University";
    public static final int DB_VERSION=1;
   EditText userName, password, reTypePassword, email, phone,gender;
    Button regBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        reTypePassword = (EditText) findViewById(R.id.reTypePassword);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        gender = (EditText) findViewById(R.id.gender);
        regBtn= (Button) findViewById(R.id.regBtn);
        regBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // start to save user to internal data base

        UserHelper userHelper=new UserHelper(this,DB_NAME,null,DB_VERSION);
        User user=new User();
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
        user.setPhone(phone.getText().toString());
        user.setUserName(userName.getText().toString());
        user.setgender(gender.getText().toString());
        userHelper.insertUser(user);


        // return to other activity with created user

        Intent intent=new Intent();
        intent.putExtra("user",user);
        setResult(RESULT_OK,intent);
        finish();
    }
}