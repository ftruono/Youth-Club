package com.example.nello.youthclub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.youthclub.beans.Locale;

public class Mappa_lista extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, Serializable {

    private String value=null, pulsanti[]=new String[4],imei;
    private ListView lista1;
    private ArrayAdapter<Locale> adapter;
    private List<Locale>risultati=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_mappa_lista);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //Elementi passati
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
             imei=extras.getString("imei");
             ClientRequest clientRequest=new ClientRequest(null);
            //0=ricerca per luogo 1=ricerca per gps 2=ricerca per nome
             switch (extras.getInt("mod")){
                 case 0:
                     value=extras.getString("nome_locale");
                     pulsanti=extras.getStringArray("tipi");
                     risultati = clientRequest.search(value, 15,1);
                     while (risultati==null){
                         risultati = clientRequest.search(value, 15,1);
                     }
                     break;
                 case 1:
                     risultati=clientRequest.search(extras.getFloat("lat"),extras.getFloat("lng"), 15,2);
                     while (risultati==null){
                         risultati = clientRequest.search(extras.getFloat("lat"),extras.getFloat("lng"), 15,2);
                     }
                     break;
                 case 2:
                     risultati =clientRequest.search(extras.getString("nome_locale"),3);
                     while (risultati==null){
                         risultati = clientRequest.search(extras.getString("nome_locale"),3);
                     }
                     break;
             }

        }

        //Lista
        lista1=findViewById(R.id.lista1);
        CustomAdapter1 customAdapter1=new CustomAdapter1(this,R.layout.activity_mappa_lista,new ArrayList<Locale>());
        lista1.setAdapter(customAdapter1);

        //aggiunge gli elementi alla lista
        int x=risultati.size();
        for(int i=0;i<x;i++){
            Locale locale=risultati.get(i);
            customAdapter1.add(locale);

        }
        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), InfoLocale.class);
                i.putExtra("imei",imei);
                i.putExtra("locale", risultati.get(position));
                startActivity(i);
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap mMap) {
        Marker marker;
        float zoomLevel = 10.0f;
        int x=risultati.size();
        for(int i=0;i<x;i++){
            LatLng l=new LatLng(risultati.get(i).getLatitudine(),risultati.get(i).getLongitudine());
            marker = mMap.addMarker(new MarkerOptions().position(l).title(risultati.get(i).getNome()));
            marker.setTag(0);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(l,zoomLevel));
        }
        mMap.setOnMarkerClickListener(this);
    }

    /** Called when the user clicks a marker. */
    @Override
    public boolean onMarkerClick(final Marker marker) {
        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();
        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Log.d("Lat", String.valueOf(marker.getPosition()));
            Toast.makeText(this, marker.getTitle() + " has been clicked " + marker.getPosition() + " times.", Toast.LENGTH_SHORT).show();
        }
        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }
}