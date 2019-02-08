package com.example.nello.youthclub;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import it.youthclub.activity.MainActivity;

public class Splash extends AppCompatActivity {

    private ImageView immagine;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        immagine = findViewById(R.id.foto);

        immagine.setImageResource(R.mipmap.ic_launcher_foreground);

        int secondsDelayed = 3;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(Splash.this, MainActivity.class));
                finish();
            }
        }, secondsDelayed * 1000);
    }

}
