package it.youthclub.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import it.youthclub.model.Categoria;
import it.youthclub.model.Locali.Locale;
import it.youthclub.model.places.Place;

public class GoogleApi extends ApiProvider {

	
	
	public GoogleApi() {
		super("AIzaSyDrloWpBhPQ-OZ7MyfPRhLn2kFGEbjGTMU");
		mapped=getMapCategory();
		
	}
    
	
	
	
	
	
	@Override
	public List<Locale> search(Place p) {
	    List<Locale> finalList=new ArrayList<Locale>();
		String tags="";
		String query="";
		for(ItemApi it : mapped) {
			 for(String k:it.getKey())
			   tags+=k + " ";
			
		
	    query=tags + p.getName();
	    try {
	    	
			query=URLEncoder.encode(query,java.nio.charset.StandardCharsets.UTF_8.toString()).replace("+", "%20");
		} catch (UnsupportedEncodingException e1) {
			
		}
		String googleLink="https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + query + "&language=it&key=" + super.API_KEY; 
		HttpURLConnection connection = null;
		URL url;
		try {
			url = new URL(googleLink);
			connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("GET");
		    BufferedReader read=new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
		    String tot="";
		    String single="";
		    while( (single=read.readLine()) !=null) {
		    	tot+=single;
		    }
		    read.close();
			connection.disconnect();
			JSONObject obj=new JSONObject(tot);
			int id_p=p.getID();
			List<Locale> temp=decodeJSON(obj,id_p);
			if(temp!=null)
			 finalList.addAll(temp);
			//System.out.println("len :" + finalList.size() + " query=" + query);
			tags="";
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	   
	  }
	    System.out.println("len :" + finalList.size());
		return finalList;
	}
	
	
	
	
	private List<Locale> decodeJSON(JSONObject obj,int idplace){
		try {
			List<Locale> loc=new LinkedList<>(); 
			JSONArray results=obj.getJSONArray("results");
			for(int i=0;i<results.length();++i) {
				JSONObject locale=results.getJSONObject(i);
				String[] myCat=getMyCategoriesFromApi(locale.getJSONArray("types"));
				if(myCat.length>0) {
					Locale l=new Locale("google");
					l.setPlaceID(idplace);
					l.setIdApi(locale.getString("id"));
					l.setVia(locale.getString("formatted_address"));
					l.setNome(locale.getString("name"));
					l.setCategory(Categoria.getValueFromCategoriesNames(myCat));
					JSONObject location=locale.getJSONObject("geometry").getJSONObject("location");
					l.setLatitudine(location.getFloat("lat"));
					l.setLongitudine(location.getFloat("lng"));
					loc.add(l);
				}
				
			}
			return loc;
		}catch(JSONException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	
	protected String[] getMyCategoriesFromApi(JSONArray types){
			ArrayList<String> ar=new ArrayList<>(); 
			for(int i=0;i<types.length();++i) {
		    	String cat=types.getString(i);
		    	for(ItemApi item:mapped) {
		    		for(String key:item.getKey())
		    			if(key.equals(cat)) {
		    				ar.add(item.getValue());
		    			}
		    	}
		    	
		    }
			String s[]=new String[ar.size()];
			return ar.toArray(s);
	}
	
	
	

	/**
	 * Riadatta le categorie : "enoteca","pub","disco","bar" ai types di google.
	 * Google Types : https://developers.google.com/places/web-service/supported_types
	 * 
	 * 
	 */
	protected HashSet<ItemApi> getMapCategory() {
		HashSet<ItemApi> set=new HashSet<>();
		set.add(new ItemApi(new String[] {"bar","cafe"},Categoria.BAR));
		set.add(new ItemApi(new String[] {"food","restaurant"},Categoria.PUB));
		set.add(new ItemApi(new String[] {"nigth_club"},Categoria.DISCO));
		set.add(new ItemApi(new String[] {"bar"},Categoria.ENOTECA));
		return set;
		
	}
	
	

}
