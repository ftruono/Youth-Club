package it.youthclub.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.nello.youthclub.ClientRequest;
import com.example.nello.youthclub.MyReview;
import com.example.nello.youthclub.R;

import it.youthclub.beans.Utente;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //TODO appena si avvia l'app deve creare l'istanza di utente con l'imei e utente deve essere passata da un activity all'altra
    private Utente user;
    private String imei;
    private TelephonyManager telephonyManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //si autentica all'avvio.
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        getImei();
        user=new Utente(imei);
        ClientRequest request=new ClientRequest(user);
        request.autenticator();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //Pulsante in alto a destra
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
                /*ClientRequest request=new ClientRequest(user);
                int res=request.editReview(20,313,"prova","prova",3,3,3,5);
                */
                Toast.makeText(getApplicationContext(),"risultato " ,Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    //Menu laterale
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.my_profile) {
            Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
            i.putExtra("utente",user);
            startActivity(i);
        } else if (id == R.id.my_review) {
            Intent i = new Intent(getApplicationContext(), MyReview.class);
            i.putExtra("utente",user);
            startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }





    public void lanciaRicerca_locale(View v) {
        Intent i = new Intent(getApplicationContext(), Ricerca_localita.class);
        i.putExtra("utente",user);
        startActivity(i);
    }
    public void lanciaRicerca_Nome(View v) {
        Intent i = new Intent(getApplicationContext(), Ricerca_nome.class);
        i.putExtra("utente",user);
        startActivity(i);
    }

    public void getImei(){

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    0);
        }else{
            imei=telephonyManager.getDeviceId();
        }



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==0){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getImei();
            }else{
                finish();
            }


        }
    }
}
