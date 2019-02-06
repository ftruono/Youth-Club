package it.youthclub.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nello.youthclub.ClientRequest;
import com.example.nello.youthclub.InfoLocale;
import com.example.nello.youthclub.R;
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

import it.youthclub.adapters.LocaliAdpter;
import it.youthclub.beans.Locale;
import it.youthclub.beans.Utente;

public class Mappa_lista extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, Serializable {

    private ListView lista1;
    private Utente t;
    private ClientRequest request;
    private List<Locale>risultati=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mappa_lista);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Elementi passati
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            t=(Utente)extras.getSerializable("utente");
            request=new ClientRequest(t);
            int categoria=extras.getInt("categoria");
            String luogo=extras.getString("luogo");
            String name=extras.getString("nome_locale");

            if(luogo==null){
                    float lat = extras.getFloat("lat");
                    float lng = extras.getFloat("lng");
                    risultati = request.search(lat, lng, categoria);

            }else
                risultati=request.search(luogo,categoria);

             if(name!=null)
                 risultati=request.search(name);
            }

//TODO ritorno alla schermata precendete
        if(risultati==null){
            Toast.makeText(getApplicationContext(),"Il server non risponde " ,Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }


        //Lista
        lista1=findViewById(R.id.lista1);
        LocaliAdpter customAdapter1=new LocaliAdpter(this,R.layout.activity_mappa_lista,risultati);
        lista1.setAdapter(customAdapter1);


        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), InfoLocale.class);
                i.putExtra("utente",t);
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

            
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }


    private void typeSearch() {


    }
}