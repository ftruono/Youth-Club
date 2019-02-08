package it.youthclub.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import it.youthclub.model.Categoria;
import it.youthclub.model.locali.Locale;
import it.youthclub.model.places.Place;

public class FourSquareApi extends ApiProvider {

	public FourSquareApi() {
		super("3ODL1BKVCN4XI5JC3S5CZHNQFNOHNP2AVWVG115BC20DJPF4","D21QIXZTNTI1WFW513FZ40AC2BDC44SZXX5X0MNSSJDHPROE");
		
		
	}

	@Override
	public List<Locale> search(Place p) {
		String categories="";
		String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		for(ItemApi it : mapped) {
			 for(String k:it.getKey())
			   categories+=k + ",";
		}	 
		int pos=categories.lastIndexOf(",");
		categories=categories.substring(0, pos);
		String ll=p.getLatitudine() + "," + p.getLongitudine();
		
		
		String fourlink="https://api.foursquare.com/v2/venues/search?ll=" + ll +
				        "&categoryId=" + categories + "&intent=browse&radius=30000&client_id=" + super.API_KEY + "&client_secret=" + super.SECRET_KEY + "&v=" + timeStamp;
		HttpURLConnection connection = null;
		try {
			URL url = new URL(fourlink);
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
			int id_p=p.getID();
		    JSONObject obj=new JSONObject(tot);
		    return decodeJSON(obj,id_p);
		}catch(IOException e) {
			e.printStackTrace();
			
		}
		return null;
	}

	
	private List<Locale> decodeJSON(JSONObject obj,int idplace){
		JSONObject response=obj.getJSONObject("response");
		ArrayList<Locale> lista=new ArrayList<Locale>();
		JSONArray venues=response.getJSONArray("venues");
		for(int i=0;i<venues.length();++i) {
			try {
				JSONObject locale=venues.getJSONObject(i);
				String cat[]=getMyCategoriesFromApi(locale.getJSONArray("categories"));
				if(cat.length>0) {
					Locale l=new Locale("four");
					l.setIdApi(locale.getString("id"));
					l.setPlaceID(idplace);
					l.setCategory(Categoria.getValueFromCategoriesNames(cat));
					l.setNome(locale.getString("name"));
					JSONObject location=locale.getJSONObject("location");
					l.setLatitudine(location.getFloat("lat"));
					l.setLongitudine(location.getFloat("lng"));
					lista.add(l);
					String addr="";
					JSONArray address=location.getJSONArray("formattedAddress");
					for(int j=0;j<address.length();++j) {
						String line=address.getString(j);
						addr+=line + " ";
					}
					/*addr=location.getString("address") + " " +  location.getString("city") + " " + location.getString("state") + 
							" " + location.getString("country");*/
					
					l.setVia(addr);
					
					
				}
			}catch(JSONException ex) {
				System.out.println(ex.getMessage());
			}
		}
		
		System.out.println("lunghezza : " + lista.size());
		return lista;
	}
	
	
	
	
	@Override
	protected String[] getMyCategoriesFromApi(JSONArray types) {
		List<String> ar=new ArrayList<>();
		for(int i=0;i<types.length();++i) {
			JSONObject it=types.getJSONObject(i);
			String cat=it.getString("id");
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
		HashSet<ItemApi> set=new HashSet<ItemApi>();
		set.add(new ItemApi(new String[] {"4bf58dd8d48988d112941735","4bf58dd8d48988d116941735","52e81612bcbc57f1066b7a0d",
				"56aa371ce4b08b9a8d57356c","4bf58dd8d48988d11e941735","4bf58dd8d48988d119941735","4bf58dd8d48988d11d941735","4bf58dd8d48988d122941735"}, Categoria.BAR));
		set.add(new ItemApi(new String[] {"4bf58dd8d48988d155941735","52e81612bcbc57f1066b7a06","4bf58dd8d48988d11b941735"},Categoria.PUB));
		set.add(new ItemApi(new String[] {"4bf58dd8d48988d11f941735","4bf58dd8d48988d121941735"},Categoria.DISCO));
		set.add(new ItemApi(new String[] {"4bf58dd8d48988d123941735","4bf58dd8d48988d14b941735"},Categoria.ENOTECA));
		return set;
	}

}
