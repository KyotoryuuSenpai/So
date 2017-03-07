package com.example.kyotoryuu.so;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Fotogrames extends AppCompatActivity {

    Button buttonEnrere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotogrames);

        buttonEnrere = (Button)findViewById(R.id.buttonEnrere);

        ImageView imageView = (ImageView) findViewById(R.id.imageFotogrames);
        AnimationDrawable animacio = (AnimationDrawable) imageView.getDrawable();
        animacio.start();

        buttonEnrere.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Fotogrames.this,Animacions.class);
                startActivity(intent);
            }
        });
    }
}
