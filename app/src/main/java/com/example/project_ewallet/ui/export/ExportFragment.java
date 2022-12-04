package com.example.project_ewallet.ui.export;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import com.example.project_ewallet.R;
import com.example.project_ewallet.DBHelper;
import com.example.project_ewallet.DBManager;
import com.example.project_ewallet.databinding.FragmentBudgetBinding;
import com.example.project_ewallet.databinding.FragmentExportBinding;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;

public class ExportFragment extends Fragment {
    private FragmentExportBinding binding;
    private DBManager dbManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentExportBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        dbManager = new DBManager(this.getActivity());
        dbManager.open();
        Button btnExportExpenses = (Button) root.findViewById(R.id.btnExportExpense);
        Button btnExportIncome = (Button) root.findViewById(R.id.btnExportIncome);

        btnExportExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exportDBExpenses();
            }
        });

        btnExportIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exportDBIncome();
            }
        });

        return root;
    }

    private void exportDBExpenses() {


        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "expenses.csv");
        try
        {

            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));

            Cursor curCSV = dbManager.fetchExpense();
            csvWrite.writeNext(curCSV.getColumnNames());
            while(curCSV.moveToNext())
            {
                //Which column you want to export
                String[] arrStr ={String.valueOf(curCSV.getInt(0)),curCSV.getString(1), String.valueOf(curCSV.getDouble(2)),curCSV.getString(3)};
                csvWrite.writeNext(arrStr);
                Log.d("debug", String.valueOf(arrStr));
            }
            csvWrite.close();
            curCSV.close();

        }
        catch(Exception sqlEx)
        {
            Log.e("Message", sqlEx.getMessage(), sqlEx);
        }
    }

    private void exportDBIncome() {

        DBHelper dbhelper = new DBHelper(this.getContext());
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "income.csv");
        try
        {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));

            Cursor curCSV = dbManager.fetchIncome();
            csvWrite.writeNext(curCSV.getColumnNames());
            while(curCSV.moveToNext())
            {

                String[] arrStr ={String.valueOf(curCSV.getInt(0)),curCSV.getString(1), String.valueOf(curCSV.getDouble(2)),curCSV.getString(3)};
                csvWrite.writeNext(arrStr);
            }
            csvWrite.close();
            curCSV.close();


        }
        catch(Exception sqlEx)
        {
            Log.e("Message", sqlEx.getMessage(), sqlEx);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

