package com.mah.ag0071.assigment1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by User on 2017-09-19.
 */

public class ExpensesDBHelper extends SQLiteOpenHelper{

    public static final String TABEL_NAME = "Expenses";
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_USERNAME = "Username";
    public static final String COLUMN_AMOUNT = "Amount";
    public static final String COLUMN_CATEGORY = "Category";
    public static final String COLUMN_DATE = "Date";
    public static final String COLUMN_TITLE = "Title";
    public static final String DATABASE_NAME = "User.db";
    public static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "create table " + TABEL_NAME + "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_USERNAME + " text not null, " +
                    COLUMN_TITLE + " text not null, " +
                    COLUMN_CATEGORY + " text not null, " +
                    COLUMN_DATE + " text not null, " +
                    COLUMN_AMOUNT + " integer )";

    public ExpensesDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(IncomeDBHelper.class.getName(),
                "Upgrading database from version " + oldVersion +
                        " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_NAME);
        onCreate(db);
    }
}
