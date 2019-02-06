package it.youthclub.test.impl;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

import it.youthclub.model.locali.Locale;
import it.youthclub.model.locali.LocaleDM;
import it.youthclub.model.places.Place;
import it.youthclub.model.places.PlaceDM;
import it.youthclub.model.recensioni.Recensione;
import it.youthclub.model.users.UserDM;
import it.youthclub.model.users.Utente;

public class DataModelTesting {

	private LocaleDM locDM=new LocaleDM();
	private UserDM userDM=new UserDM();
	private PlaceDM placeDM=new PlaceDM();
	
	
	/**
	 * Test del metodo GetLocale che prendere come paramentro un oggeto place e una categoria
	 */
	@Test
	private final void testGetLocale() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, 01);
		List<Locale> l=locDM.getLocale(new Place(40.6791257f, 14.7550486f, "Salerno", cal.getTime()),8);
		assertNotNull(l);
	}
	/**
	 * Testo del metodo AddReview che prendere come paramentro un oggetto Recensione
	 */
	
	@Test
	private final void testAddReview() {
		locDM.addReviewLocale(new Recensione(3432432, 12, "servizio ottimo", "sono rimasto sodisfatto del servizio", 5, 5, 5));
		assertNotNull(locDM);
	}
	
	
	
	/**
	 * Test del metodo CreateUser che crea un utente e prende come parametro un oggetto Utente
	 */
	@Test
	private final void testCreateUser() {
		userDM.createUser(new Utente("Giacomo"));
		assertNotNull(userDM);
	}
	/**
	 * Test del metodo GeuUserById che prende come paramentro la stringa del id di un utente
	 */
	@Test
	private final void testGetUserById() {
		Utente t=userDM.getUserById("Giacomo");
		assertNotNull(t);
	}
	/**
	 * Test del metodo AddPlace che aggiugnge un place e prende come oggetto un Place
	 */
	@Test
	private final void testAddPlace() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, 01);
		Place p= placeDM.addPlace(new Place(40.6791257f, 14.7550486f, "Salerno",cal.getTime()));
		assertNotNull(p);
	}
	/**
	 * Test del metodo CheckIfExist contro se un place esiste e prende come parametri lat e lng 
	 */
	@Test
	private final void testCheckIfExist() {
		Place p=placeDM.CheckIfExist(40.6791257f, 14.7550486f);
		assertNotNull(p);
	}
	/*
	 * Test del metodo EditPlace che aggiorna un place e prende come un oggetto un Place
	 */
	@Test
	private final void testEditPlace() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, 01);
		Place p=placeDM.editPlace(new Place(40.6791257f, 14.7550486f, "Salerno1",cal.getTime()));
		assertNotNull(p);
	}
}
