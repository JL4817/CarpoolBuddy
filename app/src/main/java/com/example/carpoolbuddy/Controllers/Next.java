package com.example.carpoolbuddy.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.carpoolbuddy.Models.Vehicle;
import com.example.carpoolbuddy.R;

public class Next extends AppCompatActivity{

    private TextView location, price, capacity, model, sp;
    private String lo, pr, ca, mo, spN;
    private Vehicle vehicle;

    private EditText battSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        price = findViewById(R.id.priceN);
        capacity = findViewById(R.id.capacityN);
        model = findViewById(R.id.modelN);
        sp = findViewById(R.id.spN);
        location = findViewById(R.id.locationN);

        if(getIntent().hasExtra("selected_vehicle")){
            vehicle = getIntent().getParcelableExtra("selected_vehicle");
            vehicle.getModel();
        }

        lo = vehicle.getLocation();
        pr = String.valueOf(vehicle.getPrice());
        ca = String.valueOf(vehicle.getCapacity());
        mo = vehicle.getModel();
      // spN = vehicle.g();

        location.setText(lo);
        price.setText(pr);
        capacity.setText(ca);
        model.setText(mo);
        sp.setText(spN);

        if(vehicle.getModel().equals("Electric Car")){
            battSize = new EditText(this);
            battSize.setHint("Battery Size");
         //   layout.addView(battSize);
            System.out.println("HELLO SUCKER");
        }





    }








    public void book(View v){



    }








}