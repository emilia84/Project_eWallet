package com.example.project_ewallet;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity {
    private TextView reservation;
    private DBManager dbManager;
    private String date;
    private String id;
    private String category;
    private double amount;
    private EditText txtAmount;
    private TextView txtCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_delete_expense);

        Intent i = getIntent();
        date = i.getStringExtra("date");
        id = i.getStringExtra("id");
        category = i.getStringExtra("category");
        amount = i.getExtras().getDouble("salary");

        reservation = (TextView) findViewById(R.id.txtRes);

        reservation.setText("Your selected date: " + date);
        txtCategory = (TextView) findViewById(R.id.txtCategory);
        txtCategory.setText("Your selected category: " + category);

        txtAmount = (EditText) findViewById(R.id.txtAmountExpense);
        txtAmount.setText("Your selected expense: " + String.valueOf(amount));

        Button but = (Button) findViewById(R.id.btnDate);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(UpdateDeleteActivity.this,d,c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
                reservation.setText(fmtDate.format(c.getTime()));
            }
        });



        dbManager = new DBManager(this);
        dbManager.open();
    }
    Calendar c = Calendar.getInstance();
    SimpleDateFormat fmtDate = new SimpleDateFormat("yyyy-MM-dd");
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
        Log.d("Application","onDestroy");
        dbManager.close();
    }

//    public void addNewExpense(View v) {
//        String catVal="";
//        switch(v.getId()){
//            case R.id.btnMiscel:
//                Button miscel = (Button) findViewById(R.id.btnMiscel);
//                catVal = miscel.getText().toString();
//                break;
//            case R.id.btnBill:
//                Button bill = (Button) findViewById(R.id.btnBill);
//                catVal = bill.getText().toString();
//                break;
//            case R.id.btnClot:
//                Button clothes = (Button) findViewById(R.id.btnClot);
//                catVal = clothes.getText().toString();
//                break;
//            case R.id.btnFood:
//                Button food = (Button) findViewById(R.id.btnFood);
//                catVal = food.getText().toString();
//                break;
//            case R.id.btnHealth:
//                Button health = (Button) findViewById(R.id.btnHealth);
//                catVal = health.getText().toString();
//                break;
//            case R.id.btnHouse:
//                Button house = (Button) findViewById(R.id.btnHouse);
//                catVal = house.getText().toString();
//                break;
//            case R.id.btnTrans:
//                Button transport = (Button) findViewById(R.id.btnTrans);
//                catVal = transport.getText().toString();
//                break;
//            case R.id.btnToilet:
//                Button toilet = (Button) findViewById(R.id.btnToilet);
//                catVal = toilet.getText().toString();
//                break;
//            case R.id.btnEnt:
//                Button entertain = (Button) findViewById(R.id.btnEnt);
//                catVal = entertain.getText().toString();
//                break;
//            default:throw new RuntimeException();
//
//        }
//        EditText amount = (EditText) findViewById(R.id.txtAmountExpense);
//        double amountVal = Double.parseDouble(amount.getText().toString());
//        dbManager.insertExpense(catVal, amountVal);
//    }

    public void updateData(View v) {
        Button update = (Button)findViewById(R.id.btnUpdate);
        EditText amount = (EditText)findViewById(R.id.txtAmountExpense);
        reservation = (TextView) findViewById(R.id.txtRes);

        String catVal = update.getText().toString();
        double amountVal = Double.parseDouble(amount.getText().toString());
        String date = reservation.getText().toString();

//        dbManager.updateExpense(id,catVal,amountVal,date);
    }
    public void deleteData(View v) {
        // you need to implement this


//        dbManager.deleteExpense(id);

    }

//    public void updateData(View v) {
//        Button update = (Button)findViewById(R.id.btnEdit);
//        EditText amount = (EditText)findViewById(R.id.txtAmount);
//        EditText id = (EditText) findViewById(R.id.txtID);
//        Date date;
//
//        String catVal = update.getText().toString();
//        long idVal = Long.parseLong(id.getText().toString());
//        double amountVal = Double.parseDouble(amount.getText().toString());
//        Date date;
//
//        dbManager.update(idVal,catVal,amountVal,date);
//    }
//    public void deleteData(View v) {
//        // you need to implement this
//        EditText id = (EditText) findViewById(R.id.txtID);
//        long idVal = Long.parseLong(id.getText().toString());
//        dbManager.delete(idVal);
//    }

}
