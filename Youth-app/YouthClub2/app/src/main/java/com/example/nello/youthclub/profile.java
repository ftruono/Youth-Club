package com.example.nello.youthclub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class profile extends AppCompatActivity {
    private ImageView img_profile;
    private TextView name,telefono,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name=findViewById(R.id.my_name);
        telefono=findViewById(R.id.my_phone);
        email=findViewById(R.id.my_email);

        name.setText("Vincenzo liguorino");
        telefono.setText("3333176760");
        email.setText("petro@gmail.com");
    }




}
