package com.example.nello.youthclub;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Mappa_lista extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

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
    public void onMapReady(GoogleMap mMap) {
        Marker marker;
        float zoomLevel = 13.0f;

        LatLng marker1 = new LatLng(40.7694772,14.7908394);
        marker = mMap.addMarker(new MarkerOptions().position(marker1).title("marker1"));
        marker.setTag(0);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker1,zoomLevel));
        LatLng marker2 = new LatLng(40.773582,14.780284);
        marker = mMap.addMarker(new MarkerOptions().position(marker2).title("marker2"));
        marker.setTag(0);

        mMap.setOnMarkerClickListener(this);



        /*
        LatLng marker1 = new LatLng(40.7694772,14.7908394);
        googleMap.addMarker(new MarkerOptions().position(marker1).title("Marker in fisciano"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker1,zoomLevel));
        LatLng marker2 = new LatLng(40.773582,14.780284);
        googleMap.addMarker(new MarkerOptions().position(marker2).title("Marker 2"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(marker2));
        googleMap.setOnMarkerClickListener(this);*/



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