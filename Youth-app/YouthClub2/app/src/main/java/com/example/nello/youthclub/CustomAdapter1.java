package com.example.nello.youthclub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter1 extends ArrayAdapter<BeanLocale> {
    private LayoutInflater inflater;
    private BeanLocale locale;
    private TextView nome,via;
    private ImageView img;
    public View getView(int position, View v, ViewGroup parent){
        if(v==null){
            v=inflater.inflate(R.layout.layout_locale, null);
        }
        nome= v.findViewById(R.id.nome_locale);
        via=v.findViewById(R.id.via_locale);
        img=v.findViewById(R.id.logo_locale);
        locale=getItem(position);
        nome.setText(locale.getNome());
        via.setText(locale.getVia());
        v.setTag(locale);
        return v;
    }


    public  CustomAdapter1(Context context, int resource, List<BeanLocale> objects){
        super(context, resource,objects);
        inflater=LayoutInflater.from(context);
    }
}
