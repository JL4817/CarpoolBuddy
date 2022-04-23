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

import com.example.carpoolbuddy.Controllers.AfterLogIn;

import com.example.carpoolbuddy.Models.Alumni;
import com.example.carpoolbuddy.Models.Student;
import com.example.carpoolbuddy.Models.Vehicle;
import com.example.carpoolbuddy.Models.User;

import com.example.carpoolbuddy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;

public class AuthActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    private EditText emailField;
    private EditText passwordField;

    //aluminai
    private LinearLayout layout;
    private EditText nameField;
    private EditText gradYearField;
    private Spinner userRoleSpinner;
    private String selectedRole;
    private String uid;
    private static int uidGenerator = 1;

    //student
    private EditText studentGrade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_activity);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        layout = findViewById(R.id.linearLayoutAuth);
        userRoleSpinner = findViewById(R.id.spinnerAuth);
        setupSpinner();
        uid = "" + uidGenerator;
        uidGenerator++;
    }





    // setup spinner where user selects what user type they want to make an account for
    private void setupSpinner() {
        String[] userTypes = {"Student", "Teacher", "Alumni", "Parent"};
        // add user types to spinner
        ArrayAdapter<String> langArrAdapter = new ArrayAdapter<String>(AuthActivity.this,
                android.R.layout.simple_spinner_item, userTypes);
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
        if(selectedRole.equals("Alumni")) {
            gradYearField = new EditText(this);
            gradYearField.setHint("Graduation year");
            layout.addView(gradYearField);
        }

        if(selectedRole.equals("Student")) {
            studentGrade = new EditText(this);
            studentGrade.setHint("Student Grade");
            layout.addView(studentGrade);
        }


    }


    public void commonFields() {
        layout.removeAllViewsInLayout();
        nameField = new EditText(this);
        nameField.setHint("Name");
        layout.addView(nameField);
        emailField = new EditText(this);
        emailField.setHint("Email");
        layout.addView(emailField);
        passwordField = new EditText(this);
        passwordField.setHint("Password");
        layout.addView(passwordField);
    }



    public void signIn(View v){

        String emailString = emailField.getText().toString();
        String passwordString = passwordField.getText().toString();

        System.out.println(String.format("Email and Passsword is", emailString, passwordString));

     //   FirebaseUser mUser = mAuth.getCurrentUser();

        Intent nextScreen = new Intent(getBaseContext(), AfterLogIn.class);
        startActivity(nextScreen);


     //   Vehicle por = new Vehicle("america", "BMW", 4, "5", 1);
      //  UUID.randomUUID().toString();
        //firestore.collection("itmes").document("hello").collection("cars").document("hi").set(por);



    }

    public void signUp(View v){
        System.out.println("BYE");

        String nameString = nameField.getText().toString();
        String emailString = emailField.getText().toString();
        String passwordString = passwordField.getText().toString();

        mAuth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d("Sign Up", "Succesfully signed up the user");

                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);

                }else{
                    Log.w("Sign up", "createuserWIthEmailFailure", task.getException());
                    updateUI(null);
                }
            }
        });

        if(selectedRole.equals("Alumni")) {
            int gradYearInt = Integer.parseInt(gradYearField.getText().toString());
            Alumni newUser = new Alumni(uid, nameString, emailString, gradYearInt);
            uidGenerator++;
            firestore.collection("people").document(uid).set(newUser);
        }


        if(selectedRole.equals("Student")) {
            int studentGradeInt = Integer.parseInt(studentGrade.getText().toString());
            Student newUser = new Student(emailString, nameString, passwordString, studentGradeInt);
            firestore.collection("people").document(uid+1).set(newUser);
        }
        
        
        

    }

    public void updateUI(FirebaseUser currentUser){

        if(currentUser != null){
            Intent intent = new Intent(this, AfterLogIn.class);
            startActivity(intent);
        }


    }



}