package com.example.nello.youthclub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;


import java.util.List;

public class CustomAdapterRecensione1 extends ArrayAdapter<Recensione> {
    private LayoutInflater inflater;
    private RatingBar stelle;
    private TextView titolo,testo;
    private Button modifica;


    public View getView(int position, View v, ViewGroup parent){
        if(v==null){
            v=inflater.inflate(R.layout.activity_locale, null);
        }
        stelle=v.findViewById(R.id.ratingBar);
        titolo=v.findViewById(R.id.titolo_recensione);
        testo=v.findViewById(R.id.testo_recensione);
        Recensione rec=getItem(position);
        stelle.setNumStars(rec.getVotazione());
        titolo.setText(rec.getTitolo());
        testo.setText(rec.getTesto());
        v.setTag(rec);
        return v;
    }
    public  CustomAdapterRecensione1 (Context context, int resource, List<Recensione> objects){
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
    }
}
