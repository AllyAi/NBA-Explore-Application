package com.example.nbaexploreapplication;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.nbaexploreapplication.databinding.ActivityNbaTourismBinding;

public class NbaTourism extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;     //Declare variables as private only to be used in this class
    private ActivityNbaTourismBinding binding;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nba_tourism);  //use the nba tourism xml layout file

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager() //Obtain the supportmapfragment and get notified when the map is ready to be used.
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sanfran = new LatLng(37.7680465, -122.3921997); //set the marker to desired location using latitude and longitude
        mMap.addMarker(new MarkerOptions().position(sanfran).title("Chase Centre Arena")); //in this case set to the chase centre arena in sanfrancisco
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sanfran));
    }

}