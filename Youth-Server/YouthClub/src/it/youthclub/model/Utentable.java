package it.youthclub.model;

import java.sql.SQLException;

public interface Utentable {
	public String createUser(Utente t);
	
	/*ADMIN*/
	
	public void editUserByID(String id,Utente t) throws SQLException ;
	public int deleteUserById(String id);
	public int deleteAllUser();
	public Utente[] getAllUser() throws SQLException;
	public Utente getUserById(int id);
}
