package it.youthclub.processing;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import it.youthclub.beans.Utente;


public class Task extends AsyncTask<URL,Integer, JSONObject> {

     //eventuale enumeratore con i tipi di ricerca (?)
    public static enum Operation{
        RICERCA_RECENSIONE,AUTENTICAZIONE
    }
    private Operation mode;
    private String method;
    private Utente user;



    public Task(Operation op, String method, Utente t){
        this.method=method;
        this.mode=op;
        this.user=t;
    }






    @Override
    protected JSONObject doInBackground(URL... urls) {
        HttpURLConnection connection= null;
        try {

            connection =(HttpURLConnection)urls[0].openConnection();
            connection.setRequestProperty("Cookie",user.getJSESSION());
            connection.setRequestMethod(method);

        } catch (IOException e) {
            e.printStackTrace();
        }

        switch(mode){

            case RICERCA_RECENSIONE:

                try {
                    String tot,s;
                    tot="";
                    BufferedReader buff=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while((s=buff.readLine())!=null) {
                        tot+=s;
                    }
                    buff.close();
                    connection.disconnect();
                    return new JSONObject(tot);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                break;


            case AUTENTICAZIONE:
                Map<String, List<String>> headerFields = connection.getHeaderFields(); //recupero i campi
                if(headerFields!=null) {
                    String session = headerFields.get("Set-Cookie").get(0); //prendo solo i cookies
                    String js=session.substring(0,session.indexOf(";")+1);
                    user.setJSESSION(js);
                    connection.disconnect();
                    return null;
                }


        }

        return null;


    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        Log.d("thread","sono terminato");
        super.onPostExecute(jsonObject);
    }
}
