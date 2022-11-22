package com.example.project_ewallet.ui.future;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.project_ewallet.DBManager;
import com.example.project_ewallet.R;
import com.example.project_ewallet.databinding.FragmentFutureBinding;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FutureFragment extends Fragment {


        private FragmentFutureBinding binding;
        private PieChart pieChart;
        private DBManager dbManager;
        private Map<String, Double> typeAmountMap = new HashMap<>();
        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {


            binding = FragmentFutureBinding.inflate(inflater, container, false);

            View root = binding.getRoot();
            pieChart = (PieChart) root.findViewById(R.id.chart);
            dbManager = new DBManager(this.getActivity());
            dbManager.open();
            initPieChart();
            showPieChart();

            return root;
        }

    private void getData(){
            Cursor c = dbManager.fetchExpense();
            if (c.getCount() > 0){
                c.moveToFirst();
                while(!c.isLast()){
                    typeAmountMap.put(c.getString(3),c.getDouble(1));
                    c.moveToNext();

                }
            }
    }


    private void showPieChart(){

        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        String label = "type";

        //initializing data

        getData();

        //input data and fit data into pie chart entry
        for(String type: typeAmountMap.keySet()){
            pieEntries.add(new PieEntry(typeAmountMap.get(type).floatValue(), type));
        }

        //collecting the entries with label name
        PieDataSet pieDataSet = new PieDataSet(pieEntries,label);
        //setting text size of the value
        pieDataSet.setValueTextSize(12f);
        //grouping the data set from entry to chart
        PieData pieData = new PieData(pieDataSet);
        //showing the value of the entries, default true if not set
        pieData.setDrawValues(true);

        pieChart.setData(pieData);
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
        pieChart.animateY(1400, Easing.EaseInOutQuad);


    }
        public void onDestroyView(){
            super.onDestroyView();
            binding = null;
        }


    }
