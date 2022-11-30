package com.example.project_ewallet;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DBManager {
    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    // ================================================
    public DBManager open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }
    // ================================================

    public void insertIncome( String categories, double amount) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean("data_changed", true)
                .apply();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.DATE, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        contentValue.put(DBHelper.AMOUNT, amount);
        contentValue.put(DBHelper.CATEGORIES, categories);

        database.insert(DBHelper.INCOME_TABLE, null, contentValue);
    }
    public void insertExpense( String categories, double amount) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean("data_changed", true)
                .apply();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.DATE, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        contentValue.put(DBHelper.AMOUNT, amount);
        contentValue.put(DBHelper.CATEGORIES, categories);

        database.insert(DBHelper.EXPENSE_TABLE, null, contentValue);
    }

    public Cursor fetchIncome() {
        String[] columns = new String[] { DBHelper.ID, DBHelper.AMOUNT, DBHelper.DATE, DBHelper.CATEGORIES };
        Cursor cursor = database.rawQuery("SELECT * FROM INCOME",null);

        return cursor;
    }
    public Cursor fetchExpense() {
        String[] columns = new String[] { DBHelper.ID, DBHelper.AMOUNT, DBHelper.DATE, DBHelper.CATEGORIES };
        Cursor cursor = database.rawQuery("SELECT * FROM EXPENSE",null);

        return cursor;
    }


    public int updateIncome(long id, double amount, String date) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean("data_changed", true)
                .apply();

        ContentValues contentValues = new ContentValues();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.DATE, date);
//        contentValue.put(DBHelper.DATE, new SimpleDateFormat("yyyy-MM-dd").format(date));
        contentValue.put(DBHelper.AMOUNT, amount);

        int i = database.update(DBHelper.INCOME_TABLE, contentValues, DBHelper.ID + " = " + id, null);
        return i;
    }

    public int updateExpense(long id, double amount, String date) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean("data_changed", true)
                .apply();

        ContentValues contentValues = new ContentValues();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.DATE, date);
//        contentValue.put(DBHelper.DATE, new SimpleDateFormat("yyyy-MM-dd").format(date));
        contentValue.put(DBHelper.AMOUNT, amount);

        int i = database.update(DBHelper.EXPENSE_TABLE, contentValues, DBHelper.ID + " = " + id, null);
        return i;
    }

    // ====================================================
    public void deleteIncome(long id) {
        //Delete button is updated in detail list immediately
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean("data_changed", true)
                .apply();
        database.delete(DBHelper.INCOME_TABLE, DBHelper.ID + "=" + id, null);
    }
    public void deleteExpense(long id) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean("data_changed", true)
                .apply();
        database.delete(DBHelper.EXPENSE_TABLE, DBHelper.ID + "=" + id, null);
    }
}
