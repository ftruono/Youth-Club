package it.youthclub.test.impl;

import java.util.List;

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
	private final testGetLocale() {
		list=locDM.getLocale(new Place(50,40.6791257, 14.7550486, "Salerno", "12/05/2019"), 8);
		assertNotNull(list);
	}
	/**
	 * Testo del metodo AddReview che prendere come paramentro un oggetto Recensione
	 */
	private final testAddReview() {
		locDM.addReviewLocale(new Recensione(3432432, 12, "servizio ottimo", "sono rimasto sodisfatto del servizio", 5, 5, 5));
		assertNotNull(locDM);
	}
	/**
	 * Test del metodo EditRecensione che prende come paramentro un oggeto Recensine e un float della differenza dei voti delle recensioni 
	 */
	private final testEditReviview() {
		locDM.updateLocale(new Recensione(3432432, 12, "servizio ottimo", "sono rimasto sodisfatto del servizio", 5, 5, 5),4,5 );
		assertNotNull(locDM);
	}
	
	/**
	 * Test del metodo CreateUser che crea un utente e prende come parametro un oggetto Utente
	 */
	private final testCreateUser() {
		userDM.createUser(new Utente("Giacomo"));
		assertNotNull(userDM);
	}
	/**
	 * Test del metodo GeuUserById che prende come paramentro la stringa del id di un utente
	 */
	private final testGetUserById() {
		Utente t=userDM.getUserById("Giacomo");
		assertNotNull(t);
	}
	/**
	 * Test del metodo AddPlace che aggiugnge un place e prende come oggetto un Place
	 */
	private final testAddPlace() {
		Place p= placeDM.addPlace(new Place(50,40.6791257, 14.7550486, "Salerno", "12/05/2019"));
		assertNotNull(p);
	}
	/**
	 * Test del metodo CheckIfExist contro se un place esiste e prende come parametri lat e lng 
	 */
	private final testCheckIfExist() {
		Place p=placeDM.CheckIfExist(50,40.6791257, 14.7550486);
		assertNotNull(p);
	}
	/*
	 * Test del metodo EditPlace che aggiorna un place e prende come un oggetto un Place
	 */
	private final testEditPlace() {
		Place p=placeDM.editPlace(new Place(50,40.6791257, 14.7550486, "Salerno1", "12/05/2019"));
		assertNotNull(p);
	}
}
