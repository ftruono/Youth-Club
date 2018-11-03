package it.youthclub.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
	
		String targetURL1="https://maps.googleapis.com/maps/api/place/textsearch/json?query=bar+night_club+Fisci&language=it&key=" + API; 
		String targetURL="https://maps.googleapis.com/maps/api/place/autocomplete/xml?input=lamia&key=" + API; 
		HttpURLConnection connection = null;
		URL url = new URL(targetURL1);
	    connection = (HttpURLConnection) url.openConnection();
	    connection.setRequestMethod("GET");
	    String s=null;
	    BufferedReader buff=new BufferedReader(new InputStreamReader(connection.getInputStream()));
		return null;
	}

}
