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
        Bundle b = intent.getBundleExtra("BUNDLE");
        ArrayList<String[]> list = (ArrayList<String[]>) b.getSerializable("ARRAYLIST");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rView);
        DetailAdapter adapter = new DetailAdapter(list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
