package com.example.kyotoryuu.so;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class EventsMapa extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mapa;
    Button buttonEnrere;
    TextView textInfo;

    Marker marker;
    MarkerOptions options;
    CameraUpdate update;
    double latitude = 0;
    double longitude = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        textInfo = (TextView)findViewById(R.id.textInfo);
        buttonEnrere = (Button)findViewById(R.id.buttonEnrere);

        buttonEnrere.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(EventsMapa.this,Geolocalitzacio.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        LatLng latlng = new LatLng(40, -3);
        options = new MarkerOptions().position(latlng);
        marker = mapa.addMarker(options);
        update = CameraUpdateFactory.newLatLngZoom(latlng, 5);
        mapa.animateCamera(update);
        mapa.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                latitude = point.latitude;
                longitude = point.longitude;
                marker.remove();
                options.position(point);
                marker = mapa.addMarker(options);
                String posicion = "Latitud: "+latitude+"\n Longitud: "+longitude;
                setText(posicion);
                marker.setTitle(posicion);
                update = CameraUpdateFactory.newLatLngZoom(point, 5);
                mapa.animateCamera(update);
            }
        });
    }

    public void setText(String texto){
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }
}

