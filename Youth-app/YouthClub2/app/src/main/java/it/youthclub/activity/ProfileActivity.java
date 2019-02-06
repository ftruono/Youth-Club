package it.youthclub.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nello.youthclub.R;

import it.youthclub.beans.Utente;

public class ProfileActivity extends AppCompatActivity {
    private ImageView img_profile;
    private TextView name,telefono;
    private Utente t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name=findViewById(R.id.my_name);
        telefono=findViewById(R.id.my_phone);
        Bundle d=getIntent().getExtras();
        if(d!=null){
            t=(Utente)d.getSerializable("utente");

        }

        name.setText(t.getImei());
        telefono.setText(t.getJSESSION());

    }




}
