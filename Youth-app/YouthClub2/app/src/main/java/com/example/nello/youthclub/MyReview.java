package com.example.nello.youthclub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import it.youthclub.adapters.CustomAdapterRecensione1;
import it.youthclub.beans.Recensione;
import it.youthclub.beans.Utente;

public class MyReview extends AppCompatActivity {
    private ListView lista;
    private Utente user;
    private List<Recensione> rec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_review);

        lista=findViewById(R.id.lista_tue_recensioni);
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            user=(Utente)extras.getSerializable("utente");
        }


        ClientRequest req=new ClientRequest(user);
        rec=req.getReviewsByAccount();
        CustomAdapterRecensione1 customAdapterRecensione1=new CustomAdapterRecensione1(this,R.layout.layout_recensione,rec);
        lista.setAdapter(customAdapterRecensione1);



    }

    public void modificaMyReview(View v){
        Intent i=new Intent(v.getContext(),EditRecensione.class);
        i.putExtra("recensione",(Recensione)v.getTag());
        i.putExtra("utente",user);
        startActivity(i);
    }

}
