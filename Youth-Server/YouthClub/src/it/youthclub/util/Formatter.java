package it.youthclub.util;


import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import it.youthclub.model.Locali.Locale;
import it.youthclub.model.Recensioni.Recensione;

public class Formatter {
  
   private List<Locale> lst;
   
   public Formatter(List<Locale> lst) {
	   this.lst=lst;
   }
   
   
   
   
   public JSONObject getJSON() {
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
		   localeJSON.put("totaleVoti", loc.getTotVoti());
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
   
   
}
