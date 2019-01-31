package it.youthclub.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import it.youthclub.model.Categoria;
import it.youthclub.model.locali.Locale;
import it.youthclub.model.places.Place;

public class YelpApi extends ApiProvider {

	public YelpApi() {
		super("nl-toKzHXki4xQi64-HjwVBTUs6LbcVcjbG1bq8mcwDpRETGVLE7UHCfOJNztZ3TkiDgcUqa92Y6JPs43pvsQ9eEMaOd1cA5DOvmx1EqHhadjVs81OjwXWpCS5XMW3Yx");
	}
	
	
	@Override
	public List<Locale> search(Place p) {
		String categories="";
		
		for(ItemApi it : mapped) {
			 for(String k:it.getKey())
			   categories+=k + ",";
		}	 
		int pos=categories.lastIndexOf(",");
		categories=categories.substring(0, pos);
		String query=p.getName();
		try {
	    	
			query=URLEncoder.encode(query,java.nio.charset.StandardCharsets.UTF_8.toString()).replace("+", "%20");
		} catch (UnsupportedEncodingException e1) {
			
		}
		String yelpLink="https://api.yelp.com/v3/businesses/search?location=" + query +  "&categories=" + categories + "&radius=39000";
		HttpURLConnection connection = null;
		URL url;
		try {
			url = new URL(yelpLink);
			connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("GET");
		    connection.setRequestProperty ("Authorization", "Bearer " + super.API_KEY);
		    BufferedReader read=new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
		    String tot="";
		    String single="";
		    while( (single=read.readLine()) !=null) {
		    	tot+=single;
		    }
		    read.close();
			connection.disconnect();
			int id_p=p.getID();
			JSONObject obj=new JSONObject(tot);
			return decodeJSON(obj,id_p);
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	
	
	private List<Locale> decodeJSON(JSONObject obj,int idplace){
		JSONArray businesses=obj.getJSONArray("businesses");
		List<Locale> lst=new ArrayList<Locale>();
		for(int i=0;i<businesses.length();++i) {
			try {
				JSONObject locale=businesses.getJSONObject(i);
				String myCat[]=getMyCategoriesFromApi(locale.getJSONArray("categories"));
				if(myCat.length>0) {
					Locale l=new Locale("yelp");
					l.setIdApi(locale.getString("id"));
					l.setPlaceID(idplace);
					l.setCategory(Categoria.getValueFromCategoriesNames(myCat));
					l.setNome(locale.getString("name"));
					JSONObject coord=locale.getJSONObject("coordinates");
					l.setLatitudine(coord.getFloat("latitude"));
					l.setLongitudine(coord.getFloat("longitude"));
					l.setPhone(locale.getString("display_phone"));
					JSONObject location=locale.getJSONObject("location");
					String addr="";
					JSONArray address=location.getJSONArray("display_address");
					for(int j=0;j<address.length();++j) {
						String line=address.getString(j);
						addr+=line + " ";
						
					}
						
						
					
					l.setVia(addr);
					lst.add(l);
					
					
				}
			}
			catch(JSONException e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("lunghezza : " + lst.size());
		return lst;
	}
	
	@Override
	protected String[] getMyCategoriesFromApi(JSONArray types){
		ArrayList<String> ar=new ArrayList<>(); 
		for(int i=0;i<types.length();++i) {
			JSONObject category=types.getJSONObject(i);
	    	String cat=category.getString("alias");
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
	
	
	@Override
	protected HashSet<ItemApi> getMapCategory() {
		HashSet<ItemApi> set=new HashSet<>();
		set.add(new ItemApi(new String[] {"bars","cocktailbars","sportsbars","sakebars","whiskeybars","coffee","coffeeteasupplies"}, Categoria.BAR));
		set.add(new ItemApi(new String[] {"wine_bars","wineries","winetastingroom","beer_and_wine"}, Categoria.ENOTECA));
		set.add(new ItemApi(new String[] {"pubs","irish_pubs"}, Categoria.PUB));
		set.add(new ItemApi(new String[] {"danceclubs"}, Categoria.DISCO));
		return set;
	}

	

}
