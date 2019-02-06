package it.youthclub.test.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.youthclub.model.locali.Locale;
import it.youthclub.model.places.Place;
import it.youthclub.model.recensioni.Recensione;
import it.youthclub.model.users.Utente;

class BeanTest {
	
	private Locale loc;
	private Place plc;
	private Recensione rec;
	private Utente ut;
	
	
	/**
	 * Test Bean Locale
	 */
	@Test
	final void testBeanLocale() throws Exception {
		int idT=1;
		int idP=20;
		String nomeT="Galeon";
		String viaT="Via Roma,256";
		String numeroC="089236482";
		int numV=2;
		float votoT=3.5f;
		float totV=5;
		float latT=40.6791257f;
		float lngT=14.7550486f;
		int cat=3;
		String fonteT="Google";
		String id_apiT="b675a7affb57a712ed72a2c8493f9a55f601cbd2";
		
		loc.setID(idT);
		loc.setIdApi(id_apiT);
		loc.setFonte(fonteT);
		loc.setCategory(cat);
		loc.setNome(nomeT);
		loc.setLatitudine(latT);
		loc.setLongitudine(lngT);
		loc.setNumeroVotanti(numV);
		loc.setPlaceID(idP);
		loc.setPhone(numeroC);
		loc.setTotVoto(totV);
		loc.setVia(viaT);
		loc.setVoto(votoT);
		
		assertNotNull(loc);
		assertEquals(idT,loc.getID());
	}
	
	/**
	 * Test Bean Place
	 */
	@Test
	final void testBeanPlace() throws Exception {
		int idT = 2;
		float latT = 40.9280f;
		float longiT = 14.7942f;
		String nameT = "Salerno";
		Date dateFormat = new Date("2019/02/05"); 
		
		plc.setID(idT);
		plc.setLatitudine(latT);
		plc.setLongitudine(longiT);
		plc.setName(nameT);
		plc.setScadenza(dateFormat);
		
		assertNotNull(plc);
		assertEquals(idT,plc.getID());
	}
	
	/**
	 * Test Bean Recensione
	 */
	@Test
	final void testBeanRecensione() throws Exception {
		int idT = 1;
		String aID="ac1";
		int locID=3;
		String tet="Ottimo drink";
		String titRec="Festa Febbraio";
		float vt = 3.5f;
		int votSer = 4;
		int votQP = 4;
		int votCb = 3; 
		
		rec.setAccountID(aID);
		rec.setId(idT);
		rec.setLocaleID(locID);
		rec.setTesto(tet);
		rec.setTitoloRecensione(titRec);
		rec.setVotoCibo(votCb);
		rec.setVotoQP(votQP);
		rec.setVotoServizio(votSer);
		
		assertNotNull(rec);
		assertEquals(idT,rec.getId());
	}
	
	/**
	 * Test Bean Utente
	 */
	@Test
	final void testBeanUtente() throws Exception {
		String idT="User2";
		
		ut.setIdutente(idT);
		
		assertNotNull(ut);
		assertEquals(idT,ut.getIdutente());
	}


}
