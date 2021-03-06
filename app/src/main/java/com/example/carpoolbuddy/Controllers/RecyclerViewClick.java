package com.example.carpoolbuddy.Controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.carpoolbuddy.Models.ElectricCar;
import com.example.carpoolbuddy.Models.Plane;
import com.example.carpoolbuddy.Models.RV;
import com.example.carpoolbuddy.Models.SportsCar;
import com.example.carpoolbuddy.Models.User;
import com.example.carpoolbuddy.Models.Vehicle;
import com.example.carpoolbuddy.R;
import com.example.carpoolbuddy.Utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class RecyclerViewClick extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseFirestore firestore;

    private TextView location, price, model, type;
    private String lo, pr, mo, ty, bat;
    private int position;
    private Vehicle selectedVehicle;

    private ArrayList<Vehicle> vehicleList;
    private LinearLayout layout;

    private TextView battSize;
    private TextView speed;
    private TextView nRoom;
    private TextView pSize;


    private TextView carMaxCapacityTextView;
    private TextView carRemainingCapacity;
    private TextView vehicleID;

    //for the owner alone
    private Button buttonReservedRide;
    private Button buttonCloseRide;

    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_click);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

     //  imageView = findViewById(R.id.imageView3);
    //    Glide.with(this).load("https://www.bmw.com/content/dam/bmw/marketBMWCOM/bmw_com/events/ces2022/eink/BMW_eink_01_mobile.jpg.asset.1641400104406.jpg").into(imageView);


        if(getIntent().hasExtra("vehicleList") && getIntent().hasExtra("vehiclePos")){

            vehicleList = (ArrayList<Vehicle>) getIntent().getSerializableExtra("vehicleList");
            position = (int) getIntent().getSerializableExtra("vehiclePos");


            //vehicle.getModel();
            selectedVehicle = vehicleList.get(position);
            layout = findViewById(R.id.mainLayout);
            price = findViewById(R.id.priceN);
            model = findViewById(R.id.modelN);
            location = findViewById(R.id.locationN);
            carMaxCapacityTextView = findViewById(R.id.maxCa);
            carRemainingCapacity = findViewById(R.id.reCa);
            vehicleID = findViewById(R.id.bookedUID);

            imageView = findViewById(R.id.imageView3);

            lo = selectedVehicle.getLocation();
            pr = String.valueOf(selectedVehicle.getPrice());
            mo = selectedVehicle.getModel();
            String book = selectedVehicle.getVehicleID();
            carMaxCapacityTextView.setText(String.valueOf("Maximum Capacity:"+ selectedVehicle.getCapacity()));
            carRemainingCapacity.setText(String.valueOf("Seats left: "+ selectedVehicle.getRemainingCapacity()));
            vehicleID.setText(selectedVehicle.getReservedUIDs().toString());
            location.setText("Location: "+lo);
            price.setText("Price: "+pr);
            model.setText("Model: "+mo);
            vehicleID.setText("Vehicle ID: "+book);

            //il is supposed to be the link, is there anywhere to get the string inside there
           String il = selectedVehicle.getImageLinks();
           Glide.with(this).load(il).into(imageView);


            if (vehicleList.get(position).getType().equals(Constants.V_ELECTRICCAR)) {
                ElectricCar electricCar = (ElectricCar) vehicleList.get(position);
             //   System.out.println(electricCar.getBatterySize());
                battSize = new TextView(this);
                battSize.setHint("Battery Size: "+electricCar.getBatterySize());
                layout.addView(battSize);

            } else if(vehicleList.get(position).getType().equals(Constants.V_SPORTSCAR)) {
                SportsCar sportsCar = (SportsCar) vehicleList.get(position);
                speed = new TextView(this);
                speed.setHint("Max Speed: "+sportsCar.getMaxSpeed());
                layout.addView(speed);

            }
            else if(vehicleList.get(position).getType().equals(Constants.V_RV)){
                RV rv = (RV) vehicleList.get(position);
                nRoom = new TextView(this);
                nRoom.setHint("Number of Rooms Avaliable: "+rv.getNrOfRooms());
                layout.addView(nRoom);

            }
            else if(vehicleList.get(position).getType().equals(Constants.V_PLANE)){
                Plane plane = (Plane) vehicleList.get(position);
                pSize = new TextView(this);
                pSize.setHint("Plane Size: "+plane.getPlaneSize());
                layout.addView(pSize);
            }


        }

        buttonReservedRide = findViewById(R.id.buttonReservedRide);
        buttonReservedRide.setOnClickListener(this);

        buttonCloseRide = findViewById(R.id.buttonCloseRide);
        buttonCloseRide.setOnClickListener(this);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        firestore.collection(Constants.PEOPLE_COLLECTION).document(mUser.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User tempUser = documentSnapshot.toObject(User.class);

                if(tempUser != null && tempUser.getOwnedVehicles().contains(selectedVehicle.getVehicleID())){
                    buttonCloseRide.setVisibility(View.VISIBLE);
                }
                else{
                    buttonReservedRide.setVisibility(View.VISIBLE);
                }

            }
        });


    }

//what are booked UID's, it doesn;t show in the layout
    //other vehicles except electric car doesn't show
    //rec click alos doens't work

    public void book(){

        //close vihicle if urser took last seat avaliable
        if(selectedVehicle.getRemainingCapacity() == 1){
            firestore.collection("vehicle").document(selectedVehicle.getVehicleID())
                    .update("open", false);
        }
        //update capacity
        System.out.println("HERE:");
        System.out.println(selectedVehicle.getRemainingCapacity());
        firestore.collection("vehicle").document(selectedVehicle.getVehicleID())
                .update("remainingCapacity", selectedVehicle.getRemainingCapacity() -1);

        //add user's uid to the list of reservedUIds
        selectedVehicle.addReservedUIDs(mAuth.getUid());
        firestore.collection("vehicle").document(selectedVehicle.getVehicleID())
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

    private void closeRide(){
        firestore.collection("vehicle").document(selectedVehicle.getVehicleID())
                .update("open", false);

        //go back to VehiclesInfoActivity
        Intent intent = new Intent(getApplicationContext(), VehiclesInfoActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if(i==buttonReservedRide.getId()){
            book();
        }
        else if(i == buttonCloseRide.getId()){
            closeRide();
        }
    }
}