package com.example.carpoolbuddy.Controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.carpoolbuddy.R;

public class MainMenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }


    public void createV(View v){

        Intent nextScreen = new Intent(getBaseContext(), CreateVehicle.class);
        startActivity(nextScreen);

    }


    public void toInfoShow(View v){
        Intent nextScreen = new Intent(getBaseContext(), VehiclesInfoActivity.class);
        startActivity(nextScreen);
    }



}