package com.example.nello.youthclub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import it.youthclub.beans.Utente;

public class Ricerca_nome extends AppCompatActivity {
    private EditText editText;
    private CheckBox cb1,cb2,cb3,cb4;
    private ImageView cerca;
    private Utente user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricerca_nome);
        editText = findViewById(R.id.name);
        cerca = findViewById(R.id.img_search);
        Bundle d=getIntent().getExtras();
         if(d!=null){
             user=(Utente)d.getSerializable("utente");
         }



        cerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().indexOf(",")<0){
                    Toast.makeText(v.getContext(), "Si prega di inserire la città dopo il nome del locale separati da una virgola", Toast.LENGTH_SHORT).show();
                }else {
                    Intent i = new Intent(getApplicationContext(), Mappa_lista.class);
                    i.putExtra("nome_locale", editText.getText().toString());
                    i.putExtra("categoria",0);
                    i.putExtra("utente",user);
                    startActivity(i);
                }
            }
        });
    }






}
