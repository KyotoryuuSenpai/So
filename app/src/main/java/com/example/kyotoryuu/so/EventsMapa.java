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
    //Variables
    private GoogleMap mapa;
    Button buttonEnrere;
    Marker marker;
    MarkerOptions options;
    CameraUpdate update;
    double latitude = 0;
    double longitude = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_mapa);

        //Inicialització del GoogleMap.
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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
        //Es crea una variable amb la latitud.
        LatLng latlng = new LatLng(40, -3);
        //S'afegeix un marcador al mapa amb la latitud anterior.
        options = new MarkerOptions().position(latlng);
        marker = mapa.addMarker(options);
        //Es mou la vista cap a la posició del marcador.
        update = CameraUpdateFactory.newLatLngZoom(latlng, 5);
        mapa.animateCamera(update);
        //Configuració per quan es fa clic al mapa.
        mapa.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                //Asignem els valors de point a dues variables.
                latitude = point.latitude;
                longitude = point.longitude;
                //Borrem el marcador antic.
                marker.remove();
                //Posem un nou marcador.
                options.position(point);
                marker = mapa.addMarker(options);
                //Mostrem la informació de la posició actual.
                String posicion = "Latitud: "+latitude+"\n Longitud: "+longitude;
                setText(posicion);
                marker.setTitle(posicion);
                //Movem la vista cap al marker.
                update = CameraUpdateFactory.newLatLngZoom(point, 5);
                mapa.animateCamera(update);
            }
        });
    }

    //Metode per enviar al Toast la informació(No es permet fer un toast dins del setOnMapClickListener).
    public void setText(String texto){
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }
}

