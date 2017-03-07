package com.example.kyotoryuu.so;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    Button buttonAnimacions, buttonGeolocalitzacio, buttonCamera, buttonSo, buttonVideo, buttonSortir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAnimacions = (Button)findViewById(R.id.buttonAnimacions);
        buttonGeolocalitzacio = (Button)findViewById(R.id.buttonGeolocalitzacio);
        buttonCamera = (Button)findViewById(R.id.buttonCamera);
        buttonSo = (Button)findViewById(R.id.buttonSo);
        buttonVideo = (Button)findViewById(R.id.buttonVideo);
        buttonSortir = (Button)findViewById(R.id.buttonSortir);

        buttonAnimacions.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,Animacions.class);
                startActivity(intent);
            }
        });

        buttonGeolocalitzacio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,Geolocalitzacio.class);
                startActivity(intent);
            }
        });

    }

}
