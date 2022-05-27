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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText FirstName, LastName, EmailAddress, TextPassword;
    private Button button;                   //declare the variables as private only to be used in this class
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;            //declare variable for firebase authentication

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);  //use the register layout xml file

        mAuth = FirebaseAuth.getInstance();  //store the instance of firebase authentication in the mAuth variable

        button = findViewById(R.id.Register);  //initialise register button
        button.setOnClickListener(this);       //set an on click listener to the button

        FirstName = findViewById(R.id.FirstName);        //initialise firstname variable
        LastName = findViewById(R.id.LastName);          //initialise lastname variable
        EmailAddress = findViewById(R.id.EmailAddress);  //initialise emailaddress variable
        TextPassword = findViewById(R.id.TextPassword);  //initialise password variable


        progressBar = findViewById(R.id.progressBar);   //initialise the progress bar
    }

    @Override
    public void onClick(View v) {  //this method called when a button is clicked
        switch (v.getId()){      //depending on the id of the button, execute different cases
            case R.id.Register:  //if the id of the button is register
                registerUser();  //call the register user method
                break;           //break off from the rest of the code
        }
    }

    public void newTask(){ //takes the user to the log in page, method is called after successful registration.
        startActivity(new Intent(this, LoginRegister.class));
    }

    private void registerUser(){  //register user method
        String firstName = FirstName.getText().toString().trim();    //store inputed user info
        String lastName = LastName.getText().toString().trim();     //in each of the variables
        String email = EmailAddress.getText().toString().trim();    //get the text, convert it to string and trim unnecessary info
        String password = TextPassword.getText().toString().trim();

        if(firstName.isEmpty()){  //if the first name field is empty
            FirstName.setError("This field is required to be filled."); //output error
            FirstName.requestFocus(); //get user to attempt again
            return;  //return the contents extracted from the user
        }

        else if(lastName.isEmpty()){ //if the last name field is empty
            LastName.setError("This field is required to be filled."); //output error
            LastName.requestFocus(); //get user to attempt again
            return; //return the contents extracted from the user
        }

        else if(email.isEmpty()){ //if the email field is empty
            EmailAddress.setError("This field is required to be filled."); //output error
            EmailAddress.requestFocus(); //get user to attempt again
            return; //return the contents extracted from the user
        }

        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){ //if the email field contains forbidden characters
            EmailAddress.setError("Please enter a valid email!"); //output error
            EmailAddress.requestFocus(); //get user to attempt again
            return; //return the contents extracted from the user
        }

        else if(password.isEmpty()){  //if the password field is empty
            TextPassword.setError("This field is required to be filled."); //output error
            TextPassword.requestFocus(); //get user to attempt again
            return; //return the contents extracted from the user
        }

        else if(password.length() < 6){ //if the password length is less than 6
            TextPassword.setError("This field is required to be filled."); //output error
            TextPassword.requestFocus(); //get user to attempt again
            return; //return the contents extracted from the user
        }

        progressBar.setVisibility(View.VISIBLE); //set the visibility of the progressbar to visible by default to simulate loading
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() { //add on complete listener to method
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) { //when the task is complete
                        if(task.isSuccessful()){                            //and is successful
                            User user = new User(firstName, lastName, email); //add the new info to the user

                            FirebaseDatabase.getInstance().getReference("Users")  //get the users from the firebase database
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()) //get the current user and their id
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() { //set the information as collected by the information
                                @Override
                                public void onComplete(@NonNull Task<Void> task)  {  //when the task is complete
                                    if(task.isSuccessful()){                       //and is successful
                                        Toast.makeText(RegisterActivity.this, "You have Successfully registered an Account.", Toast.LENGTH_LONG).show(); //output message
                                        progressBar.setVisibility(View.GONE);  //change the visibility status of the progress bar to gone to simulate completed task
                                        newTask(); //execute the new task method which starts the login register activity
                                    }
                                    else{ //else if the task is unsuccessful
                                        Toast.makeText(RegisterActivity.this, "An error occured, please try again.", Toast.LENGTH_LONG).show(); //output message
                                        progressBar.setVisibility(View.GONE);  //change the visibility of the status of the progress bar to gone

                                    }
                                }
                            });
                        }
                        else{ //if the task isn't complete
                            Toast.makeText(RegisterActivity.this, "Failed to register, please try again.", Toast.LENGTH_LONG).show();  //output message
                            progressBar.setVisibility(View.GONE);  //change the visibility of the status of the progress bar to gone
                        }
                    }
                });
    }
}