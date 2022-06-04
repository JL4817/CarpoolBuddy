package com.example.carpoolbuddy.Controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.carpoolbuddy.Models.Alumni;
import com.example.carpoolbuddy.Models.ElectricCar;
import com.example.carpoolbuddy.Models.Plane;
import com.example.carpoolbuddy.Models.RV;
import com.example.carpoolbuddy.Models.SportsCar;
import com.example.carpoolbuddy.Models.Student;
import com.example.carpoolbuddy.Models.Teacher;
import com.example.carpoolbuddy.Models.User;
import com.example.carpoolbuddy.Models.Vehicle;
import com.example.carpoolbuddy.R;
import com.example.carpoolbuddy.Utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class CreateVehicle extends AppCompatActivity {

    private FirebaseFirestore firestore;

    //vehicle common
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private EditText location;
    private EditText model;
    private EditText capacity;
    private EditText price;
    private Switch open;
    private EditText type;

    private LinearLayout layout;
    private Spinner userRoleSpinner;
    private String selectedRole;

    //electric cars
    private EditText battSize;

    //Plane
    private EditText aircraftSize;

    //RV
    private EditText nrOfRoomsAd;

    //sports car
    private EditText maxSpeedPossible;


    ArrayList<String> ownedVehicles;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_vehicle);
        firestore = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        layout = findViewById(R.id.linearLayoutVehicle);
        userRoleSpinner = findViewById(R.id.spinnerVehicle);
        setupSpinner();

    }




    // setup spinner where user selects what user type they want to make an account for
    private void setupSpinner() {
        String[] vehicleTypes = {Constants.V_ELECTRICCAR, Constants.V_PLANE, Constants.V_RV, Constants.V_SPORTSCAR};
        // add user types to spinner
        ArrayAdapter<String> langArrAdapter = new ArrayAdapter<String>(CreateVehicle.this,
                android.R.layout.simple_spinner_item, vehicleTypes);
        langArrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userRoleSpinner.setAdapter(langArrAdapter);

        //triggered whenever user selects something different
        userRoleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                selectedRole = parent.getItemAtPosition(position).toString();
                addFields();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }




    public void addFields() {
        commonFields();
        if(selectedRole.equals(Constants.V_ELECTRICCAR)) {
            battSize = new EditText(this);
            battSize.setHint("Battery Size");
            layout.addView(battSize);
        }

        if(selectedRole.equals("Plane")) {
            aircraftSize = new EditText(this);
            aircraftSize.setHint("Plane Size");
            layout.addView(aircraftSize);
        }


        if(selectedRole.equals("RV")) {
            nrOfRoomsAd = new EditText(this);
            nrOfRoomsAd.setHint("Number of Roonms");
            layout.addView(nrOfRoomsAd);
        }

        if(selectedRole.equals("Sports Car")) {
            maxSpeedPossible = new EditText(this);
            maxSpeedPossible.setHint("Max Speed");
            layout.addView(maxSpeedPossible);
        }



    }


    public void commonFields() {

        layout.removeAllViewsInLayout();
        location = new EditText(this);
        location.setHint("Location");
        layout.addView(location);
        model = new EditText(this);
        model.setHint("Model");
        layout.addView(model);
        capacity = new EditText(this);
        capacity.setHint("Capacity");
        layout.addView(capacity);
        price = new EditText(this);
        price.setHint("Price");
        layout.addView(price);
        open = new Switch(this);
        open.setHint("Check if Open");
        layout.addView(open);
        type = new EditText(this);
        type.setHint("Write the Type:");
        layout.addView(type);

    }



    public void createVehicleB(View v){

        //generate + get new key
        DocumentReference newSignUpKey = firestore.collection(Constants.VEHICLE_COLLECTION).document();
        String vehicleKey = newSignUpKey.getId();

        //user ID
        String userID = mUser.getUid();

        //make new user according to selected usertype
        Vehicle newVehicle = null;

        String locationPlace = location.getText().toString();
        String modelName = model.getText().toString();
        int spaces = Integer.parseInt(capacity.getText().toString());
        int theCost = Integer.parseInt(price.getText().toString());
        String typeV = type.getText().toString();


      //  boolean openH = Boolean.parseBoolean(open.getText().toString());

        Boolean checked = open.isChecked();


        if(selectedRole.equals(Constants.V_ELECTRICCAR)) {
            int batterySizer = Integer.parseInt(battSize.getText().toString());
            newVehicle = new ElectricCar(locationPlace, modelName, spaces, theCost, checked, typeV, vehicleKey, batterySizer, userID);

          //  String id = mAuth.getCurrentUser().getUid();
         //   ownedVehicles.add(id);


        }
        else if(selectedRole.equals(Constants.V_PLANE)) {
            int planeSize= Integer.parseInt(aircraftSize.getText().toString());
            newVehicle = new Plane(locationPlace, modelName, spaces, theCost, checked, typeV, vehicleKey, planeSize, userID);

        }
        else if(selectedRole.equals(Constants.V_RV)) {
            int nrOfRooms = Integer.parseInt(nrOfRoomsAd.getText().toString());
            newVehicle = new RV(locationPlace, modelName, spaces, theCost, checked, typeV, vehicleKey, nrOfRooms, userID);

        }
        else if(selectedRole.equals(Constants.V_SPORTSCAR)) {
            int maxSpeed = Integer.parseInt(maxSpeedPossible.getText().toString());
            newVehicle = new SportsCar(locationPlace, modelName, spaces, theCost, checked, typeV, vehicleKey, maxSpeed, userID);
        }


        //add the new vehicle to the database
        newSignUpKey.set(newVehicle);

    }


    public void toTheBack(View v){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }



}