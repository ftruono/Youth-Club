package it.youthclub.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;


import com.example.nello.youthclub.R;

import java.util.List;

import it.youthclub.beans.Recensione;

public class CustomAdapterRecensione1 extends ArrayAdapter<Recensione> {
    private LayoutInflater inflater;
    private RatingBar stelle;
    private TextView titolo,testo;
    private Button modifica;
    private String imei;

    public View getView(int position, View v, ViewGroup parent){
        if(v==null){
            v=inflater.inflate(R.layout.layout_recensione, null);
        }
        stelle=v.findViewById(R.id.ratingBar);
        titolo=v.findViewById(R.id.titolo_recensione);
        testo=v.findViewById(R.id.testo_recensione);
        Recensione rec=getItem(position);
        stelle.setRating(rec.getVoto());
        titolo.setText(rec.getTitoloRecensione());
        testo.setText(rec.getTesto());
        modifica=v.findViewById(R.id.modifica_recensione);
        modifica.setTag(rec);
        if(!rec.getAccountID().equals(imei)){
            modifica.setVisibility(View.INVISIBLE);
        }

        return v;


    }
    public  CustomAdapterRecensione1(Context context, int resource, List<Recensione> objects, String imei){
        super(context, resource, objects);
        this.imei=imei;
        inflater = LayoutInflater.from(context);
    }
}
