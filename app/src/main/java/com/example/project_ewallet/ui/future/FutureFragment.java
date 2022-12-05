package com.example.project_ewallet.ui.future;


import android.app.FragmentTransaction;
import android.content.Intent;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.project_ewallet.DBManager;

import com.example.project_ewallet.DetailActivity;
import com.example.project_ewallet.ExpenseActivity;
import com.example.project_ewallet.IncomeActivity;

import com.example.project_ewallet.R;
import com.example.project_ewallet.databinding.FragmentFutureBinding;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FutureFragment extends Fragment {

        private FragmentFutureBinding binding;
        private PieChart pieChart;

        private DBManager dbManager;
        private Map<String, Double> typeAmountMap = new HashMap<>();

        ArrayList<String[]> expenseArr = new ArrayList<>();
        ArrayList<String[]> incomeArr = new ArrayList<>();

        Double totalIncome = 0.0;
        Double totalExpense = 0.0;
        Double balance = 0.0;

        @Override
        public void onResume() {

            super.onResume();
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.getContext());

            if (prefs.getBoolean("data_changed", false)) {
                prefs.edit().remove("data_changed").apply();
            getParentFragmentManager().beginTransaction().detach(this).commit();
            getParentFragmentManager().beginTransaction().attach(this).commit();

            }
        }
        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {

            binding = FragmentFutureBinding.inflate(inflater, container, false);

            View root = binding.getRoot();
            pieChart = (PieChart) root.findViewById(R.id.chart);
            TextView txtStatus = (TextView) root.findViewById(R.id.txtStatus);

            dbManager = new DBManager(this.getActivity());
            dbManager.open();

            initPieChart();
            getData(root);
            txtStatus.setText("Balance: " + balance);
            showPieChart();

            Button btnDetails = (Button)root.findViewById(R.id.btnDetails);
            btnDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), DetailActivity.class);
                    Bundle args = new Bundle();
                    args.putSerializable("expense",(Serializable)expenseArr);
                    args.putSerializable("income",(Serializable)incomeArr);
                    i.putExtra("bundle",args);
                    startActivity(i);
                }
            });

            Button btnExp = (Button)root.findViewById(R.id.btnExpense);
            btnExp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), ExpenseActivity.class);
                    startActivity(i);
                }
            });

            Button btnIn = (Button)root.findViewById(R.id.btnIncome);
            btnIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), IncomeActivity.class);
                    startActivity(i);
                }
            });

            return root;
        }

    private void showPieChart(){

        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        //initializing data

        Log.d("piechart", "pie chart showed");

        for (String[] expense: expenseArr) {
            if(typeAmountMap.containsKey(expense[3])){
                typeAmountMap.put(expense[3],typeAmountMap.get(expense[3]) + Double.parseDouble(expense[2]));
            }else{
                typeAmountMap.put(expense[3],Double.parseDouble(expense[2]));
            }

        }

        //input data and fit data into pie chart entry
        for(String type: typeAmountMap.keySet()){

            Log.d("keyset", type);
            Log.d("amt", String.valueOf(typeAmountMap.get(type)));
            pieEntries.add(new PieEntry(typeAmountMap.get(type).floatValue(), type));
        }

        //set color
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#FFBE0B"));
        colors.add(Color.parseColor("#FD8A09"));
        colors.add(Color.parseColor("#FB5607"));
        colors.add(Color.parseColor("#FD2133"));
        colors.add(Color.parseColor("#FF006E"));
        colors.add(Color.parseColor("#C11CAD"));
        colors.add(Color.parseColor("#A22ACD"));
        colors.add(Color.parseColor("#8338EC"));
        colors.add(Color.parseColor("#3A86FF"));

        //collecting the entries with label name
        PieDataSet pieDataSet = new PieDataSet(pieEntries,null);
        //setting text size of the value
        pieDataSet.setValueTextSize(12f);
        //providing color list for coloring different entries
        pieDataSet.setColors(colors);
        //grouping the data set from entry to chart
        PieData pieData = new PieData(pieDataSet);
        //showing the value of the entries, default true if not set
        pieData.setDrawValues(true);
        //set word wrap
        pieChart.getLegend().setWordWrapEnabled(true);

        pieChart.setNoDataText("No data exists!");

        pieChart.setData(pieData);
        pieChart.setDrawEntryLabels(false);
        pieChart.notifyDataSetChanged();
        pieChart.invalidate();

    }

    private void initPieChart() {
        //using percentage as values instead of amount
        pieChart.setUsePercentValues(true);

        //remove the description label on the lower left corner, default true if not set
        pieChart.getDescription().setEnabled(false);

        //enabling the user to rotate the chart, default true
        pieChart.setRotationEnabled(true);
        //adding friction when rotating the pie chart
        pieChart.setDragDecelerationFrictionCoef(0.9f);
        //setting the first entry start from right hand side, default starting from top
        pieChart.setRotationAngle(0);

        //highlight the entry when it is tapped, default true if not set
        pieChart.setHighlightPerTapEnabled(true);
        //adding animation so the entries pop up from 0 degree
        pieChart.animateY(1500, Easing.EaseInOutQuad);

    }
        public void onDestroyView(){
            super.onDestroyView();
            dbManager.close();
            binding = null;
        }

    public void getData(View v) {
        expenseArr.clear();
        totalExpense = 0.0;
        totalIncome = 0.0;

        Cursor c = dbManager.fetchExpense();
        if (c != null && c.moveToFirst()) {

            while(!c.isAfterLast()) {
                String[] expense = new String[4];
                expense[0] = c.getString(0);

                expense[1] = c.getString(1);

                expense[2] = c.getString(2);
                expense[3] = c.getString(3);
                Log.d("",String.format("%5d %10s %7.2f\n %10s",
                        c.getInt(0),c.getString(1),c.getDouble(2),c.getString(3))
                );

                expenseArr.add(expense);
                c.moveToNext();
            }
        }

        incomeArr.clear();
        // move this to a RecyclerView
        Cursor cs = dbManager.fetchIncome();
        if (cs != null && cs.moveToFirst()) {

            while(!cs.isAfterLast()) {

                String[] income = new String[4];
                income[0] = cs.getString(0);
                income[1] = cs.getString(1);
                income[2] = cs.getString(2);
                income[3] = cs.getString(3);

                Log.d("",String.format("%5d %10s %7.2f\n %10s",
                        cs.getInt(0),cs.getString(1),cs.getDouble(2),cs.getString(3))
                );

                incomeArr.add(income);
                cs.moveToNext();
            }
        }

        //get total income and expense
        //Balance is not recorded , View v is never used!!!
        for (String [] strArr : expenseArr) {
            totalExpense += Double.parseDouble(strArr[2]);

        }
        for (String [] strArr : incomeArr) {
            totalIncome += Double.parseDouble(strArr[2]);

        }
        balance = totalIncome - totalExpense;
        pieChart.notifyDataSetChanged();
        pieChart.invalidate();
    }
}
