package com.example.carpoolbuddy.Controllers;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpoolbuddy.R;

public class RecHolder extends RecyclerView.ViewHolder {

        protected TextView location;
        protected TextView model;
        protected TextView capacity;
        protected TextView price;


        public RecHolder(@NonNull View itemView) {
            super(itemView);

            location = itemView.findViewById(R.id.locationTextView);
            model = itemView.findViewById(R.id.modelTextView);
            capacity = itemView.findViewById(R.id.capacityTextView);
            price = itemView.findViewById(R.id.priceTextView);


        }

}
