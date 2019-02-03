package com.example.nello.youthclub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;

public class EditRecensione extends AppCompatActivity {
    private int id;//id utente
    private int localeID;
    private BeanRecensione recensione=null;
    private TextView nomeLocale,viaLocale;
    private EditText titolo,testoR;
    private RatingBar votoT,votoQP,votoS,votoC;// float voto int votoServizio int votoQP int votoCibo;
    private boolean scrivi=true;//true=scrivi false=modifica
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recensione);

        nomeLocale = findViewById(R.id.nome_locale);
        viaLocale = findViewById(R.id.via_locale);
        votoT = findViewById(R.id.VotoComplessivo);
        votoQP = findViewById(R.id.VotoQP);
        votoS = findViewById(R.id.VotoServizio);
        votoC = findViewById(R.id.VotocCibo);
        titolo = findViewById(R.id.editTextNomeRecensione);
        testoR = findViewById(R.id.editTextTestoRecensione);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id_utente");
            localeID = extras.getInt("id_locale");
            nomeLocale.setText(extras.getString("nomelocale"));
            viaLocale.setText(extras.getString("via"));

            if (scrivi = extras.getBoolean("valoreRec")) {
                recensione = (BeanRecensione) extras.getSerializable("recensione");
                titolo.setText(recensione.getTesto());
                testoR.setText(recensione.getTesto());
                votoT.setRating(recensione.getVoto());
                votoQP.setRating(recensione.getVotoQP());
                votoS.setRating(recensione.getVotoServizio());
                votoC.setRating(recensione.getVotoCibo());
            }
        }
    }


//TODO controllare il comportamento del server quando modifico una recensione



    public void sumbitRecesione(View v){

        if(titolo.getText().length()==0) {
            Toast.makeText(this, "Scrivere un titolo alla recensione", Toast.LENGTH_SHORT).show();

        }else if(testoR.getText().length()==0) {
            Toast.makeText(this, "Scrivere un testo alla recensione", Toast.LENGTH_SHORT).show();

        } else {
            //URL ulr=new URL("http://youthclub.ddns.net:8080/index.jsp?review=add&account="+id+"&testo="+testoR+"&titolo="+titolo+"&votoServizio="+votoS+"&votoQP="+votoQP+"&votoCibo="+votoC+"&idLocale="+localeID);
            //TODO connesione al server
            Toast.makeText(this, "recensione iniviata", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
    }
}
