package it.youthclub.test.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.youthclub.SearchController;

class SearchTest {
	
	private String searchT;
	private int categoryT;
	SearchController controller;
	JSONObject obj;
	
	/**
	 * Test per il metodo di ricerca con nome
	 */
	@Test
	final void testSearchName() throws Exception{
		controller = new SearchController("Madegra,Salerno");
		assertNotNull(controller);
		obj = controller.search();
		assertNotNull(obj);	
	}
	
	/**
	 * Test per il metodo di ricerca con luogo
	 */
	@Test
	final void testSearchLuogo() throws Exception{
		controller = new SearchController("Salerno",1,true);
		assertNotNull(controller);
		obj = controller.search();
		assertNotNull(obj);	
	}
	
	/**
	 * Test per il metodo di ricerca con gps
	 */
	@Test
	final void testSearchGps() throws Exception{
		controller = new SearchController("40.9280,14.7942",1,false);
		assertNotNull(controller);
		obj = controller.search();
		assertNotNull(obj);	
	}
}
