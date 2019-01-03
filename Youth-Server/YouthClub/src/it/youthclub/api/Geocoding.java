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
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import it.youthclub.model.places.Place;
import it.youthclub.model.places.PlaceDM;



public class Geocoding{
	private final static String KEY="AIzaSyDrloWpBhPQ-OZ7MyfPRhLn2kFGEbjGTMU";
	private String url=null,luogo;
	
	public static enum ErrorCode{
		OK,NO_DATA,PARTIAL;
	}
	
	
	public Geocoding(String luogo) {
		try {
			this.luogo=URLEncoder.encode(luogo,java.nio.charset.StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			
		}
		url="https://maps.googleapis.com/maps/api/geocode/json?address=" + this.luogo + "&key=" + KEY;
		
	}
	
	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		try {
			this.luogo =URLEncoder.encode(luogo,java.nio.charset.StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
	}
	
	public int searchAndLearn() {
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
		   JSONObject obj=new JSONObject(tot);
		   decodeJSON(obj);
		   
		   
		   read.close();
		   connection.disconnect();
		   
		   
		  
		   
		} catch (IOException e) {
			
			e.printStackTrace();
		}catch(JSONException ex) {
			ex.printStackTrace();
		}
	    
		
		
		return 0;
	}
	
	private Place decodeJSON(JSONObject obj) {
		
		float lat,lng;
		String place=null,paese=null,prov=null;
		int better=0; //indica la precisione
		Place p;
		if(!obj.getString("status").contains("OK")) {
			   return null;
		   }
		   try {
		   if(obj.getString("partial_match")!="") 
			  return null;
		   }catch(JSONException e) {
			  
		   }		   
		   JSONObject component=obj.getJSONArray("results").getJSONObject(0);
		   JSONArray address=component.getJSONArray("address_components");
		   JSONObject location=component.getJSONObject("geometry").getJSONObject("location");
		   lat=location.getFloat("lat");
		   lng=location.getFloat("lng");
		   PlaceDM pDM=new PlaceDM();
		   p=pDM.CheckIfExist(lat, lng);
		   if(p!=null)
			   return p;
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
			
			p=new Place(lat,lng,place + " " + prov + " " + paese);
			p=pDM.addPlace(p);
			
			System.out.println(p.toString());
			return p;
		   
	}
	

}
