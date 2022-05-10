package com.example.carpoolbuddy.Controllers;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpoolbuddy.Models.Vehicle;
import com.example.carpoolbuddy.R;

import java.util.ArrayList;

public class RecHolder extends RecyclerView.ViewHolder {

        protected TextView location;
        protected TextView model;
        protected TextView capacity;
        protected TextView price;
        protected TextView type;


        public RecHolder(@NonNull View itemView) {
            super(itemView);

            location = itemView.findViewById(R.id.locationTextView);
            model = itemView.findViewById(R.id.modelTextView);
            capacity = itemView.findViewById(R.id.capacityTextView);
            price = itemView.findViewById(R.id.priceTextView);
            type = itemView.findViewById(R.id.typeTextView);

        }

    public interface ItemClickListener{
        void onItemClick(ArrayList<Vehicle> details, int position);
    }


//      if(getIntent().hasExtra("selected_vehicle")){
//        Vehicle vehicle = getIntent().getParcelableExtra("selected_vehicle");
//    }

}
