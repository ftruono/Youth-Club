package it.youthclub.model;

import java.sql.SQLException;

public interface Localable {
	public String createLocale(Locale l);
	
	/*ADMIN*/
	
	public void editLocaleByID(int id,Locale l) throws SQLException ;
	public int deleteLocaleById(int id);
	public int deleteAllLocale();
	public Locale[] getAllLocale() throws SQLException;
	public Locale getLocaleById(int id);
}

