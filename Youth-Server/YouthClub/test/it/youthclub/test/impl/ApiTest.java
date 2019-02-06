package it.youthclub.test.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.youthclub.api.FourSquareApi;
import it.youthclub.api.GeoException;
import it.youthclub.api.Geocoding;
import it.youthclub.api.GoogleApi;
import it.youthclub.api.YelpApi;
import it.youthclub.model.locali.Locale;
import it.youthclub.model.places.Place;
import junit.framework.TestCase;

public class ApiTest {
	
	private FourSquareApi fr=new FourSquareApi();
	private GoogleApi ga=new GoogleApi();
	private YelpApi y=new YelpApi();
	private Geocoding geo;
	
	List<Locale> loc;
	Place pl;
	
	
	
    @BeforeClass
	public static void initilizate() {
		System.out.println("setup");
		
	}
	
	
	
    @BeforeEach
    public void before() {
        Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, 01);
		pl = new Place(43, 40.9280f, 14.7942f, "Salerno", cal.getTime());
    }
    
    /**
	 * Test del metodo search per l'api FourSquare
	 */
    
	@Test
	public void testSearchFourSquare() throws Exception{
		//before();
		loc = fr.search(pl);
		assertNotNull(loc);
	}
	
	/**
	 * Test del metodo search per l'api Google
	 */
	
	@Test
	public void testSearchGoogle() throws Exception{
		//before();
		loc = ga.search(pl);
		assertNotNull(loc);
	}
	
	/**
	 * Test del metodo search per l'api Yelp
	 */
	
	@Test
	public void testSearchYelp() throws Exception{
		//before();
		loc = y.search(pl);
		assertNotNull(loc);
	}
	
	/**
	 * Test del metodo search per Geocoding con parametri latitudine e longitudine
	 */
	
	@Test
	public void testGeocodingLatLong() throws Exception{
		geo = new Geocoding(40.9280f, 14.7942f);
		Place p=geo.searchAndLearn();
		assertNotNull(p);
	}
	
	/**
	 * Test del metodo search per Geocoding con il luogo
	 */
	
	@Test
	public void testGeocodingLuogo() throws Exception{
		geo = new Geocoding("Salerno");
		Place p=geo.searchAndLearn();
		assertNotNull(p);
	}
	
	
	
	

}
