package com.example.nbaexploreapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;



public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton nbaTicket, nbaMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        nbaTicket = findViewById(R.id.imageButton2);
        nbaTicket.setOnClickListener(this);

        nbaMap = findViewById(R.id.imageButton5);
        nbaMap.setOnClickListener(this);

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.imageButton2:
                startActivity(new Intent(WelcomeActivity.this, TicketBooking.class));
                break;

            case R.id.imageButton5:
                startActivity(new Intent(WelcomeActivity.this, NbaTourism.class));
                break;
        }

    }
}