package it.youthclub.api;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

public abstract class ApiProvider implements Provider {

	protected String API_KEY="";
	protected String SECRET_KEY="";
	
	
	public ApiProvider(String api) throws MalformedURLException{
        this.API_KEY=api;
	}
	
	public ApiProvider(String api,String secret) throws MalformedURLException {
		this(api);
		this.SECRET_KEY=secret;
	}
	

	
	
	
}
