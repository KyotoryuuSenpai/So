package com.example.kyotoryuu.so;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;
import java.io.IOException;

public class Sound extends AppCompatActivity {

    Button bttnReproduir, buttonEnrere;
    ToggleButton bttnGravar;

    private static String mNomFitxer1 = null;
    private static String mNomFitxer2 = null;
    private boolean mGravant = false;
    private boolean mReproduint = false;

    private MediaRecorder mRecorder = null;
    private MediaPlayer mPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);
        //Posem les referencies dels botons
        bttnGravar = (ToggleButton) findViewById(R.id.bttnGravar);
        bttnReproduir = (Button) findViewById(R.id.bttnReproduir);
        buttonEnrere = (Button) findViewById(R.id.buttonEnrere);

        //GENERAR EL NOM DEL FITXER
        mNomFitxer1 = Environment.getExternalStorageDirectory() + "/gravacio1.3gp";
        //mNomFitxer2= getExternalFilesDir(null) + "gravacio2.3gp";

        buttonEnrere.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Sound.this,MainActivity.class);
                startActivity(intent);
            }
        });

        bttnGravar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mGravant) {
                    aturaGravacio();
                } else {
                    comencaGravacio();
                }
                //Invertim la variable booleana
                mGravant = !mGravant;
            }
        });

        bttnReproduir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mReproduint) {
                    aturaReproduccio();
                } else {
                    comencaReproduccio();
                }
                //Invertim la variable booleana
                mReproduint = !mReproduint;
            }
        });
    }


    private void comencaGravacio() {
        //Crea el mediaRecorder i especifica la font d'audio, el format de sortida
        // i el fitxer, i el codificador d'audio
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mNomFitxer1);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            //Enllestim la gravació
            mRecorder.prepare();
            mRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Si s'ha pogut disposar tot correctament, comença a gravar

    }

    private void aturaGravacio() {
        try {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void aturaReproduccio() {
        mPlayer.release();
        mPlayer = null;
    }


    private void comencaReproduccio() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mNomFitxer1);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
