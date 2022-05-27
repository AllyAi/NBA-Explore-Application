package com.example.nbaexploreapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TicketBooking extends AppCompatActivity implements View.OnClickListener{ //implement on click listener for the whole class

    private ImageView logo;  //set private variable for the imageview widget that holds the logo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_booking);  //Use ticket booking layout file

        logo = findViewById(R.id.imageView);        //initialise the logo image
        logo.setOnClickListener(this);              //set the on click listener to to image
    }

    @Override
    public void onClick(View v) { //on click method
        switch (v.getId()) {      //switch between cases depending on the id of button pressed
            case R.id.imageView:  //if imageView button is pressed
                startActivity(new Intent(TicketBooking.this, WelcomeActivity.class)); //start the welcome activity class
                break; //break off from any other cases

        }
    }
}