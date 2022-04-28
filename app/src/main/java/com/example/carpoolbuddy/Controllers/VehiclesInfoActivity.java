package com.example.carpoolbuddy.Controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.carpoolbuddy.Models.Vehicle;
import com.example.carpoolbuddy.R;
import com.example.carpoolbuddy.Utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class VehiclesInfoActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;


    private ArrayList<Vehicle> vehiclesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles_info);

        firestore = FirebaseFirestore.getInstance();


        vehiclesList = new ArrayList<Vehicle>();
    }

    public void ShowVL(View v){
        vehiclesList.clear();
        TaskCompletionSource<String> getAllRidesTask = new TaskCompletionSource<>();
        firestore.collection(Constants.VEHICLE_COLLECTION).whereEqualTo("open", true).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful() && task.getResult() != null){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        vehiclesList.add(document.toObject(Vehicle.class));
                    }
                    getAllRidesTask.setResult(null);
                }
                else{
                    Log.d("VehiclesInfoActivity", "Error getting comcumets from db: ", task.getException());
                }
            }
        });


        getAllRidesTask.getTask().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                System.out.println(vehiclesList.toString());
            }
        });

    }



}