package com.example.nello.youthclub;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import it.youthclub.activity.MainActivity;
import it.youthclub.beans.Categoria;
import it.youthclub.beans.Locale;
import it.youthclub.beans.Recensione;
import it.youthclub.beans.Utente;


public class EditRecensione extends AppCompatActivity {
    private Utente t;
    private Locale l;
    private Recensione recensione;
    private Categoria c;


    private TextView nomeLocale,viaLocale;
    private EditText titolo,testoR;
    private RatingBar votoQP,votoS,votoC;// float voto int votoServizio int votoQP int votoCibo;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recensione);

        nomeLocale = findViewById(R.id.nome_locale);
        viaLocale = findViewById(R.id.via_locale);
        votoQP = findViewById(R.id.VotoQP);
        votoS = findViewById(R.id.VotoServizio);
        votoC = findViewById(R.id.VotocCibo);
        titolo = findViewById(R.id.editTextNomeRecensione);
        testoR = findViewById(R.id.editTextTestoRecensione);
        logo=findViewById(R.id.logo_locale);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            t= (Utente) extras.getSerializable("utente");
            l = (Locale) extras.getSerializable("locale");
            recensione=(Recensione) extras.getSerializable("recensione");


            if(l!=null){
                nomeLocale.setText(l.getNome());
                viaLocale.setText(l.getVia());
            } else if(recensione!=null){
                nomeLocale.setText(l.getNome());
                viaLocale.setText(l.getVia());
                titolo.setText(recensione.getTesto());
                testoR.setText(recensione.getTesto());
                votoQP.setRating(recensione.getVotoQP());
                votoS.setRating(recensione.getVotoServizio());
                votoC.setRating(recensione.getVotoCibo());
            }

            logo.setImageResource(R.drawable.disco);
        //    Integer[] cat= c.getSingleCategoriesFromValue(extras.getInt("categoria"));

        }
    }






    public void sumbitRecesione(View v){
        int votoQPi = (int) votoQP.getRating() ,votoSi=(int) votoS.getRating(),votoCi=(int)votoC.getRating();

        if(titolo.getText().length()==0) {
            Toast.makeText(this, "Scrivere un titolo alla recensione", Toast.LENGTH_SHORT).show();
            }else if(testoR.getText().length()==0) {
                Toast.makeText(this, "Scrivere un testo alla recensione", Toast.LENGTH_SHORT).show();
                 } else {
                    //URL ulr=new URL("http://10.0.0.2:8080/index.jsp?review=add&account="+id+"&testo="+testoR+"&titolo="+titolo+"&votoServizio="+votoS+"&votoQP="+votoQP+"&votoCibo="+votoC+"&idLocale="+localeID);
                    ClientRequest clientRequest = new ClientRequest(t);
                    if(l!=null){
                        int i=clientRequest.addReview(testoR.getText().toString(), titolo.getText().toString(), votoSi, votoQPi, votoCi, l.getID());
                    }else {
                        clientRequest.editReview(recensione.getId(),recensione.getLocaleID(),testoR.getText().toString(),titolo.getText().toString(),votoSi, votoQPi, votoCi,recensione.getVoto());
                    }

                }
                    Toast.makeText(this, "recensione iniviata", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);

        }
    }

