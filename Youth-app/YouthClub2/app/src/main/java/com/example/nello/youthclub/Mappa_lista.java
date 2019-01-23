package com.example.nello.youthclub;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Mappa_lista extends AppCompatActivity implements OnMapReadyCallback  {

    private TextView text;
    private String value="questo è in caso che vincenzo è strozno";
    private String pulsanti[]=new String[4];
    private ListView lista1;
    //1 pub 2 bat 3 enoteca 4 discoteche
    private ArrayAdapter<Locale> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_mappa_lista);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //Elementi passati
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
             value=extras.getString("nome_locale");
             pulsanti=extras.getStringArray("tipi");

        }
        for(int i=0;i<4;i++){
            if(pulsanti[i]!=null)
                System.out.print(pulsanti[i]);
        }


        //Lista
        lista1=findViewById(R.id.lista1);
        CustomAdapter1 customAdapter1=new CustomAdapter1(this,R.layout.activity_mappa_lista,new ArrayList<Locale>());
        lista1.setAdapter(customAdapter1);

        for(int i=0;i<10;i++){
            Locale locale=new Locale(null,"cazzo"+i,"fisciano","id");
            customAdapter1.add(locale);
        }
        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), InfoLocale.class);
                startActivity(i);
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        LatLng marker1 = new LatLng(40.7694772,14.7908394);
        googleMap.addMarker(new MarkerOptions().position(marker1).title("Marker in fisciano"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(marker1));
        LatLng marker2 = new LatLng(40.773582,14.780284);
        googleMap.addMarker(new MarkerOptions().position(marker2).title("Marker 2"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(marker2));

    }
}