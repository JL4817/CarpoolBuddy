package com.example.carpoolbuddy.Controllers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpoolbuddy.Models.Vehicle;
import com.example.carpoolbuddy.R;

import java.util.ArrayList;

public class RecAdapter extends RecyclerView.Adapter<RecHolder>{

    ArrayList<Vehicle> vehiclesList;
    private ItemClickListener mItemListener;

    public RecAdapter(ArrayList vehiclesList, ItemClickListener itemClickListener){
        this.vehiclesList = vehiclesList;
        this.mItemListener = itemClickListener;
    }


    @NonNull
    @Override
    public RecHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_row_view, parent, false);

        RecHolder holder = new RecHolder(myView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecHolder holder, final int position) {
        holder.location.setText(vehiclesList.get(position).getLocation());
        holder.model.setText(vehiclesList.get(position).getModel());
        holder.capacity.setText(String.valueOf(vehiclesList.get(position).getCapacity()));
        holder.price.setText(String.valueOf(vehiclesList.get(position).getPrice()));

        holder.itemView.setOnClickListener(view -> {
            mItemListener.onItemClick(vehiclesList.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return vehiclesList.size();
    }


    public interface ItemClickListener{
        void onItemClick(Vehicle details);
    }



}
