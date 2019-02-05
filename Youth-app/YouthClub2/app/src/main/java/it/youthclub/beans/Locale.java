package it.youthclub.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Locale implements Serializable {
    private int id;
    private int idPlace;
    private String nome;
    private String via;
    private String numeroCellulare;
    private float mediaVoto;
    private float lat,lng;
    private int category;
    private String fonte;
    private List<Recensione> recensioni;
    private String id_api;




    public Locale(int id, int idPlace, String nome, String via, String numeroCellulare, int mediaVoto,
                  float lat, float lng, int category, String fonte, String id_api) {

        this.id=id;
        this.idPlace = idPlace;
        this.nome = nome;
        this.via = via;
        this.numeroCellulare = numeroCellulare;
        this.mediaVoto = mediaVoto;
        this.lat = lat;
        this.lng = lng;
        this.category = category;
        this.fonte = fonte;
        this.recensioni = new ArrayList<>();
        this.id_api = id_api;
    }

    public Locale() {
        numeroCellulare="";
        mediaVoto=0;
        via="";
        this.recensioni = new ArrayList<>();
    }
//Fonte
    public String getFonte() {
        return fonte;
    }
    public void setFonte(String fonte) {
        this.fonte = fonte;
    }
//IdApi
    public String getIdApi() {
        return id_api;
    }
    public void setIdApi(String id_api) {
        this.id_api = id_api;
    }
    //Id
    public int getID() {
        return id;
    }
    public void setID(int iD) {
        id = iD;
    }
//PlaceID
    public int getPlaceID() {
        return idPlace;
    }
    public void setPlaceID(int idPlace) {
        this.idPlace=idPlace;
    }
//nome
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
//Via
    public String getVia() {
        return via;
    }
    public void setVia(String via) {
        this.via = via;
    }
//NumeroCellulare
    public String getPhone() {
        return numeroCellulare;
    }
    public void setPhone(String telefono) {
        numeroCellulare = telefono;
    }
//voto
    public float getMediaVoto() {
        return mediaVoto;
    }
    public void setMediaVoto(float nv) {
        mediaVoto = nv;
    }

//Latidudine Log
    public float getLatitudine() {
        return lat;
    }
    public void setLatitudine(float lat) {
        this.lat = lat;
    }
    public float getLongitudine() {
        return lng;
    }
    public void setLongitudine(float lng) {
        this.lng = lng;
    }
//categoria
    public int getCategory() {
        return category;
    }
    public void setCategory(int category) {
        this.category = category;
    }



    public boolean addReview(Recensione s) {
        return recensioni.add(s);
    }
    public List<Recensione> getRecensioni() {
        return recensioni;
    }
    @Override
    public int hashCode() {
        return 31*17+nome.toLowerCase().hashCode();
    }

    //TODO equals Nome(lower case tutto) lat lng
    //TODO eqauls toString clone


}
