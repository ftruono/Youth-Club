package it.youthclub.model.users;

import java.sql.SQLException;

public interface Utentable {
	public void createUser(Utente t);
	public Utente getUserById(String id);
	
}
