package com.example.kyotoryuu.so;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Fotogrames extends AppCompatActivity {
    //Variables
    Button buttonEnrere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotogrames);

        ImageView imageView = (ImageView) findViewById(R.id.imageFotogrames);

        //Asignem la animació al imageView i la iniciem.
        AnimationDrawable animacio = (AnimationDrawable) imageView.getDrawable();
        animacio.start();

        buttonEnrere = (Button)findViewById(R.id.buttonEnrere);
        buttonEnrere.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Fotogrames.this,Animacions.class);
                startActivity(intent);
            }
        });
    }
}
