package com.example.nello.youthclub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Ricerca_nome extends AppCompatActivity {
    private EditText editText;
    private CheckBox cb1,cb2,cb3,cb4;
    private ImageView cerca;
    private String pulsanti[]=new String[4];
    //1 pub 2 bat 3 enoteca 4 discoteche
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricerca_nome);
        editText = findViewById(R.id.edit);
        cb1 = findViewById(R.id.pub);
        cb2 = findViewById(R.id.bar);
        cb3 = findViewById(R.id.enoteche);
        cb4 = findViewById(R.id.discoteche);
        cerca = findViewById(R.id.img_search);


        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pulsanti[0]="pub";
            }
        });
        cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pulsanti[1]="bar";
            }
        });
        cb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pulsanti[2]="enoteca";
            }
        });
        cb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pulsanti[3]="discoteca";
            }
        });


        cerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().indexOf(",")<0){
                    Toast.makeText(v.getContext(), "Si prega di inserire la città dopo il nome del locale separati da una virgola", Toast.LENGTH_SHORT).show();
                }else {
                    Intent i = new Intent(getApplicationContext(), Mappa_lista.class);
                    i.putExtra("nome_locale", editText.getText().toString());
                    i.putExtra("tipi",pulsanti);
                    i.putExtra("mod",2);
                    Bundle extras=getIntent().getExtras();
                    if(extras!=null){
                        i.putExtra("imei",extras.getString("imei"));
                    }
                    startActivity(i);
                }
            }
        });
    }






}
