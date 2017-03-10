package com.example.kyotoryuu.so;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

public class Video extends AppCompatActivity {

    VideoView video;
    Button bttnGravar,bttnReproduir, buttonEnrere;
    //Una constant per identificar l'intent de gravar vídeo
    final static int INTENT_GRAVAR_VIDEO=1;
    Uri uriVideo=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        //Indiquem les referencies dels botons, repecte al xml
        bttnReproduir=(Button) findViewById(R.id.bttnReproduir);
        bttnGravar=(Button) findViewById(R.id.bttnGravar);
        buttonEnrere=(Button) findViewById(R.id.buttonEnrere);
        video=(VideoView) findViewById(R.id.videoView);

        //Posem incialment el botó de reproduir en fals, ja que no hi ha res per reproduir encara.
        bttnReproduir.setEnabled(false);

        buttonEnrere.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Video.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onClickBotoGravar(View v){
        //Es crea l'intent i es llança
        Intent intent=new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, INTENT_GRAVAR_VIDEO);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == RESULT_OK){
            if (requestCode == INTENT_GRAVAR_VIDEO){
                uriVideo= data.getData();
                Toast.makeText(this,uriVideo.getPath(), Toast.LENGTH_LONG).show();
                //Activar el boto de reproducir video perque ja hi ha un video
                bttnReproduir.setEnabled(true);
            }
        }else if (resultCode == RESULT_CANCELED){
            uriVideo=null;
            //En cas de que la gravacio sigui cancel·lada, mostrem un missatge
            Toast.makeText(this,"Gravació cancel·lada", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickBotoReproduir(View v){
        //Primer mostra un toast amb l'URI del video
        Toast.makeText(this,"Reproduint: ", Toast.LENGTH_LONG).show();

        //Després accedeix al visor, carrega el video i el reprodueix
        video.setVideoURI(uriVideo);
        video.start();
    }
}
