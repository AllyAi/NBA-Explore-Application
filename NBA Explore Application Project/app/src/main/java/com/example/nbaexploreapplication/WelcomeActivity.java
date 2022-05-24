package com.example.nbaexploreapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;



public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton nbaTicket;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        next = findViewById(R.id.button);
        next.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button:
                startActivity(new Intent(WelcomeActivity.this, TicketBooking.class));
        }
    }
}