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
import com.example.carpoolbuddy.Models.Student;

import com.example.carpoolbuddy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class AuthActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    private EditText emailField;
    private EditText passwordField;

    private LinearLayout layout;
    private EditText nameField;
    private Spinner userRoleSpinner;
    private String selectedRole;

    //alumni
    private EditText gradYearField;

    private String uid;
    private static int uidGenerator = 1;

    //student
    private EditText studentGrade;
    private String id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_activity);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        layout = findViewById(R.id.linearLayoutAuth);
        userRoleSpinner = findViewById(R.id.spinnerAuth);
        setupSpinner();

        id = "" + uidGenerator;

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


        mAuth.signInWithEmailAndPassword(emailString, passwordString)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("SIGN UP", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                            Intent nextScreen = new Intent(getBaseContext(), MainMenu.class);
                            startActivity(nextScreen);


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("SIGN UP", "signInWithEmail:failure", task.getException());
                            Toast.makeText(AuthActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });



      //  System.out.println(String.format("Email and Passsword is", emailString, passwordString));

     //   FirebaseUser mUser = mAuth.getCurrentUser();





      //  UUID.randomUUID().toString();



    }



    public void signUp(View v){


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
            Alumni newUser = new Alumni(uidGenerator, nameString, emailString, gradYearInt);
            firestore.collection("people").document(String.valueOf(uidGenerator)).set(newUser);
            uidGenerator++;
        }


        if(selectedRole.equals("Student")) {
            int studentGradeInt = Integer.parseInt(studentGrade.getText().toString());
            Student newUser = new Student(uidGenerator, emailString, nameString, studentGradeInt);
            firestore.collection("people").document(String.valueOf(uidGenerator+1)).set(newUser);
        }
        
        
        

    }

    //WHY DOES SIGN UP MAKE YOU MOVE TO NEXT SCREEN???

    public void updateUI(FirebaseUser currentUser){

        if(currentUser != null){
            Intent intent = new Intent(this, MainMenu.class);
            startActivity(intent);
        }


    }



}