package com.example.carpoolbuddy.Controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.example.carpoolbuddy.Models.ElectricCar;
import com.example.carpoolbuddy.Models.Plane;
import com.example.carpoolbuddy.Models.RV;
import com.example.carpoolbuddy.Models.SportsCar;
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
import java.util.List;

public class VehiclesInfoActivity extends AppCompatActivity implements RecHolder.ItemClickListener{

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private ArrayList<Vehicle> vehiclesList;
    private RecyclerView recView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_lists);

        context = this;
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
                        if(document.get("type").equals(Constants.V_ELECTRICCAR)) {
                            vehiclesList.add(document.toObject(ElectricCar.class));
                        }
                        if(document.get("type").equals(Constants.V_PLANE)) {
                            vehiclesList.add(document.toObject(Plane.class));
                        }
                        if(document.get("type").equals(Constants.V_RV)) {
                            vehiclesList.add(document.toObject(RV.class));
                        }
                        if(document.get("type").equals(Constants.V_SPORTSCAR)) {
                            vehiclesList.add(document.toObject(SportsCar.class));
                        }

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


                RecAdapter myAdapter = new RecAdapter(vehiclesList ,new RecHolder.ItemClickListener() {

                    @Override
                    public void onItemClick(ArrayList<Vehicle> details, int position) {
                        //  showToast(details.getLocation()+"CLICKED");


                        Intent i = new Intent(context, RecyclerViewClick.class);
                        i.putExtra("vehicleList", vehiclesList);
                        i.putExtra("vehiclePos", position);
                        startActivity(i);



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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onItemClick(ArrayList<Vehicle> details, int position) {

    }
}