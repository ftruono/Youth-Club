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
	
	private ArrayList<Recensione> s;
	private ReviewControl r;
	
	private int idT;
	private int localeID;
	private String account;
	private String testo;
	private String titolo;
	private  int votoQP;
	private  int votoS;
	private  int votoCibo;
	private  float oldVote;
	
	@BeforeEach
	public void setUp() throws Exception {
		System.out.println("call");
		r=new ReviewControl();
		account="358240051111110";
		idT = 3596400;
		localeID = 314;
		testo="ottimo drink";
		titolo="Recensione Marzo";
		votoQP = 5;
		votoS = 5;
		votoCibo = 5;
		oldVote = 5;

	}
	
	/**
	 * Testa il metodo di aggiunta recensione
	 */
	@Test
	final void testAddRecensione() throws Exception{
		boolean b = r.addRecensione(account, localeID, testo, titolo, votoS, votoQP, votoCibo);
		assertEquals(true,b);
	}
	
	
	/**
	 * Testa il metodo di edit recensione sul database
	 */
	@Test
	final void testEditRecensione() throws Exception {
		boolean b = r.editRecensione(idT, localeID, testo, titolo, votoQP, votoS, votoCibo, oldVote);
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
