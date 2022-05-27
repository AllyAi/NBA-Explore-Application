package com.example.nbaexploreapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private Button button;             //declare the variables private only to be used in this class
    private ImageView imageView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)  //require specific android version forbidding any version earlier than lollipop

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1); //request or exclude any window features needed/not required
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT); //set the status bar to transparent
        setContentView(R.layout.activity_main);  //use the activity main xml layout file

        button = findViewById(R.id.nextActivity); //initialise the next activity button under the button variable
        videoView = findViewById(R.id.viewVideo); //initialise the view video button under the video view variable
        imageView = findViewById(R.id.imageView2); //initialise the image view button under the image view variable

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.nba_splash; //pass the video file stored in the /raw folder to the videoView

        Uri uri = Uri.parse(videoPath); //uniform resource identifier that grabs the videoPath variable and extracts the necessary information from the path
        videoView.setVideoURI(uri);     //set the videoview to the video filepath specified
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){ //when the video is prepared ready to be played
            @Override
            public void onPrepared(MediaPlayer mp){ //execute the onPrepared method
                mp.start();                         //start the media player
                imageView.setVisibility(View.GONE); //set the visibility of the image view thumbnail to gone.
            }
        });

       videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){  //when the video has loaded
           @Override
           public void onCompletion(MediaPlayer mp){ //start the media player
               mp.start();  //start the media player
           }
       });

        button.setOnClickListener(new View.OnClickListener()  //set on click listener for the button
        {
            @Override
            public void onClick(View v) {  //call the onclick method when the button has been pressed
                startActivity(new Intent(MainActivity.this, LoginRegister.class));  //start the login register activity when the button has been pressed
            }
        });
    }
}