package it.youthclub.test.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.youthclub.ReviewControl;
import it.youthclub.model.recensioni.Recensione;
import it.youthclub.model.recensioni.RecensioneDM;

class ReviewControlTest {
	
	ArrayList<Recensione> s;
	RecensioneDM dm;
	ReviewControl r;
	
	private static int idT;
	private static int localeIDT;
	private String testoT;
	private String titoloT;
	private static int votoQPT;
	private static int votoST;
	private static int votoCiboT;
	private static float oldVoteT;
	
	@BeforeEach
	void setUp() throws Exception {
		dm = new RecensioneDM();
		
		idT = 3596400;
		localeIDT = 60;
		testoT="ottima drink";
		titoloT="Recensione Marzo";
		votoQPT = 5;
		votoST = 4;
		votoCiboT = 5;
		oldVoteT = 4;

	}
	
	/**
	 * Testa il metodo di edit recensione sul database
	 */
	@Test
	final void testEditRecensione() throws Exception {
		boolean b = r.editRecensione(idT, localeIDT, testoT, titoloT, votoQPT, votoST, votoCiboT, oldVoteT);
		assertEquals(true,b);
		
	}
	
	/**
	 * Testa il metodo di aggiunta recensione
	 */
	@Test
	final void testAddRecensione() throws Exception{
		boolean b = r.addRecensione("rec1", localeIDT, testoT, titoloT, votoST, votoQPT, votoCiboT);
		assertEquals(true,b);
	}
	
	/**
	 * Testa il metodo che ritorna una lista di recensioni in base
	 * a un utente.
	 * Lo testiamo su un utente che ha almeno una recensione
	 */
	@Test
	final void testGetRecensioniByUser() throws Exception {
		s = r.getRecensioniByUser("user1");
		assertNotNull(s);
	}
}
