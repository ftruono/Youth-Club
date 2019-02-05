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


    public ClientRequest(Utente t) {
        this.user=t;
    }




    public void autenticator(){
        try {

            URL url = new URL("http://10.0.2.2:8080/YouthClub/index.jsp?auth="+ user.getImei());
            // http://localhost:8080//YouthClub/

            Task tsk=new Task(Task.Operation.AUTENTICAZIONE,"GET",user);
            tsk.execute(url);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    //paese o coordinate gps
    public List<Locale>  search(final String luogo, final int cat, int mod) {//0 GPS 1 string
        try {
             List<Locale> locali=new ArrayList<>();
             URL url = new URL("http://10.0.2.2:8080/YouthClub/index.jsp?search="+luogo+"&mode=1&cat="+cat);
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
        return null;

    }

    //Ricerca usando latidune e logitudine del gps
    public List<Locale> search(Float lat, Float lng, int cat, int mod){//0 gps 1 string
        try {
             URL url = new URL("http://10.0.2.2:8080/YouthClub/index.jsp?search="+lat+","+lng+"&cat="+cat+"&mode=1");
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

    public List<Locale> search(String luogo, int mod){
        //URL http://youthclub.ddns.net:8080/index.jsp?name=[ricerca]
        try {
            if (!luogo.contains(",")) return null;
            URL url = new URL("http://10.0.2.2:8080/YouthClub/index.jsp?name="+luogo);
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

    public int addReview(String account,String testo,String titolo,int votoS,int votoQP,int votoCibo,int idL){

        URL url= null;
        try {
            url = new URL("http://10.0.0.2:8080/index.jsp?review=add&account=" + account + "&testo=" + testo +
            "&titolo=" + titolo + "&votoServizio=" +  votoS + "&votoQP=" + votoQP + "&votoCibo=" + votoCibo + "&idLocale=" + idL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Task t1=new Task(Task.Operation.RICERCA_RECENSIONE,"POST",user);
         t1.execute(url);

        try { JSONObject o=t1.get();


            int x=decodReview(t1.get());
            return x;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int editReview(int id,int idL,String testo,String titolo,int votoS,int votoQP,int votoCibo,float oldVote){
        URL url= null;
        try {
            url = new URL("http://10.0.0.2:8080/index.jsp?review=edit&id=" + id + "&testo=" + testo +
                    "&titolo=" + titolo + "&votoServizio=" +  votoS + "&votoQP=" + votoQP + "&votoCibo=" + votoCibo + "&idLocale=" + idL );
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
            url = new URL("http://10.0.0.2:8080/index.jsp?review=get&account=" + user.getImei());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Task t1=new Task(Task.Operation.RICERCA_RECENSIONE,"POST",user);
        t1.execute(url);
        try {
            t1.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null; //TODO scrivere il rispettivo metodo di decode!
    }




   private int decodeJSONReview(JSONObject obj){
       try {
           JSONObject response=obj.getJSONObject("response");
           return obj.getInt("code");
       } catch (JSONException e) {

       }

       return 0;
   }

    private List<Locale> decodeJSON(JSONObject obj) {
        try {
            //TODO: controlli duplicati e errore di DecodeException (sfrutta throws)!
            List<Locale> loc=new LinkedList<>();
            JSONArray results=obj.getJSONArray("results");

            for(int i=0;i<results.length();++i) {
                JSONObject locale=results.getJSONObject(i);
                Locale l=new Locale();
                l.setPlaceID(locale.getInt("id_place"));
                l.setCategory(locale.getInt("categoria"));
                l.setPhone(locale.getString("numero_cellulare"));
                l.setMediaVoto(locale.getLong("voto"));
                l.setLongitudine(locale.getLong("lng"));
                l.setLatitudine(locale.getLong("lat"));
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
                        r.setVotoCibo(Integer.parseInt(recensione.getString("VotoCibo")));
                        l.addReview(r);
                    }
                loc.add(l);
            }
            return loc;
        } catch (JSONException e) {
            System.out.println(e.getMessage());

        }
        return null;

    }

    public int decodReview(JSONObject obj){
        try {
            JSONArray results=obj.getJSONArray("response");
            for(int i=0;i<results.length();++i) {
                JSONObject risposta=results.getJSONObject(i);
               return risposta.getInt("code");

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }






}
