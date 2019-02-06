package it.youthclub.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nello.youthclub.R;

import java.util.List;
import java.util.Random;

import it.youthclub.beans.Categoria;
import it.youthclub.beans.Locale;

public class LocaliAdpter extends ArrayAdapter<Locale> {
    private LayoutInflater inflater;
    private Categoria cat;
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

        /*Integer[] x=cat.getSingleCategoriesFromValue(locale.getCategory());
        Random r=new Random(x.length-1);
        int value=r.nextInt();
        switch(value){
            case 0:
                img.setImageResource(R.drawable.disco);
                break;
            case 1:
                img.setImageResource(R.drawable.enoteca);
                break;
            case 2:
                img.setImageResource(R.drawable.ic_local_bar_black_24dp);
                break;
            case 3:
                img.setImageResource(R.drawable.pub);
                break;


        }*/

        v.setTag(locale);
        return v;
    }


    public LocaliAdpter(Context context, int resource, List<Locale> objects){
        super(context, resource,objects);
        inflater=LayoutInflater.from(context);
    }
}
