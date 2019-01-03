package it.youthclub.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import it.youthclub.model.Categoria;
import it.youthclub.model.Locale;

public class GoogleApi extends ApiProvider {

	public GoogleApi() throws MalformedURLException {
		super("AIzaSyDrloWpBhPQ-OZ7MyfPRhLn2kFGEbjGTMU");
	}

	@Override
	public List<Locale> search(String in,Categoria cat) {
	
		String targetURL1="https://maps.googleapis.com/maps/api/place/textsearch/json?query=bar+night_club+Fisci&language=it&key=" + super.API_KEY; 
		HttpURLConnection connection = null;
		URL url;
		try {
			url = new URL(targetURL1);
			connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("GET");
		    BufferedReader buff=new BufferedReader(new InputStreamReader(connection.getInputStream()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	    String s=null;
	    
		return null;
	}

}
