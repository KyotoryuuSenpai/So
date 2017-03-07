package com.example.kyotoryuu.so;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Interpolacio extends AppCompatActivity {

    Button buttonEnrere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolacio);

        ImageView imatge = (ImageView)findViewById(R.id.imageInterpolacio);
        Animation animacioDoge = AnimationUtils.loadAnimation(this, R.anim.venir);
        imatge.startAnimation(animacioDoge);

        buttonEnrere = (Button)findViewById(R.id.buttonEnrere);

        buttonEnrere.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Interpolacio.this,Animacions.class);
                startActivity(intent);
            }
        });
    }
}
