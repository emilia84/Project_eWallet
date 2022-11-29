package com.example.project_ewallet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class IncomeActivity extends AppCompatActivity {
    private TextView reservation;
    private DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.income_activity);

        dbManager = new DBManager(this);
        dbManager.open();
    }

    protected void onDestroy() {

        super.onDestroy();
        Log.d("Application", "onDestroy");
        dbManager.close();
    }

    public void addIncome(View v) {
        String catVal = "";
        Log.d("debug point", String.valueOf(v.getId()));
        switch (v.getId()) {
            case R.id.btnDep:
                Button deposit = (Button) findViewById(R.id.btnDep);
                catVal = deposit.getText().toString();
                break;
            case R.id.btnSal:
                Button salary = (Button) findViewById(R.id.btnSal);
                catVal = salary.getText().toString();
                break;
            case R.id.btnSav:
                Button saving = (Button) findViewById(R.id.btnSav);
                catVal = saving.getText().toString();
                break;
            default:
                throw new RuntimeException();
        }
        EditText amount = (EditText) findViewById(R.id.txtAmountIncome);
        double amountVal = Double.parseDouble(amount.getText().toString());
        dbManager.insertIncome(catVal, amountVal);

        finish();
    }
}
