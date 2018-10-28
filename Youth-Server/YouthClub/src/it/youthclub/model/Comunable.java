package it.youthclub.model;

import java.sql.SQLException;

public interface Comunable {
	
	public String createComune(Comune t);
	
	/*ADMIN*/
	
	public void editComuneByID(int id,Comune t) throws SQLException ;
	public int deleteComuneById(int id);
	public int deleteAllComune();
	public Comune[] getAllComune() throws SQLException;
	public Comune getComuneById(int id);
}
