package com.example.project_ewallet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    public static final String INCOME_TABLE = "INCOME";
    public static final String EXPENSE_TABLE = "EXPENSE";

    // Table columns
    public static final String ID = "_id";
    public static final String DATE = "date";
    public static final String AMOUNT = "amount";
    public static final String CATEGORIES = "categories";


    // Database Information
    static final String DB_NAME = "EWALLET.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_INCOME = "create table " + INCOME_TABLE + "(" + ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE + " TEXT NOT NULL, "
            + AMOUNT + " DOUBLE(9,2) NOT NULL, "
            + CATEGORIES + " TEXT NOT NULL );";

    private static final String CREATE_EXPENSE = "create table " + EXPENSE_TABLE + "(" + ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE + " TEXT NOT NULL, "
            + AMOUNT + " DOUBLE(9,2) NOT NULL, "
            + CATEGORIES + " TEXT NOT NULL );";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDB) {
        Log.d("DBHelper","onCreate");

        sqLiteDB.execSQL(CREATE_INCOME);
        sqLiteDB.execSQL(CREATE_EXPENSE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDB, int oldVersion, int newVerion) {
        Log.d("version change", "oldVersion: " + oldVersion + ", newVersion: " + newVerion);
        sqLiteDB.execSQL("DROP TABLE IF EXISTS " + INCOME_TABLE);
        sqLiteDB.execSQL("DROP TABLE IF EXISTS " + EXPENSE_TABLE);
        onCreate(sqLiteDB);
    }
}
