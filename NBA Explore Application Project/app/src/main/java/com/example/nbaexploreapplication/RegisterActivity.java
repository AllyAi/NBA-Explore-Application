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
    private Button button;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        mAuth = FirebaseAuth.getInstance();

        button = findViewById(R.id.Register);
        button.setOnClickListener(this);

        FirstName = findViewById(R.id.FirstName);
        LastName = findViewById(R.id.LastName);
        EmailAddress = findViewById(R.id.EmailAddress);
        TextPassword = findViewById(R.id.TextPassword);


        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Register:
                registerUser();
                break;
        }
    }

    public void newTask(){ //takes the user to the log in page, method is called after successful registration.
        startActivity(new Intent(this, LoginRegister.class));
    }

    private void registerUser(){
        String firstName = FirstName.getText().toString().trim();
        String lastName = LastName.getText().toString().trim();
        String email = EmailAddress.getText().toString().trim();
        String password = TextPassword.getText().toString().trim();

        if(firstName.isEmpty()){
            FirstName.setError("This field is required to be filled.");
            FirstName.requestFocus();
            return;
        }

        else if(lastName.isEmpty()){
            LastName.setError("This field is required to be filled.");
            LastName.requestFocus();
            return;
        }

        else if(email.isEmpty()){
            EmailAddress.setError("This field is required to be filled.");
            EmailAddress.requestFocus();
            return;
        }

        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            EmailAddress.setError("Please enter a valid email!");
            EmailAddress.requestFocus();
            return;
        }

        else if(password.isEmpty()){
            TextPassword.setError("This field is required to be filled.");
            TextPassword.requestFocus();
            return;
        }

        else if(password.length() < 6){
            TextPassword.setError("This field is required to be filled.");
            TextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(firstName, lastName, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)  {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "You have Successfully registered an Account.", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        newTask();
                                    }
                                    else{
                                        Toast.makeText(RegisterActivity.this, "An error occured, please try again.", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);

                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "Failed to register, please try again.", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}