package com.example.nello.youthclub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class Recensione extends AppCompatActivity {
    private int votazione;
    private String  titolo,testo;

    public Recensione(int votazione, String titolo, String testo) {
        this.votazione = votazione;
        this.titolo = titolo;
        this.testo = testo;
    }

    public int getVotazione() {
        return votazione;
    }

    public void setVotazione(int votazione) {
        this.votazione = votazione;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recensione);



    }
}
