package com.example.carpoolbuddy.Controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.carpoolbuddy.Models.ElectricCar;
import com.example.carpoolbuddy.Models.SportsCar;
import com.example.carpoolbuddy.Models.Vehicle;
import com.example.carpoolbuddy.R;
import com.example.carpoolbuddy.Utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewClick extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    private TextView location, price, model, type;
    private String lo, pr, mo, ty, bat;
   // private int position;
    private Vehicle vehicle;

    private ArrayList<Vehicle> vehicleList;
   // private Vehicle selectedVehicle;
    private LinearLayout layout;
    private TextView battSize;

    private TextView carMaxCapacityTextView;
    private TextView carRemainingCapacity;
    private TextView bookedUIDs;
    private Button buttonReservedRide;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        if(getIntent().hasExtra("vehicle")){

            vehicle = (Vehicle)getIntent().getParcelableExtra("vehicle");
         //   selectedVehicle = (Vehicle)getIntent().getSerializableExtra("selectedVehicle");
            vehicleList = (ArrayList<Vehicle>) getIntent().getSerializableExtra("vehicleList");
         //   position = (int) getIntent().getSerializableExtra("vehiclePos");


            //vehicle.getModel();

            layout = findViewById(R.id.mainLayout);
            price = findViewById(R.id.priceN);
            model = findViewById(R.id.modelN);
            location = findViewById(R.id.locationN);
          //  type = findViewById(R.id.locationN);

            carMaxCapacityTextView = findViewById(R.id.maxCa);
            carRemainingCapacity = findViewById(R.id.reCa);
            bookedUIDs = findViewById(R.id.bookedUID);

            lo = vehicle.getLocation();
            pr = String.valueOf(vehicle.getPrice());
            mo = vehicle.getModel();
        //    ty = vehicle.getType();

            carMaxCapacityTextView.setText(String.valueOf(vehicle.getCapacity()));
            carRemainingCapacity.setText(String.valueOf(vehicle.getRemainingCapacity()));
            bookedUIDs.setText(vehicle.getReservedUIDs().toString());

            location.setText("Location: "+lo);
            price.setText("Price: "+pr);

            model.setText("Model: "+mo);
          //  type.setText("Type of Car: "+ty);


            if (vehicle.getType().equals(Constants.V_ELECTRICCAR)) {
                ElectricCar electricCar = (ElectricCar) vehicle;
                System.out.println(electricCar.getBatterySize());

                battSize = new EditText(this);
                battSize.setHint("Battery Size");
                layout.addView(battSize);

            } else if(vehicle.getType().equals(Constants.V_SPORTSCAR)) {
                SportsCar sportsCar = (SportsCar) vehicle;
             //   System.out.println(sportsCar.());
            }

            //why do I need a vehicle id?
            //it suddenly doesn't work





        }




         //   vehiclesList = (ArrayList<Vehicle>)getIntent().getSerializableExtra("vehicleList");
        //    position = (int)getIntent().getSerializableExtra("vehiclePosition");


        buttonReservedRide = findViewById(R.id.carpool);
        buttonReservedRide.setOnClickListener(this);





    }






    public void book(){

        //close vihicle if urser took last seat avaliable
        if(vehicle.getRemainingCapacity() == 1){
            firestore.collection("vehicles").document(vehicle.getVehicleID())
                    .update("open", false);
        }
        //update capacity
        firestore.collection("vehicles").document(vehicle.getVehicleID())
                .update("remainingCapacity", vehicle.getRemainingCapacity() -1);

        //add user's uid to the list of reservedUIds
        vehicle.addReservedUIDs(mAuth.getUid());
        firestore.collection("vehicles").document(vehicle.getVehicleID())
                .update("reservedUids", vehicle.getReservedUIDs())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //go back to VehiclesInfoActivity
                        Intent intent = new Intent(getApplicationContext(), VehiclesInfoActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if(i==buttonReservedRide.getId()){
            book();
        }
    }
}