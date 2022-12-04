package com.example.project_ewallet;

import android.content.Intent;
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
        holder.txtDate.setText(item[1]);
        holder.txtAmount.setText(item[2]);
        holder.txtCat.setText(item[3]);

        //Pass data to UpdateDelete Activity
        holder.txtCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), UpdateDeleteActivity.class);
//                holder.getAdapterPosition();
                i.putExtra("id", item[0]);
                i.putExtra("date", holder.txtDate.getText().toString());
                i.putExtra("amount",Double.parseDouble(holder.txtAmount.getText().toString()) );
                i.putExtra("category", holder.txtCat.getText().toString()  );
                if(i != null){
                    view.getContext().startActivity(i);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtCat;
        public TextView txtAmount;
        public TextView txtDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtCat = (TextView) itemView.findViewById(R.id.txtCat);
            this.txtAmount = (TextView) itemView.findViewById(R.id.txtAmount);
            this.txtDate = (TextView) itemView.findViewById(R.id.txtDate);
        }
    }
}
