package it.youthclub.model;

import java.sql.SQLException;

public interface Nazionable {
		
		public String createNazione(Nazione n);
		
		/*ADMIN*/
		
		public void editNazioneByID(String id,Nazione n) throws SQLException ;
		public int deleteNazioneById(String id);
		public int deleteAllNazioni();
		public Nazione[] getAllNazione() throws SQLException;
		public Nazione getNazioneById(String id);
	}
