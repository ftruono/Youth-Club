package com.example.nello.youthclub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import it.youthclub.adapters.CustomAdapterRecensione1;
import it.youthclub.beans.Categoria;
import it.youthclub.beans.Locale;
import it.youthclub.beans.Recensione;
import it.youthclub.beans.Utente;

public class InfoLocale extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private Locale locale;
    private TextView textViewnomeloclae,tvvia,tvNumeroTelefono,tvNonVoto1,tvNonVoto2;
    private ListView lista_recensione;
    private Utente t;
    private RatingBar tvtotalevoti;
    private Button scrivi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_locale);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        textViewnomeloclae=findViewById(R.id.nome);
        tvvia=findViewById(R.id.via);
        tvNumeroTelefono=findViewById(R.id.numeroTelefono);
        tvtotalevoti=findViewById(R.id.ratingBar);
        tvtotalevoti.setClickable(false);
        lista_recensione=findViewById(R.id.lista_recensione);
        scrivi=findViewById(R.id.writereview);
        tvNonVoto1=findViewById(R.id.TextNonVoto1);
        tvNonVoto2=findViewById(R.id.TextNonVoto2);

        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            t= (Utente) extras.getSerializable("utente");
            locale= (Locale) extras.getSerializable("locale");
        }
        textViewnomeloclae.setText(locale.getNome());
        if(locale.getMediaVoto()==0){
            tvtotalevoti.setVisibility(View.INVISIBLE);
            tvNonVoto1.setVisibility(View.VISIBLE);
        }else{
            tvtotalevoti.setRating(locale.getMediaVoto());
        }
        tvNumeroTelefono.setText(locale.getPhone());
        tvvia.setText(locale.getVia());



         List<Recensione> recensioni=locale.getRecensioni();
         if(recensioni.size()==0){
             lista_recensione.setVisibility(View.INVISIBLE);
             tvNonVoto2.setVisibility(View.VISIBLE);
         }else{
             CustomAdapterRecensione1 customAdapterRecensione1=new CustomAdapterRecensione1(this,R.layout.layout_recensione,recensioni);
             lista_recensione.setAdapter(customAdapterRecensione1);
         }



        scrivi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),EditRecensione.class);
                i.putExtra("utente",t);
                i.putExtra("locale",locale);

                startActivity(i);
            }
        });
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Marker marker;
        float zoomLevel = 10.0f;

            LatLng l=new LatLng(locale.getLatitudine(),locale.getLongitudine());
            marker = googleMap.addMarker(new MarkerOptions().position(l).title(locale.getNome()));
            marker.setTag(0);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(l,zoomLevel));

        googleMap.setOnMarkerClickListener(this);
    }
}
