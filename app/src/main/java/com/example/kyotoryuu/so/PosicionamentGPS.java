package com.example.kyotoryuu.so;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

public class PosicionamentGPS extends AppCompatActivity implements LocationListener{
    //Variables
    Button buttonEnrere;
    TextView textPosicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posicionament_gps);

        textPosicio = (TextView) findViewById(R.id.textPosicio);
        buttonEnrere = (Button) findViewById(R.id.buttonEnrere);

        buttonEnrere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PosicionamentGPS.this, Geolocalitzacio.class);
                startActivity(intent);
            }
        });

        //Inicialització del LocationManager.
        LocationManager gestorLoc = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //Updates de la localització. Es faran als 1500 milisegons i a 1 metre de distància.
        gestorLoc.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1500, 1, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        //Cada cop que es canvii la posició, s'avisará tant per un TextView com pel Toast.
        String text = "Posicio actual: \n"+"Latitud = "+location.getLatitude()+"\n"+"Longitud = "+location.getLongitude();
        textPosicio.setText(text);
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        String missatge="";
        //Cada cop que el gps cambia de status es mostrará en el Toast.
        switch(status){
            case LocationProvider.OUT_OF_SERVICE:
                missatge = "GPS status: Out of service";
                Toast.makeText(getApplicationContext(), missatge, Toast.LENGTH_SHORT).show();
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                missatge = "GPS status: Temporarily unavaiable";
                Toast.makeText(getApplicationContext(), missatge, Toast.LENGTH_SHORT).show();
                break;
            case LocationProvider.AVAILABLE:
                missatge = "GPS status: Available";
                Toast.makeText(getApplicationContext(), missatge, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onProviderEnabled(String provider) {
        //S'avisa quan el GPS s'habilita.
        Toast.makeText(getApplicationContext(),"GPS habilitat per l'usuari", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        //S'avisa quan el GPS es desactiva.
        Toast.makeText(getApplicationContext(),"GPS desactivat per l'usuari", Toast.LENGTH_SHORT).show();

    }
}