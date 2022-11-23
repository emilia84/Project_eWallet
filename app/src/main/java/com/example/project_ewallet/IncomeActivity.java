package com.example.project_ewallet;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class IncomeActivity extends AppCompatActivity {
    private TextView reservation;
    private DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.income_activity);

        reservation = (TextView) findViewById(R.id.txtRes);
        Button but = (Button) findViewById(R.id.btnDate);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(IncomeActivity.this, d, c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
                reservation.setText(fmtDate.format(c.getTime()));
            }
        });
        dbManager = new DBManager(this);
        dbManager.open();
    }

    Calendar c = Calendar.getInstance();
    DateFormat fmtDate = DateFormat.getDateInstance();
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, monthOfYear);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        }
    };

    protected void onDestroy() {

        super.onDestroy();
        Log.d("Application", "onDestroy");
        dbManager.close();
    }

    public void addData(View v) {
        String catVal = "";
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
        EditText amount = (EditText) findViewById(R.id.txtAmount);
        double amountVal = Double.parseDouble(amount.getText().toString());
        dbManager.insertExpense(catVal, amountVal);
    }
}
