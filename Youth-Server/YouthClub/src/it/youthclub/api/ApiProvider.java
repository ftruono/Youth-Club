package it.youthclub.api;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class ApiProvider implements Provider {

	protected String API_KEY="";
	protected String SECRET_KEY="";
	//protected String category[];
	protected HashSet<ItemApi> mapped; //rappresenta la mia categoria e la lista delle cat. del servizio
	public ApiProvider(String api){
        this.API_KEY=api;
        mapped=getMapCategory();
	}
	
	public ApiProvider(String api,String secret) {
		this(api);
		this.SECRET_KEY=secret;
	}
	

	protected abstract String[] getMyCategoriesFromApi(JSONArray types);
	
	protected abstract HashSet<ItemApi> getMapCategory();
	
	
	
}
