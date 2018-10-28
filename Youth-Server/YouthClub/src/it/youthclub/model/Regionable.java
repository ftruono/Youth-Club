package it.youthclub.model;

import java.sql.SQLException;

public interface Regionable {
	public String createRegione(Regione r);
	
	/*ADMIN*/
	
	public void editRegioneByID(String id,Regione r) throws SQLException ;
	public int deleteRegioneById(String id);
	public int deleteAllRegione();
	public Regione[] getAllRegione() throws SQLException;
	public Regione Regione(String id);
}

