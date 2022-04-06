package com.example.carpoolbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class AuthActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    private EditText emailField;
    private EditText passwordField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_activity);

        //mAuth = FirebaseAuth.getInstance();
      //  firestore = FirebaseFirestore.getInstance();

        //emailField = findViewById(R.id.logInEmail);
        //passwordField = findViewById(R.id.logInPass);
    }


    public void signIn(View v){
        System.out.print("HELLO");
        System.out.print("HEasdasdasdasdasLLO");
    }

    public void signUp(View v){
        System.out.print("HEasdaasasasasasdsdasdasdasLLO");
    }



}