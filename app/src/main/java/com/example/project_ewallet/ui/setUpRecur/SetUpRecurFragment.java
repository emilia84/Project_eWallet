package com.example.project_ewallet.ui.setUpRecur;

import android.app.DatePickerDialog;
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
import com.example.project_ewallet.databinding.FragmentBudgetBinding;
import com.example.project_ewallet.databinding.FragmentSetuprecurBinding;

import java.text.DateFormat;
import java.util.Calendar;

public class SetUpRecurFragment extends Fragment{
    private FragmentSetuprecurBinding binding;
    private TextView reservation;
    private DBManager dbManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentSetuprecurBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
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
        dbManager = new DBManager(getContext());
        dbManager.open();

        return root;
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
    //Code Interval!!!!
    public void chooseInterval(View view){
        View root = binding.getRoot();
        final RadioButton rMonth = root.findViewById(R.id.radMonth);
        final RadioButton rWeek = root.findViewById(R.id.radWeek);
        if(rMonth.isChecked()){
            //
        }
        else if(rWeek.isChecked()){
            //
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        dbManager.close();
    }
}
