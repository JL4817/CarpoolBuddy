package com.example.carpoolbuddy.Controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
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

public class CreateVehicle extends AppCompatActivity {


    private FirebaseFirestore firestore;


    //vehicle common
    private EditText location;
    private EditText model;
    private EditText capacity;
    private EditText price;

    private LinearLayout layout;
    private Spinner userRoleSpinner;
    private String selectedRole;

    //Plane
    private EditText aircraftSize;

    //RV
    private EditText nrOfRoomsAd;

    //sports car
    private EditText maxSpeedPossible;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_vehicle);

        firestore = FirebaseFirestore.getInstance();

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
       // if(selectedRole.equals("Electric Car")) {
        //    gradYearField = new EditText(this);
        //    gradYearField.setHint("Graduation year");
       //     layout.addView(gradYearField);
       // }

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
            maxSpeedPossible.setHint("Teacher Age");
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
        capacity.setHint("Password");
        layout.addView(capacity);
        price = new EditText(this);
        price.setHint("Password");
        layout.addView(price);

    }



    public void createVehicleB(View v){

        //generate + get new key
        DocumentReference newSignUpKey = firestore.collection(Constants.VEHICLE_COLLECTION).document();
        String vehicleKey = newSignUpKey.getId();

        //make new user according to selected usertype
        Vehicle newVehicle = null;

        String locationPlace = location.getText().toString();
        String modelName = model.getText().toString();
        int spaces = Integer.parseInt(capacity.getText().toString());
        int theCost = Integer.parseInt(price.getText().toString());


        if(selectedRole.equals(Constants.V_ELECTRICCAR)) {
          //  int gradYearInt = Integer.parseInt(gradYearField.getText().toString());
            newVehicle = new ElectricCar(locationPlace, modelName, spaces, theCost);

        }
        else if(selectedRole.equals(Constants.V_PLANE)) {
            int planeSize= Integer.parseInt(aircraftSize.getText().toString());
            newVehicle = new Plane(locationPlace, modelName, spaces, theCost, planeSize);

        }
        else if(selectedRole.equals(Constants.V_RV)) {
            int nrOfRooms = Integer.parseInt(nrOfRoomsAd.getText().toString());
            newVehicle = new RV(locationPlace, modelName, spaces, theCost, nrOfRooms);

        }
        else if(selectedRole.equals(Constants.V_SPORTSCAR)) {
            int maxSpeed = Integer.parseInt(maxSpeedPossible.getText().toString());
            newVehicle = new SportsCar(locationPlace, modelName, spaces, theCost, maxSpeed);
        }


        //add the new user to the database
        newSignUpKey.set(newVehicle);

    }




}