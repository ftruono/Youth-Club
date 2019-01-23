package com.example.nello.youthclub;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Locale extends AppCompatActivity {
    private Drawable imageView;
    private String nomeView;
    private String viaView;



    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locale);


    }

    public Drawable getImageView() {
        return imageView;
    }

    public Locale(Drawable imageView, String nomeView, String viaView, String id) {
        this.imageView = imageView;
        this.nomeView = nomeView;
        this.viaView = viaView;
        this.id = id;
    }

    public void setImageView(Drawable imageView) {
        this.imageView = imageView;
    }

    public String getNomeView() {
        return nomeView;
    }

    public void setNomeView(String nomeView) {
        this.nomeView = nomeView;
    }

    public String getViaView() {
        return viaView;
    }

    public void setViaView(String viaView) {
        this.viaView = viaView;
    }
}
