package it.youthclub.test.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.youthclub.api.FourSquareApi;
import it.youthclub.api.GeoException;
import it.youthclub.api.Geocoding;
import it.youthclub.api.GoogleApi;
import it.youthclub.api.YelpApi;
import it.youthclub.model.locali.Locale;
import it.youthclub.model.places.Place;

class ApiTest {
	
	private FourSquareApi fr;
	private GoogleApi ga;
	private YelpApi y;
	private Geocoding geo;
	
	List<Locale> loc;
	Place pl;
	
	
	void setUp() throws Exception {
		Date dateFormat = new Date("yyyy/MM/dd HH:mm:ss");
		pl = new Place(41, 40.9280f, 14.7942f, "Salerno", dateFormat);
	}
	
	/**
	 * Test del metodo search per l'api FourSquare
	 */
	
	final void testSearchFourSquare() throws Exception{
		loc = fr.search(pl);
		assertNotNull(loc);
	}
	
	/**
	 * Test del metodo search per l'api Google
	 */
	
	final void testSearchGoogle() throws Exception{
		loc = ga.search(pl);
		assertNotNull(loc);
	}
	
	/**
	 * Test del metodo search per l'api Yelp
	 */
	
	final void testSearchYelp() throws Exception{
		loc = y.search(pl);
		assertNotNull(loc);
	}
	
	/**
	 * Test del metodo search per Geocoding con parametri latitudine e longitudine
	 */
	
	final void testGeocodingLatLong() throws Exception{
		geo = new Geocoding(40.9280f, 14.7942f);
		assertNotNull(geo);
	}
	
	/**
	 * Test del metodo search per Geocoding con il luogo
	 */
	
	final void testGeocodingLuogo() throws Exception{
		geo = new Geocoding("Salerno");
		assertNotNull(geo);
	}
	
	/**
	 * Test del metodo Search and Learn di Geocoding
	 */
	
	final void testSearchAndLearn() {
		Place pl1;
		try {
			pl1 = geo.searchAndLearn();
			assertNotNull(pl1);
			System.out.println(pl1.getID()+ "" +pl1.getLatitudine()+ "" +pl1.getLongitudine()+ "" +pl1.getName()+ "" +pl1.getStatus());
		} catch (GeoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
