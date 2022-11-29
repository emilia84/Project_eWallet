package com.example.project_ewallet;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        Intent intent = getIntent();
        Bundle b = intent.getBundleExtra("bundle");

        ArrayList<String[]> expense = (ArrayList<String[]>) b.getSerializable("expense");
        ArrayList<String[]> income = (ArrayList<String[]>) b.getSerializable("income");

        RecyclerView recyclerViewExpense = (RecyclerView) findViewById(R.id.rViewExpense);
        DetailAdapter adapterExpense = new DetailAdapter(expense);
        recyclerViewExpense.setHasFixedSize(true);
        recyclerViewExpense.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewExpense.setAdapter(adapterExpense);

        RecyclerView recyclerViewIncome = (RecyclerView) findViewById(R.id.rViewIncome);
        DetailAdapter adapterIncome = new DetailAdapter(income);
        recyclerViewIncome.setHasFixedSize(true);
        recyclerViewIncome.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewIncome.setAdapter(adapterIncome);
    }
}
