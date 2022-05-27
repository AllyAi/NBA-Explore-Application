package com.example.nbaexploreapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewProfile extends AppCompatActivity implements View.OnClickListener {

    private FirebaseUser user;
    private DatabaseReference reference;
    private ImageView logo;
    private String userID;                 //Declare variables as private only to be used in this class
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);  //Use the view profile xml file for the layout

        logout = (Button) findViewById(R.id.logout); //initialise the logout button
        logout.setOnClickListener(this);

        user = FirebaseAuth.getInstance().getCurrentUser(); //get the current users logged into the application
        reference = FirebaseDatabase.getInstance().getReference("Users"); //grab user info and store in regerences
        userID = user.getUid(); //get the user id and store in userID variable

        final TextView FirstName = (TextView) findViewById(R.id.FirstNameEntry); //set the final variable for first name, lastname and email
        final TextView LastName = (TextView) findViewById(R.id.LastNameEntry);   //meaning once the data is retrieved, store it where it cannot be modified
        final TextView EmailAddress = (TextView) findViewById(R.id.EmailAddressEntry);

        logo = findViewById(R.id.imageView);  //initialise the imageView widget and store as a logo button
        logo.setOnClickListener(this);      //set the on click listener to the logo button

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() { //when data has been found under the specified userID
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) { //when the data is updated
                User userProfile = snapshot.getValue(User.class);      //get the value of the user

                if (userProfile != null) {                          //if the user profile is not empty/contains datta
                    String firstName = userProfile.firstName;     //grab the first name of the user
                    String lastName = userProfile.lastName;       //grab the last name of the user
                    String emailAddress = userProfile.emailAddress; //grab the email address of the user

                    FirstName.setText(firstName);                //update the Firstname textview to reflect first name grabbed
                    LastName.setText(lastName);                 //update the lastname textview to reflect the lastname grabbed
                    EmailAddress.setText(emailAddress);         //update the emailaddress textview to reflect the email address grabbed
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { //if there is a database error such as a connection or retrieval error
                Toast.makeText(ViewProfile.this, "There was an error retrieving the profile!", Toast.LENGTH_LONG).show(); //Output error message

            }
        });
    }

    @Override
    public void onClick(View v) {  //method is called when a button is clicked
        switch (v.getId()) {      //based on the button clicked switch between cases
            case R.id.imageView:  //if the imageview button is clicked
                startActivity(new Intent(ViewProfile.this, WelcomeActivity.class));  //launch the welcome activity class
                break;  //break off from the code executed
            case R.id.logout:   //if the logout button is clicked
                FirebaseAuth.getInstance().signOut();  //sign out of firebase
                startActivity(new Intent(ViewProfile.this, MainActivity.class)); //and start the Main activity class (splash screen)
                break;
        }
    }


}