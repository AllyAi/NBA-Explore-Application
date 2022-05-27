package com.example.nbaexploreapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginRegister extends AppCompatActivity implements View.OnClickListener { //implement on click listener into the class

    private TextView textView;
    private EditText editTextEmail, editTextPassword;
    private Button signIn;
    private FirebaseAuth mAuth;                       //Declare variables as private only to be used in this class
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_register);  //use the login_register xml file as the layout

        signIn = findViewById(R.id.signIn);        //initialise the signIn button
        signIn.setOnClickListener(this);          //and set an click listener to it

        textView = findViewById(R.id.Register);   //initialise the register button
        textView.setOnClickListener(this);        //and set an on click listener to it

        editTextEmail = findViewById(R.id.EmailAddress);
        editTextEmail.setOnClickListener(this);

        editTextPassword = findViewById(R.id.TextPassword);
        editTextPassword.setOnClickListener(this);

        progressBar = findViewById(R.id.progressBar);  //initialise progress bar widget

        mAuth = FirebaseAuth.getInstance();  //initialise firebase authentication


    }
    @Override
    public void onClick(View v) { //onClick method is triggered when a click is detected by the on click listener
        switch (v.getId()){   //switch between different cases depending on this id of the button pressed
            case R.id.Register:
                startActivity(new Intent(LoginRegister.this, RegisterActivity.class)); //when the register button is pressed, start the Registration Activity

            case R.id.signIn:
                userLogin(); //when the sign in button is pressed, call the userLogin method
                break;
        }
    }

    private void userLogin() { //method is called when the sign in button is pressed
        String email = editTextEmail.getText().toString().trim();  //variables to store email and password when user parses them through, convert to string and trim.
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()){
            editTextEmail.setError("Please fill in this field");  //if the email field is empty, output the error message
            editTextEmail.requestFocus();                         //and ask user for another attempt
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid email");  //if an invalid email is entered, output the prompt
            editTextEmail.requestFocus();                          //ask the user for another attempt
            return;
        }

        if (password.isEmpty()){
            editTextPassword.setError("Please fill in this field");  //if the password field is empty, output the error message
            editTextPassword.requestFocus();                         //ask the user for another attempt
            return;
        }

        if (password.length() < 6){                               //if the length of the password is less than 6
            editTextPassword.setError("invalid password");        // output the error message
            editTextPassword.requestFocus();                      //ask the user for another attempt
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() { //check if firebase has successfully signed in the user
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) { //when the task is complete
                if(task.isSuccessful()){                             //and successful
                    startActivity(new Intent(LoginRegister.this, WelcomeActivity.class)); //take the user to the Welcome activity (home page)

                }else{   //if the sign in is unsuccessful
                    Toast.makeText(LoginRegister.this, "Failed to login, please ensure correct credentials", Toast.LENGTH_LONG).show(); //output error message
                }
            }
        });



    }
}