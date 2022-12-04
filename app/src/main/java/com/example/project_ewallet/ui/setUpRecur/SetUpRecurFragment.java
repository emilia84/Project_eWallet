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
    private TextView reservation;
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

        reservation = (TextView) root.findViewById(R.id.txtRes);
        Button but = (Button) root.findViewById(R.id.btnDate);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(view.getContext(),d,c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
                reservation.setText(fmtDate.format(c.getTime()));
            }
        });

        rMonth = (RadioButton) root.findViewById(R.id.radMonth);
        rWeek = (RadioButton) root.findViewById(R.id.radWeek);

        dbManager = new DBManager(getContext());
        dbManager.open();
        chooseInterval(root);
//        addRecurExpense(root);

        return root;
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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // save views as variables in this method
        // "view" is the one returned from onCreateView
        amount = (EditText) view.findViewById(R.id.txtAmountRecur);
        miscel = (Button) view.findViewById(R.id.btnMiscel);
        bill = (Button) view.findViewById(R.id.btnBill);
        clothes = (Button) view.findViewById(R.id.btnClot);
        food = (Button) view.findViewById(R.id.btnFood);
        health = (Button) view.findViewById(R.id.btnHealth);
        house = (Button) view.findViewById(R.id.btnHouse);
        transport = (Button) view.findViewById(R.id.btnTrans);
        toilet = (Button) view.findViewById(R.id.btnToilet);
        entertain = (Button) view.findViewById(R.id.btnEnt);
        amount = (EditText) view.findViewById(R.id.txtAmountRecur);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dbManager.close();
        binding = null;
    }

    //Code Interval!!!!
    public void chooseInterval(View view){
        if(rMonth.isChecked()){

        }
        else if(rWeek.isChecked()){

        }
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
        dbManager.insertExpense(catVal, amountVal);
    }
}
