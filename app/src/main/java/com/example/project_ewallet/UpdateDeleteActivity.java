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
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_delete_activity);

        Intent i = getIntent();
        String date = i.getStringExtra("date");
        id = i.getStringExtra("id");
        String category = i.getStringExtra("category");
        double amount = i.getExtras().getDouble("amount");

        reservation = (TextView) findViewById(R.id.txtRes);
        reservation.setText(date);

        TextView txtCategory = (TextView) findViewById(R.id.txtCategory);
        txtCategory.setText(category);

        EditText txtAmount = (EditText) findViewById(R.id.txtUpdateAmount);
        txtAmount.setText(String.valueOf(amount));

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

    public void update(View v) {
        EditText amount = (EditText) findViewById(R.id.txtUpdateAmount);
        reservation = (TextView) findViewById(R.id.txtRes);

        double amountVal = Double.parseDouble(amount.getText().toString());
        String date = reservation.getText().toString();

        dbManager.updateIncome(Long.parseLong(id), amountVal, date);
        dbManager.updateExpense(Long.parseLong(id), amountVal, date);
        finish();
    }
    public void delete(View v) {
        dbManager.deleteIncome(Long.parseLong(id));
        dbManager.deleteExpense(Long.parseLong(id));
        finish();
    }
}
