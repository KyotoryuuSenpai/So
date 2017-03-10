package com.example.kyotoryuu.so;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class Camera extends AppCompatActivity {
    Button bttnFoto, bttnFotoReduida, buttonEnrere;
    ImageView imageView;
    Uri identificadorImatge;
    final int APP_CAMERA = 1;
    boolean reduida;
    int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imageView = (ImageView) findViewById(R.id.imageView);
        Button bttnFoto = (Button) findViewById(R.id.fotoNormal);
        Button bttnFotoReduida = (Button) findViewById(R.id.fotoReduida);
        Button buttonEnrere = (Button) findViewById(R.id.buttonEnrere);

        imageView.requestLayout();
        imageView.getLayoutParams().width = 1080;
        imageView.setX(0);


        buttonEnrere.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Camera.this,MainActivity.class);
                startActivity(intent);
            }
        });

        bttnFoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reduida = false;
                //Se crea el intent para la aplicacion de fotos
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                //Se crea un fichero externo y se le pasa el intent
                File foto = new File(Environment.getExternalStorageDirectory(), "fotos.jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(foto));

                //Se guarda el identificador para luego recuperarlo cuando la foto este hecha
                identificadorImatge = Uri.fromFile(foto);
                //Arranca el activity
                startActivityForResult(intent, APP_CAMERA);
            }
        });

        bttnFotoReduida.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reduida = true;
                //Se crea el intent para la aplicacion de fotos
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                //Se crea un fichero externo y se le pasa el intent
                File foto = new File(Environment.getExternalStorageDirectory(), "fotos.jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(foto));

                //Se guarda el identificador para luego recuperarlo cuando la foto este hecha
                identificadorImatge = Uri.fromFile(foto);
                //Arranca el activity
                startActivityForResult(intent, APP_CAMERA);
            }
        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("requestCode", "requestCode: " + requestCode);
        Log.e("RESULT_OK", "RESULT_OK: " + RESULT_OK);

        //Reinicia la posicion del imageView.
        imageView.requestLayout();
        imageView.getLayoutParams().width = 1080;
        imageView.setX(0);

        switch (requestCode) {
            case (APP_CAMERA):
                if (resultCode == RESULT_OK) {
                    //Da acceso a los contenidos:
                    ContentResolver contRes = getContentResolver();

                    //El contenido del fichero ha cambiado.
                    contRes.notifyChange(identificadorImatge, null);

                    //Accede al ImageView y carga la foto que ha hecho la camara

                    Bitmap bitmap;

                    //Como al cargar la imagen puede fallar, hay que ponerlo dentro de un try/catch
                    try {
                        bitmap = android.provider.MediaStore.Images.Media.getBitmap(contRes, identificadorImatge);

                        //Reducimos la imagen para no tener problemas de visualizacion:

                        int alt = (int) (bitmap.getHeight() * 1080 / bitmap.getWidth());
                        Bitmap reduit = Bitmap.createScaledBitmap(bitmap, 1080, alt, true);

                        imageView.setImageBitmap(reduit);

                        if(reduida){
                            //Poner esto para reducir imagen
                            imageView.requestLayout();
                            imageView.getLayoutParams().width = 500;
                            imageView.setX(280);
                        }

                    } catch (Exception e) {
                        Toast.makeText(this, "No es pot carregar la imatge " + identificadorImatge.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        }
    }
}
