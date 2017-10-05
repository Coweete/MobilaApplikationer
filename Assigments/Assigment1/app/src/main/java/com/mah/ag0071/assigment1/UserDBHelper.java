package com.mah.ag0071.assigment1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by User on 2017-09-15.
 */

public class UserDBHelper extends SQLiteOpenHelper{

    public static final String TABLE_NAME = "Users";
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_USERNAME = "Username";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String COLUMN_FIRSTNAME = "Firstname";
    public static final String COLUMN_SURNAME = "Surname";
    public static final String DATABASE_NAME = "Users.db";
    public static final int DATABASE_VERSION = 1;


    private static final String DATABASE_CREATE =
            "create table " + TABLE_NAME + "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_USERNAME + " text not null, "    +
                    COLUMN_PASSWORD + " text not null, "    +
                    COLUMN_FIRSTNAME + " text not null, "   +
                    COLUMN_SURNAME + " text not null )";

    public UserDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(UserDBHelper.class.getName(),
                "Upgrading database from version " + oldVersion +
                        " to " + newVersion +", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
