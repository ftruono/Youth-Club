package it.youthclub;

import it.youthclub.api.Geocoding;
import it.youthclub.model.places.Place;

public class SearchController {

	private String search,name;
	private int category;
	private boolean mode; //true per luogo , false gps
	private Geocoding geo;
	public SearchController(String search,String name,int category,boolean mode) {
		this.search=search;
		this.name=name;
		this.category=category;
		this.mode=mode;
		
		
	}
	
	/**
	 * Esegue la ricerca e formatta i dati.
	 */
	public void search() {
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
		parallel.search(p,category);
		
		
		
	}
	
	
}
