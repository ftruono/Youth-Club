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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ClientRequest  {
    List<BeanLocale> p;
    List<BeanLocale> lista;
    private URL url;
    private int code;


    private int mod=0;//0=autenticator 1=ricerca per luogo 2=ricerca per gps 3=ricerca per nome
                      //4=aggiunta della recensione 5=modifica della recensione 6=ricerca della recensione
    public ClientRequest() {

    }




    public void autenticator(String imei,int mod){
        try {

            url = new URL("http://10.0.2.2:8080/YouthClub/index.jsp?auth="+imei);
            // http://localhost:8080//YouthClub/
            this.mod=mod;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        NetworkTask networkTask=new NetworkTask();
        networkTask.execute(String.valueOf(url));
    }

    //paese o coordinate gps
    public List<BeanLocale>  search(final String luogo, final int cat,int mod) {//0 GPS 1 string
        try {
             url = new URL("http://10.0.2.2:8080/YouthClub/index.jsp?search="+luogo+",italia"+"&mode=1&cat="+cat);
            // http://localhost:8080//YouthClub/
            this.mod=mod;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        NetworkTask networkTask=new NetworkTask();
        networkTask.execute(String.valueOf(url));

        return lista;

    }

    //Ricerca usando latidune e logitudine del gps
    public List<BeanLocale> search(Float lat,Float lng,int cat,int mod){//0 gps 1 string
        try {
             url = new URL("http://10.0.2.2:8080/YouthClub/index.jsp?search="+lat+","+lng+"&cat="+cat+"&mod=1");
           // http://localhost:8080//YouthClub/
            this.mod=mod;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        NetworkTask networkTask=new NetworkTask();
        networkTask.execute(String.valueOf(url));

        return lista;
    }

    public List<BeanLocale> search(String luogo,int mod){
        //URL http://youthclub.ddns.net:8080/index.jsp?name=[ricerca]
        try {
            url = new URL("http://10.0.2.2:8080/YouthClub/index.jsp?name="+luogo);
            this.mod=mod;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        NetworkTask networkTask=new NetworkTask();
        networkTask.execute(String.valueOf(url));

        return lista;
    }

    public int addReview(BeanRecensione recensione,int mod){
        try {
            url=new URL("http://youthclub.ddns.net:8080/index.jsp?review=add&account="+recensione.getAccountID()+"&testo="+recensione.getTesto()+"&titolo="+recensione.getTitoloRecensione()+"&votoServizio="+recensione.getVotoServizio()+"&votoQP="+recensione.getVotoQP()+"&votoCibo="+recensione.getVotoCibo()+"&idLocale="+recensione.getLocaleID());
            this.mod=mod;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        NetworkTask networkTask=new NetworkTask();
        networkTask.execute(String.valueOf(url));
        return code;
    }




    public List<BeanLocale> decod(JSONObject obj) {
        try {
            List<BeanLocale> loc=new LinkedList<>();
            JSONArray results=obj.getJSONArray("results");
            for(int i=0;i<results.length();++i) {
                JSONObject locale=results.getJSONObject(i);
                BeanLocale l=new BeanLocale();
                BeanRecensione r=new BeanRecensione();

                if (locale.getInt("id")>0){
                    l.setID(locale.getInt("id"));
                }else {
                    l.setID(0);
                }
                if (locale.getString("id_api").equals(null)){
                    l.setIdApi(null);
                }else {
                    l.setIdApi(locale.getString("id_api"));
                }
                if (locale.getString("fonte").equals(null)){
                    l.setFonte(null);
                }else {
                    l.setFonte(locale.getString("fonte"));
                }
                if (locale.getInt("id_place")>0){
                    l.setPlaceID(locale.getInt("id_place"));
                }else {
                    l.setPlaceID(0);
                }
                if (locale.getString("nome").equals(null)){
                    l.setNome(null);
                }else {
                    l.setNome(locale.getString("nome"));
                }
                if (locale.getString("via").equals(null)){
                    l.setVia(null);
                }else {
                    l.setVia(locale.getString("via"));
                }
                if (locale.getString("numero_cellulare").equals(null)){
                    l.setPhone(null);
                }else {
                    l.setPhone(locale.getString("numero_cellulare"));
                }
                if (locale.getInt("numRecensioni")>0){
                    l.setMediaVoto(locale.getInt("media_recensioni"));
                }else {
                    l.setMediaVoto(0);
                }

                if (Float.parseFloat(locale.getString("lat"))>0){
                    l.setLatitudine(Float.parseFloat(locale.getString("lat")));
                }else {
                    l.setLatitudine(0);
                }
                if (Float.parseFloat(locale.getString("lng"))>0){
                    l.setLongitudine(Float.parseFloat(locale.getString("lng")));
                }else {
                    l.setLongitudine(0);
                }

                //effettua il controllo se il locale trovato prima è lo stesso dell'attuale
                //nel caso in cui fosse lo stesso unisce i dati
                if(loc.get(i-1).getNome().equals(l.getNome())){
                    //TODO da completare la copia
                }

                   JSONArray recensioneArray= locale.getJSONArray("recensioni");
                    if(recensioneArray.length()>0){
                        JSONObject recensione = results.getJSONObject(i);
                        r.setId(recensione.getInt("id"));
                        r.setAccountID(recensione.getString("accountID"));
                        r.setTesto(recensione.getString("testo"));
                        r.setTitoloRecensione(recensione.getString("titoloRecensione"));
                        r.setVoto(Float.parseFloat(recensione.getString("voto")));
                        r.setVotoServizio(Integer.parseInt(recensione.getString("votoService")));
                        r.setVotoQP(Integer.parseInt(recensione.getString("votoQP")));
                        r.setVotoCibo(Integer.parseInt(recensione.getString("VotoCibo")));
                    }



                loc.add(l);


            }
            return loc;
        } catch (JSONException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }



    class NetworkTask extends AsyncTask<String, Integer, List<BeanLocale>> {

            @Override
            protected void onPreExecute() {
            }

            @Override
            protected List<BeanLocale> doInBackground(String... values) {

                switch (mod){
                    case 0:
                        CookieManager cookieManager = new CookieManager();

                        try {
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");
                            Map<String, List<String>> headerFields = connection.getHeaderFields(); //recupero i campi
                            List<String> cookiesHeader = headerFields.get("Set-Cookie"); //prendo solo i cookies

                            if (cookiesHeader != null) {
                                for (String cookie : cookiesHeader) {
                                    System.out.println(cookie);
                                    cookieManager.getCookieStore().add(null,HttpCookie.parse(cookie).get(0));
                                }

                                //ottengo il jsessionid e lo riutilizzo per le volte successive!
                            }

                        } catch (ProtocolException e1) {
                            e1.printStackTrace();
                        } catch (MalformedURLException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        break;
                    case 1:
                        try {
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        BufferedReader read=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String tot="";
                        String single="";
                        while( (single=read.readLine()) !=null) {
                            tot+=single;
                        }
                        read.close();
                        connection.disconnect();
                        JSONObject obj=new JSONObject(tot);
                        lista=decod(obj);
                        return lista;
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    case 2:
                        try {
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            BufferedReader read=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                            String tot="";
                            String single="";
                            while( (single=read.readLine()) !=null) {
                                tot+=single;
                            }
                            read.close();
                            connection.disconnect();
                            JSONObject obj=new JSONObject(tot);
                            lista=decod(obj);
                            return lista;
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                     case 3:
                         try {
                             HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                             BufferedReader read=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                             String tot="";
                             String single="";
                             while( (single=read.readLine()) !=null) {
                                 tot+=single;
                             }
                             read.close();
                             connection.disconnect();
                             JSONObject obj=new JSONObject(tot);
                             lista=decod(obj);
                             return lista;

                         } catch (MalformedURLException e) {
                             e.printStackTrace();
                         } catch (IOException e) {
                             e.printStackTrace();
                         } catch (JSONException e) {
                             e.printStackTrace();}
                        break;



                }



                return null;
            }



            protected void onPostExecute(JSONObject obj) {

                lista=decod(obj);

            }
        }

    }
