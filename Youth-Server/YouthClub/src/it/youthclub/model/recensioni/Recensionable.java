package it.youthclub.model.recensioni;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Recensionable {
	public boolean addRecensione(Recensione r);
	public boolean editRecensioneByID(Recensione r,float oldVote);
	public ArrayList<Recensione> getAllRecensione(int idLoc) throws SQLException;
	public ArrayList<Recensione> getRecensioniByAccount(String s);
}
