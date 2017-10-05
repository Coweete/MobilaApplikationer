package com.mah.ag0071.assigment1;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by User on 2017-09-14.
 */

public class LoginController {

    public static final String USERLOCATION = "se.mah.ag0071.assigment1.USER";

    private LoginFragment loginFragment;
    private SignUpFragment signUpFragment;
    private LoginActivity loginActivity;
    private UserFragment userFragment;
    private UserDBHelper database;
    private User[] antalAnvändare;
    private int currentFragment;
    private int toastDuration = Toast.LENGTH_SHORT;


    public LoginController(LoginActivity activity){
        this.loginActivity = activity;

        loginFragment = new LoginFragment();
        signUpFragment = new SignUpFragment();
        userFragment = new UserFragment();

        loginFragment.setLoginController(this);
        signUpFragment.setLoginController(this);
        userFragment.setController(this);

        database = new UserDBHelper(this.loginActivity);
        currentFragment = 1;
        this.loginActivity.setLoginView(loginFragment);
    }

    public void changeToSignUp(CharSequence username, CharSequence password) {
        currentFragment = 2;
        loginActivity.setSignupScreen(signUpFragment);
    }

    public void changeToLogin(CharSequence username, CharSequence password){
        currentFragment = 1;
        loginActivity.setLoginView(loginFragment);
    }

    public void addUser(String username,String password,String firstname,String surname){
        if(username.equals("") || password.equals("") || firstname.equals("") || surname.equals("")){
            Toast toast = Toast.makeText(loginActivity.getApplicationContext(),"Fill in all feilds",toastDuration);
            toast.show();
        }else{
            User user = new User(username,password,firstname,surname);
            addUserToDb(user);
        }
    }

    public void addUserToDb(User user){
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserDBHelper.COLUMN_USERNAME,user.getUserName());
        values.put(UserDBHelper.COLUMN_PASSWORD,user.getPassword());
        values.put(UserDBHelper.COLUMN_FIRSTNAME,user.getFirstName());
        values.put(UserDBHelper.COLUMN_SURNAME,user.getSurName());
        db.insert(UserDBHelper.TABLE_NAME,"",values);
    }


    public void login(String username, String password) {
        User[] users;
        User activeUser = null;
        boolean flag = false;
        int nameIndex,passIndex,firstIndex,surIndex,idIndex;


        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + com.mah.ag0071.assigment1.UserDBHelper.TABLE_NAME +
                " ORDER BY " + com.mah.ag0071.assigment1.UserDBHelper.COLUMN_USERNAME, null);
        users = new User[cursor.getCount()];

        nameIndex = cursor.getColumnIndex(com.mah.ag0071.assigment1.UserDBHelper.COLUMN_USERNAME);
        passIndex = cursor.getColumnIndex(com.mah.ag0071.assigment1.UserDBHelper.COLUMN_PASSWORD);
        firstIndex = cursor.getColumnIndex(com.mah.ag0071.assigment1.UserDBHelper.COLUMN_FIRSTNAME);
        surIndex = cursor.getColumnIndex(com.mah.ag0071.assigment1.UserDBHelper.COLUMN_SURNAME);

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            users[i] = new User(cursor.getString(nameIndex),cursor.getString(passIndex),
                    cursor.getString(firstIndex),cursor.getString(surIndex));

            if(users[i].getUserName().equals(username)){
                if (users[i].getPassword().equals(password)){
                    activeUser = users[i];
                    flag = true;
                } else{
                    showToast("Wrong Password");
                }
            }
        }


        if (flag){
            Intent intent = new Intent(loginActivity,MainActivity.class);
            intent.putExtra("activeUser",activeUser);
            loginActivity.startActivity(intent);

        }else {
            showToast("Username dosen´t exist");
        }

    }

    public void showUsers() {

        int nameIndex, passIndex,firstIndex,surIndex;
        User[] users = null;
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + com.mah.ag0071.assigment1.UserDBHelper.TABLE_NAME +
                        " ORDER BY " + com.mah.ag0071.assigment1.UserDBHelper.COLUMN_USERNAME, null);
        users = new User[cursor.getCount()];
        nameIndex = cursor.getColumnIndex(com.mah.ag0071.assigment1.UserDBHelper.COLUMN_USERNAME);
        passIndex = cursor.getColumnIndex(com.mah.ag0071.assigment1.UserDBHelper.COLUMN_PASSWORD);
        firstIndex = cursor.getColumnIndex(com.mah.ag0071.assigment1.UserDBHelper.COLUMN_FIRSTNAME);
        surIndex = cursor.getColumnIndex(com.mah.ag0071.assigment1.UserDBHelper.COLUMN_SURNAME);

        for (int i = 0; i < users.length; i++) {
            cursor.moveToPosition(i);
            users[i] = new User(cursor.getString(nameIndex),cursor.getString(passIndex),
                    cursor.getString(firstIndex),cursor.getString(surIndex));
        }
        antalAnvändare = users;
        String res[] = new String[antalAnvändare.length];
        for (int i = 0; i < antalAnvändare.length; i++) {
            Log.v("Antal använder: ",antalAnvändare[i].getUserName());
            res[i] = antalAnvändare[i].getUserName();
        }
        userFragment.setContent(res);
        loginActivity.setShouwUsers(userFragment);

    }

    public void deleteAllUsers() {
        SQLiteDatabase db = database.getWritableDatabase();
        db.delete(com.mah.ag0071.assigment1.UserDBHelper.TABLE_NAME,null,null);
    }

    public void showToast(String content){
        Toast toast = Toast.makeText(loginActivity.getApplicationContext(),content,toastDuration);
        toast.show();
    }

    public void saveData(Bundle outState) {
        outState.putInt("loginFragmentId",currentFragment);
        if (currentFragment == 2){
            outState.putStringArray("loginSetUpArray",signUpFragment.getValues());
        }
    }


}
