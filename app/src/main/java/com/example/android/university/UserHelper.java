package com.example.android.university;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Eltabey on 8/4/2017.
 */

public class UserHelper extends SQLiteOpenHelper {
    public static final String USER_TABLE = "user";
    public static final String USER_NAME_COL = "user_name";
    public static final String USER_PASSWORD_COL = "password";
    public static final String USER_PHONE_COL = "phone";
    public static final String USER_ID_COL = "id";
    public static final String USER_EMAIL_COL = "email";
    public static final String USER_GENDER_COL = "gender";

    public UserHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createSQL = "CREATE  TABLE  IF NOT EXISTS " + USER_TABLE
                + "(" + USER_ID_COL + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , " + USER_NAME_COL + " TEXT NOT NULL  UNIQUE , " + USER_PASSWORD_COL + " TEXT NOT NULL , " + USER_EMAIL_COL + " TEXT NOT NULL  UNIQUE, " + USER_GENDER_COL + " TEXT NOT NULL  UNIQUE , " + USER_PHONE_COL + " TEXT)";
        sqLiteDatabase.execSQL(createSQL);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertUser(User user) {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(USER_NAME_COL,user.getUserName());
        values.put(USER_EMAIL_COL,user.getEmail());
        values.put(USER_PASSWORD_COL,user.getPassword());
        values.put(USER_PHONE_COL,user.getPhone());
        sqLiteDatabase.insert(USER_TABLE,null,values);
        sqLiteDatabase.close();
    }
    public User checkLogin(String userName,String password){
        User user =null;
        SQLiteDatabase database=getReadableDatabase();
        Cursor cursor= database.rawQuery("select * from "+USER_TABLE+" where "+USER_NAME_COL+" = '"+userName+"' and "+USER_PASSWORD_COL+" = '"+password+"'",null);
        if(cursor.moveToFirst()){
            do{
                user=new User();
                user.setUserName(cursor.getString(cursor.getColumnIndex(USER_NAME_COL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(USER_PASSWORD_COL)));
                user.setPhone(cursor.getString(cursor.getColumnIndex(USER_PHONE_COL)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(USER_EMAIL_COL)));
                user.setgender(cursor.getString(cursor.getColumnIndex(USER_GENDER_COL)));

            }while(cursor.moveToNext());
        }
        database.close();
        return user;
    }
}
