package it.youthclub.model;

import java.sql.SQLException;

public interface Recensionable {
	public String createRecensione(Recensione r);
	
	/*ADMIN*/
	
	public void editRecensioneByID(int id,Recensione r) throws SQLException ;
	public int deleteRecensioneById(int id);
	public int deleteAllRecensione();
	public Recensione[] getAllRecensione() throws SQLException;
	public Recensione getRecensioneyId(int id);

}
