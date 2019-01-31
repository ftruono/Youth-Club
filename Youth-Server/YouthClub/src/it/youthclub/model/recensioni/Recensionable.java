package it.youthclub.model.recensioni;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Recensionable {
	public void addRecensione(Recensione r);
	public void editRecensioneByID(Recensione r);
	public ArrayList<Recensione> getAllRecensione(int idLoc) throws SQLException;
}
