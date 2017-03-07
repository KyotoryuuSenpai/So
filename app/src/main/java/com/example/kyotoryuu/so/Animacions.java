package com.example.kyotoryuu.so;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

public class Animacions extends AppCompatActivity {

    Button buttonInterpolacio, buttonFotogrames, buttonEnrere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animacions);

        buttonInterpolacio = (Button)findViewById(R.id.buttonInterpolacio);
        buttonFotogrames = (Button)findViewById(R.id.buttonFotogrames);
        buttonEnrere = (Button)findViewById(R.id.buttonEnrere);

        buttonInterpolacio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Animacions.this,Interpolacio.class);
                startActivity(intent);
            }
        });

        buttonFotogrames.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Animacions.this,Fotogrames.class);
                startActivity(intent);
            }
        });

        buttonEnrere.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Animacions.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
