package com.example.kyotoryuu.so;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Geolocalitzacio extends AppCompatActivity {

    Button buttonGPS, buttonGoogle, buttonEvents, buttonEnrere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geolocalitzacio);

        buttonGPS = (Button)findViewById(R.id.buttonGPS);
        buttonGoogle = (Button)findViewById(R.id.buttonGoogle);
        buttonEvents = (Button)findViewById(R.id.buttonEvents);
        buttonEnrere = (Button)findViewById(R.id.buttonEnrere);

        buttonGPS.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Geolocalitzacio.this,PosicionamentGPS.class);
                startActivity(intent);
            }
        });

        buttonGoogle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Geolocalitzacio.this,GoogleMaps.class);
                startActivity(intent);
            }
        });

        buttonEvents.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Geolocalitzacio.this,EventsMapa.class);
                startActivity(intent);
            }
        });

        buttonEnrere.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Geolocalitzacio.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
