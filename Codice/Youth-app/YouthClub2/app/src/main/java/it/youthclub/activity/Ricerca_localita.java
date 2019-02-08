package it.youthclub.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nello.youthclub.R;

import it.youthclub.beans.Utente;

public class Ricerca_localita extends AppCompatActivity {
    private EditText editText;
    private CheckBox cb1, cb2, cb3, cb4;
    private ImageView cerca;
    private int categoria =0;
    private ImageView gps;
    private Utente t;
    private Float lat,lng;
    private boolean useGPS=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricerca_localita);
        editText = findViewById(R.id.edit);
        cb1 = findViewById(R.id.pub);
        cb2 = findViewById(R.id.bar);
        cb3 = findViewById(R.id.enoteche);
        cb4 = findViewById(R.id.discoteche);
        cerca = findViewById(R.id.img_searchl);
        gps = findViewById(R.id.img_gps);

        //Si fa passare dalla homepage i dati dell'utente
        Bundle exstras=getIntent().getExtras();
        if(exstras!=null){
            t=(Utente)exstras.getSerializable("utente");
        }


        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getGpsPosition();

            }
        });

        cerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(categoria==0){
                    Toast.makeText(getApplicationContext(),"Si prega di selezionare una categoria prima di procedere",Toast.LENGTH_LONG).show();
                }else{
                    Intent i = new Intent(getApplicationContext(), Mappa_lista.class);
                    i.putExtra("luogo", editText.getText().toString());
                    i.putExtra("categoria", categoria);
                    i.putExtra("utente",t);
                    startActivity(i);
                }

            }
        });




        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb1.isChecked()) {
                    categoria += 2;
                }else{
                    categoria -=2;
                }
            }
        });
        cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb2.isChecked())
                    categoria +=1;
                else
                    categoria -=1;
            }
        });
        cb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb3.isChecked())
                    categoria +=8;
                else
                    categoria-=8;
            }
        });
        cb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb4.isChecked())
                    categoria +=4;
                else
                    categoria-=4;
            }
        });

    }


    private void getGpsPosition(){

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                    0);

        }else{
            LocationManager locationManager = (LocationManager)
                    getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    useGPS=true;
                    lat=(float)location.getLatitude();
                    lng=(float)location.getLongitude();
                    Intent i = new Intent(getApplicationContext(), Mappa_lista.class);
                    i.putExtra("lat", lat);
                    i.putExtra("lng",lng);
                    i.putExtra("categoria", categoria);
                    i.putExtra("utente",t);
                    startActivity(i);

                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            },null);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==0){
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                getGpsPosition();
            }else{
                useGPS=false;
            }


        }
    }
}







