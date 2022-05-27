package com.example.nbaexploreapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;


public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton nbaTicket, nbaMap ,nbaProfile, nbaMedia;  //declare variables as private only to be used in this class


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);    //use the activity welcome xml file for the layout

        nbaTicket = findViewById(R.id.imageButton2);  //initialise the imagebutton under the nbaticket variable
        nbaTicket.setOnClickListener(this);           //set the on click listener to the nba ticket button

        nbaMap = findViewById(R.id.imageButton5);     //initialise the imagebutton under the nbamap variable
        nbaMap.setOnClickListener(this);              //set the on click listener to the nba map button

        nbaProfile = findViewById(R.id.imageButton4); //initialise the imagebutton under the nbaprofile variable
        nbaProfile.setOnClickListener(this);          //set the on click listener to the nba profile button

        nbaMedia = findViewById(R.id.imageButton3);  //initialise the imagebutton under the nba media variable
        nbaMedia.setOnClickListener(this);           //set the on click listener to the nba media button
    }

    @Override
    public void onClick(View v){       //on click method called when the button is pressed
        switch (v.getId()){           //swtich cases depending on the id of the button pressed
            case R.id.imageButton2:    //if the id = imagebutton2
                startActivity(new Intent(WelcomeActivity.this, TicketBooking.class)); //start the ticket booking activity
                break; //break off from the rest of the code

            case R.id.imageButton4: //if the id = imagebutton4
                startActivity(new Intent(WelcomeActivity.this, ViewProfile.class)); //start the view profile activity
                break; //break off from the rest of the code

            case R.id.imageButton5: //if the id = imagebutton5
                startActivity(new Intent(WelcomeActivity.this, NbaTourism.class)); //start the nba tourism activity
                break; //break off from the rest of the code

            case R.id.imageButton3: //if the id = imagebutton3
                startActivity(new Intent(WelcomeActivity.this, NbaMedia.class));  //start the nba media activity
                break; //break off from the rest of the code
        }
    }
}