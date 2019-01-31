package it.youthclub.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import it.youthclub.model.places.Place;
import it.youthclub.model.places.PlaceDM;


//DA aggiungere le runtimeException
public class Geocoding{
	private final static String KEY="AIzaSyDrloWpBhPQ-OZ7MyfPRhLn2kFGEbjGTMU";
	private String url=null,luogo;
	
	
	
	
	public Geocoding(float lat,float lng) {
		String temp_place=lat + "," + lng;
		//https://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452
		try {
			this.luogo=URLEncoder.encode(temp_place,java.nio.charset.StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			//TODO da gestire
		}
		
		url="https://maps.googleapis.com/maps/api/geocode/json?latlng=" + this.luogo + "&key=" + KEY;
		
	}
	
	
	public Geocoding(String luogo) {
		try {
			this.luogo=URLEncoder.encode(luogo,java.nio.charset.StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			//TODO da gestire
		}
		url="https://maps.googleapis.com/maps/api/geocode/json?address=" + this.luogo + "&key=" + KEY;
		
	}
	
	public String getLuogo() {
		return luogo;
	}

	
	
	public Place searchAndLearn() throws GeoException {
		HttpURLConnection connection = null;
		URL uri;
		try {
			uri = new URL(url);
			connection = (HttpURLConnection) uri.openConnection();
		    connection.setRequestMethod("GET");
		    BufferedReader read=new BufferedReader(new InputStreamReader(connection.getInputStream()));
		    String tot="";
		    String single="";
		    while( (single=read.readLine()) !=null) {
		    	tot+=single;
		    }
		    read.close();
			connection.disconnect();
		   JSONObject obj=new JSONObject(tot);
		   Place p=decodeJSON(obj);
		   return p;
		   
		  
		   
		   
		  
		   
		} catch (IOException e) {
			
			e.printStackTrace();
		}catch(JSONException ex) {
			ex.printStackTrace();
		}
	    
		
		
		return null;
	}
	
	private Place decodeJSON(JSONObject obj) throws GeoException {
		
		float lat,lng;
		String place=null,paese=null,prov=null;
		int better=0; //indica la precisione
		Place p;
		if(!obj.getString("status").contains("OK")) {
			   throw new GeoException("Impossibile decodificare il luogo");
		   }
		   try {
		   if(obj.getString("partial_match")!="") 
			  throw new GeoException("Risultato parziale , si prega di essere più precisi");
		   }catch(JSONException e) {
			  
		   }		   
		   JSONObject component=obj.getJSONArray("results").getJSONObject(0);
		   JSONArray address=component.getJSONArray("address_components");
		   JSONObject location=component.getJSONObject("geometry").getJSONObject("location");
		   lat=location.getFloat("lat");
		   lng=location.getFloat("lng");
		   PlaceDM pDM=new PlaceDM();
		   p=pDM.CheckIfExist(lat, lng); //controlla se esiste già quel luogo
		   Date d=new Date();
           boolean scaduto=false;
		   
		   if(p!=null) {
			   if(p.getScadenza()!=null && p.getScadenza().compareTo(d)>0) {//la situazione null non dovrebbe mai accader
				   p.setStatus(Place.EXIST);
				   return p;
			   } 
			   else
				   scaduto=true;
			   
		   }
		   
		   
		     for(int j=0;j<address.length();++j) {
				   JSONObject info=address.getJSONObject(j);
				   JSONArray types=info.getJSONArray("types");
				   if(types.length()>1){
					   if(types.getString(0).equals("administrative_area_level_3")) {
						  place=info.getString("long_name");
						  better=3;
					   }else if(types.getString(0).equals("administrative_area_level_2")) {
						     prov=info.getString("short_name");
					   }else if(types.getString(0).equals("locality")) {
						   if(better<1) {
							   better=1;
							   place=info.getString("long_name");
						   }
					   }   
					   else if(types.getString(0).equals("country")) 
						   paese=info.getString("long_name");
					   
				   }
				   
				
			   }
			if(better==0)
				place=getLuogo();
			Calendar cal = Calendar.getInstance(); 
			cal.add(Calendar.MONTH, 1);
			
			if(!scaduto) {
				p=new Place(lat,lng,place + " " + prov + " " + paese,cal.getTime());
				p.setStatus(Place.NOTEXIST);
				p=pDM.addPlace(p);
			}
				
			else {
				//aggiorno i nuovi dati del luogo (non strettamente necessario)
				p.setLatitudine(lat);
				p.setLongitudine(lng);
				p.setName(place + " " + prov + " " + paese);
				p.setScadenza(cal.getTime());
				p.setStatus(Place.EXPIRED_EXIST);
				p=pDM.editPlace(p);
			}
				
			System.out.println(p.toString());
			return p;
		   
	}
	

}
