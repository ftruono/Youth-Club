package com.example.nello.youthclub;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class DecodJson {

    private JSONObject obj;
    private JSONObject recensione;
    public List<BeanLocale> DecodJson() {
        try {
            List<BeanLocale> loc=new LinkedList<>();
            JSONArray results=obj.getJSONArray("results");
            for(int i=0;i<results.length();++i) {
                JSONObject locale=results.getJSONObject(i);
                BeanLocale l=new BeanLocale();
                BeanRecensione r=new BeanRecensione();

                l.setID(locale.getInt("id"));
                l.setIdApi(locale.getString("id_api"));
                l.setFonte(locale.getString("fonte"));
                l.setPlaceID(locale.getInt("id_place"));
                l.setNome(locale.getString("nome"));
                l.setVia(locale.getString("via"));
                l.setPhone(locale.getString("numero_cellulare"));
                l.setNumeroVotanti(locale.getInt("numVotanti"));
                l.setTotVoti(locale.getInt("totVoti"));
                l.setLatitudine(Float.parseFloat(locale.getString("lat")));
                l.setLongitudine(Float.parseFloat(locale.getString("lng")));

                    JSONArray recensioneArray= locale.getJSONArray("recensione");
                    recensione=results.getJSONObject(i);

                    r.setId(recensione.getInt("id"));
                    r.setAccountID(recensione.getString("accountID"));
                    r.setTesto(recensione.getString("testo"));
                    r.setTitoloRecensione(recensione.getString("titoloRecensione"));
                    r.setVoto(Float.parseFloat(recensione.getString("voto")));
                    r.setVotoServizio(Integer.parseInt(recensione.getString("votoService")));
                    r.setVotoQP(Integer.parseInt(recensione.getString("votoQP")));
                    r.setVotoCibo(Integer.parseInt(recensione.getString("VotoCibo")));
                    loc.add(l);


            }
            return loc;
        } catch (JSONException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }


}
