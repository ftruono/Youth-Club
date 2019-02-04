package it.youthclub.util;


import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

import it.youthclub.api.GeoException;
import it.youthclub.model.locali.Locale;
import it.youthclub.model.recensioni.Recensione;

public final class Formatter {
  
  
   
   private Formatter() {
	   
   }
   
   
   
   
   public static JSONObject getJSON(List<Locale> lst) {
	   JSONObject objF=new JSONObject();
	   JSONArray results=new JSONArray();
	   for(Locale loc:lst) {
		   JSONObject localeJSON=new JSONObject();
		   localeJSON.put("id",loc.getID());
		   localeJSON.put("id_api",loc.getIdApi());
		   localeJSON.put("fonte", loc.getFonte());
		   localeJSON.put("id_place", loc.getPlaceID());
		   localeJSON.put("nome", loc.getNome());
		   localeJSON.put("via",loc.getVia());
		   localeJSON.put("numero_cellulare", loc.getPhone());
		   localeJSON.put("numRecensioni",loc.getNumeroVotanti());
		   localeJSON.put("voto", loc.getVoto());
		   localeJSON.put("lat",loc.getLatitudine());
		   localeJSON.put("lng", loc.getLongitudine());
		   JSONArray recensioniJSONArray=new JSONArray();
		   for(Recensione rec:loc.getRecensioni()) {
			   JSONObject recensioniJSON=new JSONObject();
			   recensioniJSON.put("id",rec.getId());
			   recensioniJSON.put("accountID",rec.getAccountID());
			   recensioniJSON.put("testo",rec.getTesto());
			   recensioniJSON.put("titoloRecensione", rec.getTitoloRecensione());
			   recensioniJSON.put("voto",rec.getVoto());
			   recensioniJSON.put("votoService",rec.getVotoServizio());
			   recensioniJSON.put("votoQP",rec.getVotoQP());
			   recensioniJSON.put("votoCibo", rec.getVotoCibo());
			   recensioniJSONArray.put(recensioniJSON);
		   }
		   localeJSON.put("recensioni", recensioniJSONArray);
		   results.put(localeJSON);
		   
	   }
	   objF.put("results", results);
	   return objF;
   }
   
   public static JSONObject getJSON(ArrayList<Recensione> lst) {
	   JSONObject obj=new JSONObject();
	   JSONArray results=new JSONArray();
	   for(Recensione rec:lst) {
		   JSONObject recensioniJSON=new JSONObject();
		   recensioniJSON.put("id", rec.getId());
		   recensioniJSON.put("localeID", rec.getLocaleID());
		   recensioniJSON.put("accountID",rec.getAccountID());
		   recensioniJSON.put("testo",rec.getTesto());
		   recensioniJSON.put("titoloRecensione", rec.getTitoloRecensione());
		   recensioniJSON.put("voto",rec.getVoto());
		   recensioniJSON.put("votoService",rec.getVotoServizio());
		   recensioniJSON.put("votoQP",rec.getVotoQP());
		   recensioniJSON.put("votoCibo", rec.getVotoCibo());
		   results.put(recensioniJSON);
		   
	   }
	   obj.put("results", results);
	   
	   return obj;
   }
   
   
   public static JSONObject getJSON(GeoException e) {
	   JSONObject error=new JSONObject();
	   JSONObject obj=new JSONObject();
	   obj.put("code",e.getCode());
	   obj.put("message", e.getMessage());
	   error.put("error", obj);
	   return error;
   }
   
   
   public static JSONObject getJSON(boolean esito) {
	   JSONObject response=new JSONObject();
	   JSONObject obj=new JSONObject();
	   obj.put("code", esito ? 1 : -1);
	   response.put("response", obj);
	   return response;
	   
   }
   
   
}
