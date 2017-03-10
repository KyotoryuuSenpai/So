package com.example.kyotoryuu.so;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMaps extends AppCompatActivity implements OnMapReadyCallback {
    //Variables
    Button buttonEnrere;
    MarkerOptions options;
    CameraUpdate update;
    private GoogleMap mapa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);

        //Inicialització del map.
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        buttonEnrere = (Button)findViewById(R.id.buttonEnrere);
        buttonEnrere.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(GoogleMaps.this,Geolocalitzacio.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        //Posicionament a la ciutat desitjada per viatjar.
        LatLng amsterdam = new LatLng(52,5);
        //Afegim el marcador.
        mapa.addMarker(new MarkerOptions().position(amsterdam).title("Posició Amsterdam"));
        mapa.moveCamera(CameraUpdateFactory.newLatLng(amsterdam));
        //Movem la vista a la posició del marcador.
        update = CameraUpdateFactory.newLatLngZoom(amsterdam, 5);
        mapa.animateCamera(update);

    }

}
