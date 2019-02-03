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

public class CustomAdapterRecensione1 extends ArrayAdapter<BeanRecensione> {
    private LayoutInflater inflater;
    private RatingBar stelle;
    private TextView titolo,testo;
    private Button modifica;


    public View getView(int position, View v, ViewGroup parent){
        if(v==null){
            v=inflater.inflate(R.layout.layout_recensione, null);
        }
        stelle=v.findViewById(R.id.ratingBar);
        titolo=v.findViewById(R.id.titolo_recensione);
        testo=v.findViewById(R.id.testo_recensione);
        BeanRecensione rec=getItem(position);
        stelle.setRating(rec.getVoto());
        titolo.setText(rec.getTitoloRecensione());
        testo.setText(rec.getTesto());
        v.setTag(rec);

        return v;
    }
    public  CustomAdapterRecensione1 (Context context, int resource, List<BeanRecensione> objects){
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
    }
}
