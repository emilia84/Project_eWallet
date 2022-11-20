package com.example.project_ewallet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder>{
    private ArrayList<String[]> list;
    public DetailAdapter(ArrayList<String[]> data){
        super();
        this.list = data;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String[] item = list.get(position);
        holder.txtCat.setText(item[0]);
        holder.txtSal.setText(item[1]);
        holder.txtDate.setText(item[2]);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtCat;
        public TextView txtSal;
        public TextView txtDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtCat = (TextView) itemView.findViewById(R.id.txtCat);
            this.txtSal = (TextView) itemView.findViewById(R.id.txtSal);
            this.txtDate = (TextView) itemView.findViewById(R.id.txtDate);
        }
    }
}
