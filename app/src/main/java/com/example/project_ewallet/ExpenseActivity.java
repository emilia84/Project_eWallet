package com.example.project_ewallet;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;

public class ExpenseActivity extends AppCompatActivity {
//    private TextView reservation;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_activity);

        dbManager = new DBManager(this);
        dbManager.open();
    }

    protected void onDestroy() {

        super.onDestroy();
        Log.d("Application","onDestroy");
        dbManager.close();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addExpense(View v) {
        String catVal="";
        switch(v.getId()){
            case R.id.btnMiscel:
                Button miscel = (Button) findViewById(R.id.btnMiscel);
                catVal = miscel.getText().toString();
                break;
            case R.id.btnBill:
                Button bill = (Button) findViewById(R.id.btnBill);
                catVal = bill.getText().toString();
                break;
            case R.id.btnClot:
                Button clothes = (Button) findViewById(R.id.btnClot);
                catVal = clothes.getText().toString();
                break;
            case R.id.btnFood:
                Button food = (Button) findViewById(R.id.btnFood);
                catVal = food.getText().toString();
                break;
            case R.id.btnHealth:
                Button health = (Button) findViewById(R.id.btnHealth);
                catVal = health.getText().toString();
                break;
            case R.id.btnHouse:
                Button house = (Button) findViewById(R.id.btnHouse);
                catVal = house.getText().toString();
                break;
            case R.id.btnTrans:
                Button transport = (Button) findViewById(R.id.btnTrans);
                catVal = transport.getText().toString();
                break;
            case R.id.btnToilet:
                Button toilet = (Button) findViewById(R.id.btnToilet);
                catVal = toilet.getText().toString();
                break;
            case R.id.btnEnt:
                Button entertain = (Button) findViewById(R.id.btnEnt);
                catVal = entertain.getText().toString();
                break;
            default:throw new RuntimeException();

        }
        EditText amount = (EditText) findViewById(R.id.txtAmountRecur);
        double amountVal = Double.parseDouble(amount.getText().toString());
        dbManager.insertExpense(catVal, amountVal, String.valueOf(LocalDate.now()));
        finish();
    }
}
