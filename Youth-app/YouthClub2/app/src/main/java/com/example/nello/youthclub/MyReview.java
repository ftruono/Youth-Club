package com.example.nello.youthclub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyReview extends AppCompatActivity {
    private ListView lista;
    private String imei;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_review);

        lista=findViewById(R.id.lista_tue_recensioni);
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            imei=extras.getString("imei");
        }

        CustomAdapterRecensione1 customAdapterRecensione1=new CustomAdapterRecensione1(this,R.layout.layout_recensione,new ArrayList<BeanRecensione>());
        lista.setAdapter(customAdapterRecensione1);
        //TODO da sostituire con le vere recensioni
        for(int i=0;i<20;i++){
            BeanRecensione beanRecensione=new BeanRecensione(i,"x",0,"xx","yy",1,2,3,4);
            customAdapterRecensione1.add(beanRecensione);
        }
    }
}
