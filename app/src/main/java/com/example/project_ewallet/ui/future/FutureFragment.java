package com.example.project_ewallet.ui.future;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.project_ewallet.R;
import com.example.project_ewallet.databinding.FragmentBudgetBinding;
import com.github.mikephil.charting.charts.PieChart;


public class FutureFragment extends Fragment {

    PieChart pieChart;
        private FragmentBudgetBinding binding;


        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {


            binding = FragmentBudgetBinding.inflate(inflater, container, false);
            View root = binding.getRoot();
            pieChart = (PieChart) getView().findViewById(R.id.chart);


            return root;
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }


    }
