package com.example.android.university;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity implements View.OnClickListener {
    EditText userName;
    EditText password;
    Button logen ,regast;
    CheckBox keepMeLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        logen = (Button) findViewById(R.id.login);
        regast = (Button) findViewById(R.id.regast);
        keepMeLog = (CheckBox) findViewById(R.id.keepMeLogin);
        logen.setOnClickListener(this);
        regast.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        if(logen==v){

            String username=userName.getText().toString();
            String password=this.password.getText().toString();

            User user=new UserHelper(this,registration.DB_NAME,null,registration.DB_VERSION).checkLogin(userName.getText().toString(),password.toString());
            if(user!=null){
                // do in success case
                Toast.makeText(this,"Welcome Mr "+user.getUserName(), Toast.LENGTH_SHORT).show();
            }else{
                // do in failure case
                Toast.makeText(this,"Invalid user name or password", Toast.LENGTH_SHORT).show();
            }

            // beta3at al checked
            if(keepMeLog.isChecked()){
                SharedPreferences preferences=getSharedPreferences(MainActivity.LOGIN_SHARED_PREFS_FILE,MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putBoolean(MainActivity.KEEP_ME_LOGIN_KEY,keepMeLog.isChecked());
                editor.putString(MainActivity.USER_NAME_KEY,username);
                editor.putString(MainActivity.PASSWORD_KEY,password);
                editor.commit();
            }else{
                SharedPreferences preferences=getSharedPreferences(MainActivity.LOGIN_SHARED_PREFS_FILE,MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putBoolean(MainActivity.KEEP_ME_LOGIN_KEY,keepMeLog.isChecked());
                editor.commit();
            }

            Intent intent =new Intent(login.this,HomeActivity.class);
            intent.putExtra(MainActivity.USER_NAME_KEY,username);
            startActivity(intent);


//                   if(!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,10}")){
//                       this.password.setError("Your Password not correct");
//                       Toast.makeText(this,"Minimum eight and maximum 10 characters, at least one uppercase letter, one lowercase letter, one number and one special character",Toast.LENGTH_LONG).show();
//                       return;
//                   }
        }
        // dah beta3 al regast
        else if (regast==v){
            Intent intent =new Intent(login.this,registration.class);
            startActivityForResult(intent,0);
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0){
            if(resultCode==RESULT_OK){
                User user= (User) data.getSerializableExtra("user");
                userName.setText(user.getUserName());
                password.setText(user.getPassword());
            }
        }
    }
}
