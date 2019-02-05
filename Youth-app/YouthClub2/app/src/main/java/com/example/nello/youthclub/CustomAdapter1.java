package com.example.nello.youthclub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import it.youthclub.beans.Locale;

public class CustomAdapter1 extends ArrayAdapter<Locale> {
    private LayoutInflater inflater;
    private Locale locale;
    private TextView nome,via;
    private ImageView img;
    public View getView(int position, View v, ViewGroup parent){
        if(v==null){
            v=inflater.inflate(R.layout.layout_locale, null);
        }
        nome= v.findViewById(R.id.nome_locale);
        via=v.findViewById(R.id.via_locale);
        img=v.findViewById(R.id.logo_locale2);
        locale=getItem(position);
        nome.setText(locale.getNome());
        via.setText(locale.getVia());
        img.setImageResource(R.drawable.ic_audiotrack);
        v.setTag(locale);
        return v;
    }


    public  CustomAdapter1(Context context, int resource, List<Locale> objects){
        super(context, resource,objects);
        inflater=LayoutInflater.from(context);
    }
}
