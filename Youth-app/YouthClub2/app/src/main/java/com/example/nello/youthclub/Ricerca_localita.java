package com.example.nello.youthclub;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Locale;

import static android.location.LocationManager.*;

public class Ricerca_localita extends AppCompatActivity {
    private EditText editText;
    private CheckBox cb1, cb2, cb3, cb4;
    private ImageView cerca;
    private String pulsanti[] = new String[4];
    //1 pub 2 bat 3 enoteca 4 discoteche
    private ImageView gps;


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

        cerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Mappa_lista.class);
                i.putExtra("nome_locale", editText.getText().toString());
                i.putExtra("tipi", pulsanti);
                startActivity(i);
            }
        });
        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pulsanti[0] = "pub";
            }
        });
        cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pulsanti[1] = "bar";
            }
        });
        cb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pulsanti[2] = "enoteca";
            }
        });
        cb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pulsanti[3] = "discoteca";
            }
        });

    }


    @SuppressLint("MissingPermission")
    public void gpsposition(View view) {

    }





}

