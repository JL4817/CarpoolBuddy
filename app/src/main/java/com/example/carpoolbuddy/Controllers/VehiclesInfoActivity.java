package com.example.carpoolbuddy.Controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

import java.io.IOException;
import java.util.ArrayList;

public class VehiclesInfoActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private ArrayList<Vehicle> vehiclesList;
    private RecyclerView recView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_lists);

        mAuth = FirebaseAuth.getInstance();
        recView = findViewById(R.id.recView);
        firestore = FirebaseFirestore.getInstance();
        vehiclesList =  new ArrayList<>();
        showVL();



        //  recView.setHasFixedSize(true);



    }



    public void showVL(){
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


                RecAdapter myAdapter = new RecAdapter(vehiclesList, new RecAdapter.ItemClickListener() {

                    @Override
                    public void onItemClick(Vehicle details) {
                        showToast(details.getLocation()+"CLICKED");
                    }
                });

                //System.out.println(vehiclesList.toString());
            //System.out.print("Vehicles list gotten from server" + vehiclesList);

                recView.setAdapter(myAdapter);
                recView.setLayoutManager(new LinearLayoutManager(VehiclesInfoActivity.this));

            }
        });

    }


     private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }



}