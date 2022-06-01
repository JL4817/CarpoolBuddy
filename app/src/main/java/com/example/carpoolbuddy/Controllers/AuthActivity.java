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
import com.example.carpoolbuddy.Models.Parent;
import com.example.carpoolbuddy.Models.Student;

import com.example.carpoolbuddy.Models.Teacher;
import com.example.carpoolbuddy.Models.User;
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


    //student
    private EditText studentGrade;

    //teacher
    private EditText teacherAge;

    //parent
    private EditText nrOfKidsParent;

    private String userId;
    ArrayList<String> ownedVehicles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_activity);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        layout = findViewById(R.id.linearLayoutVehicle);
        userRoleSpinner = findViewById(R.id.spinnerAuth);
        setupSpinner();

    }




    // setup spinner where user selects what user type they want to make an account for
    private void setupSpinner() {
        String[] userTypes = {Constants.USER_STUDENT, Constants.USER_TEACHER, Constants.USER_ALUMNI, Constants.USER_PARENT};
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


        if(selectedRole.equals("Teacher")) {
            teacherAge = new EditText(this);
            teacherAge.setHint("Teacher Age");
            layout.addView(teacherAge);
        }

        if(selectedRole.equals("Parent")) {
            nrOfKidsParent = new EditText(this);
            nrOfKidsParent.setHint("Number of Kids");
            layout.addView(nrOfKidsParent);
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

      //  UUID.randomUUID().toString();



    }



    public void signUp(View v){

        //generate + get new key
      //  DocumentReference newSignUpKey = firestore.collection(Constants.PEOPLE_COLLECTION).document();
     //   String userKey = newSignUpKey.getId();



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
                    userId = user.getUid();
                    addUserToDatabase(emailString, nameString);

                }else{
                    Log.w("Sign up", "createuserWIthEmailFailure", task.getException());
                    updateUI(null);
                }
            }
        });


    }

    public void addUserToDatabase(String emailString, String nameString){

        //make new user according to selected usertype
        User newUser = null;


        if(selectedRole.equals(Constants.USER_ALUMNI)) {
            int gradYearInt = Integer.parseInt(gradYearField.getText().toString());
            newUser = new Alumni(userId, emailString, nameString, gradYearInt);

        }
        else if(selectedRole.equals(Constants.USER_STUDENT)) {
            int studentGradeInt = Integer.parseInt(studentGrade.getText().toString());
            newUser = new Student(userId, emailString, nameString, studentGradeInt);

        }
        else if(selectedRole.equals(Constants.USER_TEACHER)) {
            int teacherAgeInt = Integer.parseInt(teacherAge.getText().toString());
            newUser = new Teacher(userId, emailString, nameString, teacherAgeInt);

        }
        else if(selectedRole.equals(Constants.USER_PARENT)) {
            int nrKidsP = Integer.parseInt(nrOfKidsParent.getText().toString());
            newUser = new Parent(userId, emailString, nameString, nrKidsP);
        }

        //add the new user to the database
       // newSignUpKey.set(newUser);

        firestore.collection(Constants.PEOPLE_COLLECTION).document(userId).set(newUser);

    }


    public void updateUI(FirebaseUser currentUser){

        if(currentUser != null){
            Intent intent = new Intent(this, MainMenu.class);
            startActivity(intent);
        }


    }



}