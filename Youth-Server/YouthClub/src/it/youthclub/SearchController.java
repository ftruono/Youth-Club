package it.youthclub;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.json.JSONObject;

import it.youthclub.api.Geocoding;
import it.youthclub.model.Locali.Locale;
import it.youthclub.model.places.Place;
import it.youthclub.util.Formatter;

public class SearchController {

	private String search,name;
	private int category;
	private boolean mode; //true per luogo , false gps
	private Geocoding geo;
	private Formatter formatjson;
	public SearchController(String search,int category,boolean mode) {
		this.search=search;
		name=null;
		this.category=category;
		this.mode=mode;
		
		
	}
	
	public SearchController(String name) {
	   mode=true;
	   try {
		   this.name=name.substring(0, name.indexOf(",")).toLowerCase();
		   search=name.substring(name.indexOf(",")+1,name.length());
	   }catch(IndexOutOfBoundsException e) {
		   //deve comunicare un errore al client 
	   }
	}
	
	/**
	 * Esegue la ricerca e formatta i dati.
	 */
	public JSONObject search() {
		Geocoding geo=null;
		if(!mode) {
			String lat=search.substring(0,search.indexOf(","));
			String lng=search.substring(search.indexOf(",")+1,search.length());
			System.out.println(lat + " " + lng);
			try {
				geo=new Geocoding(Float.parseFloat(lat),Float.parseFloat(lng));
			}catch(NumberFormatException e) {
				
			}
			
		}else {
			geo=new Geocoding(search);
			
		}
		Place p=geo.searchAndLearn();
		ParallelingSearch parallel=new ParallelingSearch();
		List<Locale> lst=parallel.search(p,category);
		if(name!=null) { //significa che è una ricerca per nome
			ArrayList<Locale> names=new ArrayList<Locale>();
			for(Locale l : lst){
				if(l.getNome().toLowerCase().equals(name))
					names.add(l);
			}
			System.out.println(names.size());
			formatjson=new Formatter(names);
			return formatjson.getJSON();
			
	   }
	   formatjson=new Formatter(lst);
	   return formatjson.getJSON();
	 //Formatta i dati
	
	
	}
	
}
