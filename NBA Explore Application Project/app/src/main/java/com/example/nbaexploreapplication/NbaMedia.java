package com.example.nbaexploreapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.firebase.auth.FirebaseAuth;

public class NbaMedia extends AppCompatActivity implements View.OnClickListener{

    private ImageView logo;  //private variable for image view widget holding the logo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nba_media);   //use the nba media layout file

        logo = findViewById(R.id.imageView);       //initialise the image view logo
        logo.setOnClickListener(this);             //set an on click listener to the logo

        View imageView = findViewById(R.id.imageThumb);    //initialise the imageview
        View imageView2 = findViewById(R.id.imageThumb2);  //initialise the imageview

        VideoView videoView = findViewById(R.id.videoView);   //initialise the videoview
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.nbagame; //pass the video file stored in the /raw folder to the videoView
        Uri uri = Uri.parse(videoPath);  //uniform resource identifier that grabs the videoPath variable and extracts the necessary information from the path
        videoView.setVideoURI(uri);      //set the videoview to the video filepath specified

        VideoView videoView2 = findViewById(R.id.videoView2);  //initialise the videoview
        String videoPath2 = "android.resource://" + getPackageName() + "/" + R.raw.nbagame2; //pass the video file stored in the /raw folder to the videoView
        Uri uri2 = Uri.parse(videoPath2); //uniform resource identifier that grabs the videoPath2 variable and extracts the necessary information from the path
        videoView2.setVideoURI(uri2); //set the videoview to the video filepath specified


        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer MP, int i, int i1) { //method that completes an action based on the state of the video
                if (i == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START){ //if the video has started buffering/rendering
                    //when the first frame has buffered
                    imageView.setVisibility(View.GONE); //set the image view 2 thumbnail visibility to gone
                }
                return false;
            }
        });

        MediaController mediaController = new MediaController(this); //initialise media controller
        videoView.setMediaController(mediaController);                       //attach the media controller to the videoview
        mediaController.setAnchorView(videoView);                            //anchor the media controls to the video view

        videoView2.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer MP, int i, int i1) { //method that completes an action based on the state of the video
                if (i == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START){  //if the video has started buffering/rendering
                    //when the first frame has buffered
                    imageView2.setVisibility(View.GONE);  //set the image view 2 thumbnail visibility to gone
                }
                return false;
            }
        });

        MediaController mediaController2 = new MediaController(this);  //initialise the media controller
        videoView2.setMediaController(mediaController2);                      //attach the media controller to the videoview2
        mediaController2.setAnchorView(videoView2);                           //anchor the media controls to the videoview

    }


    @Override
    public void onClick(View v) { //method that is called after the button has been clicked
        switch (v.getId()) {     //switch between cases depending on the clicked button
            case R.id.imageView: //if the imageView button is clicked
                startActivity(new Intent(NbaMedia.this, WelcomeActivity.class)); //launch the welcome activity class
                break;

        }
    }
}