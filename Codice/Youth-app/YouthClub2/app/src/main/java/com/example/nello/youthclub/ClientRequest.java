package com.example.nello.youthclub;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import it.youthclub.beans.Locale;
import it.youthclub.beans.Recensione;
import it.youthclub.beans.Utente;
import it.youthclub.processing.Task;

public class ClientRequest  {

    private Utente user;
    private static final String LINK="http://10.0.2.2:8080/YouthClub/index.jsp?";

    public ClientRequest(Utente t) {
        this.user=t;
    }




    public void autenticator(){
        try {

            URL url = new URL(LINK + "auth="+ user.getImei());
            // http://localhost:8080//YouthClub/

            Task tsk=new Task(Task.Operation.AUTENTICAZIONE,"GET",user);
            tsk.execute(url);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    //paese o coordinate gps
    public List<Locale>  search(final String luogo, final int cat) {//0 GPS 1 string
        try {
             List<Locale> locali=new ArrayList<>();
             URL url = new URL(LINK + "search="+luogo+"&mode=1&cat="+cat);
             // http://localhost:8080//YouthClub/
            Task t1=new Task(Task.Operation.RICERCA_RECENSIONE,"GET",user);
            t1.execute(url);
            return decodeJSON(t1.get());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("erroryouth","sto dando null");
        return null;

    }

    //Ricerca usando latidune e logitudine del gps
    public List<Locale> search(Float lat, Float lng, int cat){
        try {
             URL url = new URL(LINK + "search="+lat+","+lng+"&cat="+cat+"&mode=1");
             Task t1=new Task(Task.Operation.RICERCA_RECENSIONE,"GET",user);
             t1.execute(url);
             return decodeJSON(t1.get());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Locale> search(String name){
        //URL http://youthclub.ddns.net:8080/index.jsp?name=[ricerca]
        try {
            if (!name.contains(",")) return null;
            URL url = new URL(LINK + "name="+name);
            Task t1=new Task(Task.Operation.RICERCA_RECENSIONE,"GET",user);
            t1.execute(url);
            return decodeJSON(t1.get());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int addReview(String testo,String titolo,int votoS,int votoQP,int votoCibo,int idL){

        URL url= null;
        try {
            url = new URL(LINK +"review=add&account=" + user.getImei() + "&testo=" + testo +
            "&titolo=" + titolo + "&votoServizio=" +  votoS + "&votoQP=" + votoQP + "&votoCibo=" + votoCibo + "&idLocale=" + idL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Task t1=new Task(Task.Operation.RICERCA_RECENSIONE,"POST",user);
         t1.execute(url);

        try {
            JSONObject o=t1.get();
            return decodeJSONReview(t1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public synchronized int editReview(int id,int idL,String testo,String titolo,int votoS,int votoQP,int votoCibo,float oldVote){
        URL url= null;
        try {
            url = new URL(LINK +"review=edit&id=" + id + "&testo=" + testo +
                    "&titolo=" + titolo + "&votoServizio=" +  votoS + "&votoQP=" + votoQP + "&votoCibo=" + votoCibo + "&idLocale=" + idL + "&old=" + oldVote);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Task t1=new Task(Task.Operation.RICERCA_RECENSIONE,"POST",user);
        t1.execute(url);

        try {
            return decodeJSONReview(t1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Recensione> getReviewsByAccount(){

        URL url= null;
        try {
            url = new URL(LINK+"review=get&account=" + user.getImei());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Task t1=new Task(Task.Operation.RICERCA_RECENSIONE,"POST",user);
        t1.execute(url);

        try {
           return decodeJSONReviews(t1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<Recensione> decodeJSONReviews(JSONObject obj){
        JSONArray results= null;
        ArrayList<Recensione> list=new ArrayList<>();
        try {
            results = obj.getJSONArray("results");
            for(int i=0;i<results.length();++i){
                Recensione rec=new Recensione();
                JSONObject recJSON=results.getJSONObject(i);
                rec.setId(recJSON.getInt("id"));
                rec.setLocaleID(recJSON.getInt("localeID"));
                rec.setAccountID(recJSON.getString("accountID"));
                rec.setTesto(recJSON.getString("testo"));
                rec.setTitoloRecensione(recJSON.getString("titoloRecensione"));
                rec.setVoto(Float.parseFloat(recJSON.getString("voto")));
                rec.setVotoQP(Integer.parseInt(recJSON.getString("votoQP")));
                rec.setVotoCibo(Integer.parseInt(recJSON.getString("votoCibo")));
                rec.setVotoServizio(Integer.parseInt(recJSON.getString("votoService")));

                list.add(rec);

            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }




      return null;
    }


   private int decodeJSONReview(JSONObject obj){
       try {
           JSONObject response=obj.getJSONObject("response");
           return obj.getInt("code");
       } catch (JSONException e) {

       }

       return -1;
   }

    private List<Locale> decodeJSON(JSONObject obj) {
        try {
            //TODO: controlli duplicati e errore di DecodeException (sfrutta throws)!
            List<Locale> loc=new ArrayList<>();
            JSONArray results=obj.getJSONArray("results");

            for(int i=0;i<results.length();++i) {
                JSONObject locale=results.getJSONObject(i);
                Locale l=new Locale();
                l.setPlaceID(locale.getInt("id_place"));
                l.setCategory(locale.getInt("categoria"));
                l.setPhone(locale.getString("numero_cellulare"));
                l.setMediaVoto(Float.parseFloat(locale.getString("voto")));
                l.setLongitudine(Float.parseFloat(locale.getString("lng")));
                l.setLatitudine(Float.parseFloat(locale.getString("lat")));
                l.setFonte(locale.getString("fonte"));
                l.setNome(locale.getString("nome"));
                l.setID(locale.getInt("id"));
                l.setIdApi(locale.getString("id_api"));
                l.setVia(locale.getString("via"));
                JSONArray recensioneArray= locale.getJSONArray("recensioni");
                    for (int j = 0; j < recensioneArray.length(); ++j) {
                        JSONObject recensione = recensioneArray.getJSONObject(j);
                        Recensione r = new Recensione();
                        r.setId(recensione.getInt("id"));
                        r.setAccountID(recensione.getString("accountID"));
                        r.setTesto(recensione.getString("testo"));
                        r.setTitoloRecensione(recensione.getString("titoloRecensione"));
                        r.setVoto(Float.parseFloat(recensione.getString("voto")));
                        r.setVotoServizio(Integer.parseInt(recensione.getString("votoService")));
                        r.setVotoQP(Integer.parseInt(recensione.getString("votoQP")));
                        r.setVotoCibo(Integer.parseInt(recensione.getString("votoCibo")));
                        l.addReview(r);
                    }
                int index=loc.indexOf(l);
                if(index>-1){
                   if(loc.get(index).compareTo(l)<0){
                       loc.remove(index);
                       loc.add(l);
                   }
                }else
                    loc.add(l);


            }
            return loc;
        } catch (JSONException e) {
            System.out.println(e.getMessage());

        }
        return null;

    }



}
