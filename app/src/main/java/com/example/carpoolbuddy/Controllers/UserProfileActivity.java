package com.example.carpoolbuddy.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.carpoolbuddy.R;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }


    public void toTheVehicles(View v){
        Intent intent = new Intent(this, VehiclesInfoActivity.class);
        startActivity(intent);
    }
}