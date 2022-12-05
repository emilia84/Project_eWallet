package com.example.project_ewallet.ui.setUpRecur;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.project_ewallet.DBManager;
import com.example.project_ewallet.R;
import com.example.project_ewallet.databinding.FragmentSetuprecurBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SetUpRecurFragment extends Fragment{

    private FragmentSetuprecurBinding binding;
    private DBManager dbManager;
    private RadioButton rMonth, rWeek;
    private Button miscel, bill, clothes, food, health, house, transport, toilet, entertain;
    private EditText amount;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSetuprecurBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dbManager = new DBManager(this.getActivity());
        dbManager.open();
        amount = (EditText) root.findViewById(R.id.txtAmountRecur);

        //set onClick Listener for every button, pass in the button in addRecurExpense.
        miscel = (Button) root.findViewById(R.id.btnMiscel);
        miscel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRecurExpense(miscel);
            }
        });

        bill = (Button) root.findViewById(R.id.btnBill);
        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRecurExpense(bill);
            }
        });

        clothes = (Button) root.findViewById(R.id.btnClot);
        clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRecurExpense(clothes);
            }
        });

        food = (Button) root.findViewById(R.id.btnFood);
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRecurExpense(food);
            }
        });

        health = (Button) root.findViewById(R.id.btnHealth);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRecurExpense(health);
            }
        });

        house = (Button) root.findViewById(R.id.btnHouse);
        house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRecurExpense(house);
            }
        });

        transport = (Button) root.findViewById(R.id.btnTrans);
        transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRecurExpense(transport);
            }
        });

        toilet = (Button) root.findViewById(R.id.btnToilet);
        toilet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRecurExpense(toilet);
            }
        });

        entertain = (Button) root.findViewById(R.id.btnEnt);
        entertain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRecurExpense(entertain);
            }
        });

        amount = (EditText) root.findViewById(R.id.txtAmountRecur);
        amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRecurExpense(amount);
            }
        });

        rMonth = (RadioButton) root.findViewById(R.id.radMonth);
        rWeek = (RadioButton) root.findViewById(R.id.radWeek);

        dbManager = new DBManager(getContext());
        dbManager.open();

        return root;
    }
    Calendar c = Calendar.getInstance();
    int month = c.get(Calendar.MONTH);
    int week = c.get(Calendar.WEEK_OF_YEAR);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dbManager.close();
        binding = null;
    }

    public void addRecurExpense(View v) {
        String catVal = "";
        if(v != null){
            switch (v.getId()) {
                case R.id.btnMiscel:
                    catVal = miscel.getText().toString();
                    break;
                case R.id.btnBill:
                    catVal = bill.getText().toString();
                    break;
                case R.id.btnClot:
                    catVal = clothes.getText().toString();
                    break;
                case R.id.btnFood:
                    catVal = food.getText().toString();
                    break;
                case R.id.btnHealth:
                    catVal = health.getText().toString();
                    break;
                case R.id.btnHouse:
                    catVal = house.getText().toString();
                    break;
                case R.id.btnTrans:
                    catVal = transport.getText().toString();
                    break;
                case R.id.btnToilet:
                    catVal = toilet.getText().toString();
                    break;
                case R.id.btnEnt:
                    catVal = entertain.getText().toString();
                    break;
                default:
//                throw new RuntimeException();
            }
        }

        double amountVal = Double.parseDouble(amount.getText().toString());

        if(rMonth.isChecked()){
            for( int j=0; j<= 11; j++){
                dbManager.insertExpense(catVal, amountVal);
                //set up loop each month
                for(int currentMonth = month; currentMonth < 12; currentMonth++){
                    c.set(Calendar.MONTH, currentMonth);
                }
            }
        }

        else if(rWeek.isChecked()){
            for( int j=0; j<= 51; j++) {
                dbManager.insertExpense(catVal, amountVal);
                //set up loop each week
                for(int currentWeek = week; currentWeek < 52; currentWeek++){
                    c.set(Calendar.WEEK_OF_YEAR, currentWeek);
                }
            }
        }
    }
}
