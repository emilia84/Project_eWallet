package com.example.project_ewallet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.PreferenceManager;

import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity{

    private DBManager dbManager;
    ArrayList<String[]> expense = new ArrayList<>();
    ArrayList<String[]> income = new ArrayList<>();

    @Override
    protected void onResume() {

        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if (prefs.getBoolean("data_changed", false)) {
            expense.clear();
            income.clear();
            prefs.edit().remove("data_changed").apply();
            recreate();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        Intent intent = getIntent();
        Bundle b = intent.getBundleExtra("bundle");

         expense = (ArrayList<String[]>) b.getSerializable("expense");
         income = (ArrayList<String[]>) b.getSerializable("income");

        RecyclerView recyclerViewExpense = (RecyclerView) findViewById(R.id.rViewExpense);
        DetailAdapter adapterExpense = new DetailAdapter(expense);
        recyclerViewExpense.setHasFixedSize(true);
        recyclerViewExpense.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewExpense.setAdapter(adapterExpense);

        RecyclerView recyclerViewIncome = (RecyclerView) findViewById(R.id.rViewIncome);
        DetailAdapter adapterIncome = new DetailAdapter(income);
        recyclerViewIncome.setHasFixedSize(true);
        recyclerViewIncome.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewIncome.setAdapter(adapterIncome);

        dbManager = new DBManager(this);
        dbManager.open();
    }
    protected void onDestroy() {

        super.onDestroy();
        Log.d("Application","onDestroy");
        dbManager.close();
    }
}
