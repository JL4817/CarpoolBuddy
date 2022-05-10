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
    private int position;
    private Vehicle vehicle;

    private ArrayList<Vehicle> vehicleList;
    private Vehicle selectedVehicle;
    private LinearLayout layout;
    private TextView battSize;

    private TextView carMaxCapacityTextView;
    private TextView carRemainingCapacity;
    private TextView bookedUIDs;
    private Button buttonReservedRide;




    //   ArrayList<Vehicle> vehiclesList = ;

  //  private ArrayList<Vehicle> vehiclesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        if(getIntent().hasExtra("vehicleList") & getIntent().hasExtra("pos")){
            vehicleList = (ArrayList<Vehicle>) getIntent().getSerializableExtra("vehicleList");
            position = (int) getIntent().getSerializableExtra("vehiclePos");

            if (vehicleList.get(position).getType().equals(Constants.V_ELECTRICCAR)) {
                ElectricCar electricCar = (ElectricCar) vehicleList.get(position);
                System.out.println(electricCar.getBatterySize());

                battSize = new EditText(this);
                battSize.setHint("Battery Size");
                layout.addView(battSize);

            } else if(vehicleList.get(position).getType().equals(Constants.V_SPORTSCAR)) {
                SportsCar sportsCar = (SportsCar) vehicleList.get(position);
             //   System.out.println(sportsCar.());
            }

            lo = vehicle.getLocation();
            pr = String.valueOf(vehicle.getPrice());
            mo = vehicle.getModel();
            ty = vehicle.getType();

            vehicle.getModel();

            location.setText("Location"+lo);
            price.setText("Price"+pr);
            model.setText("Model"+mo);

            layout = findViewById(R.id.attribute);

            price = findViewById(R.id.priceN);
            model = findViewById(R.id.modelN);
            location = findViewById(R.id.locationN);


            carMaxCapacityTextView = findViewById(R.id.maxCa);
            carRemainingCapacity = findViewById(R.id.reCa);
            bookedUIDs = findViewById(R.id.bookedUID);

            carMaxCapacityTextView.setText(String.valueOf(selectedVehicle.getCapacity()));
            carRemainingCapacity.setText(String.valueOf(selectedVehicle.getRemainingCapacity()));
            bookedUIDs.setText(selectedVehicle.getReservedUIDs().toString());

        }


        buttonReservedRide = findViewById(R.id.carpool);
        buttonReservedRide.setOnClickListener(this);

         //   vehiclesList = (ArrayList<Vehicle>)getIntent().getSerializableExtra("vehicleList");
        //    position = (int)getIntent().getSerializableExtra("vehiclePosition");







    }






    public void book(){

        //close vihicle if urser took last seat avaliable
        if(selectedVehicle.getRemainingCapacity() == 1){
            firestore.collection("vehicles").document(selectedVehicle.getVehicleID())
                    .update("open", false);
        }
        //update capacity
        firestore.collection("vehicles").document(selectedVehicle.getVehicleID())
                .update("remainingCapacity", selectedVehicle.getRemainingCapacity() -1);

        //add user's uid to the list of reservedUIds
        selectedVehicle.setReservedUIDs(mAuth.getUid());
        firestore.collection("vehicles").document(selectedVehicle.getVehicleID())
                .update("reservedUids", selectedVehicle.getReservedUIDs())
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