package it.youthclub.model;

import java.sql.SQLException;

public interface Provincable {
	public String createProvincia( Provincia p);
	
	/*ADMIN*/
	
	public void editProvinciaByID(int id,Provincia p) throws SQLException ;
	public int deleteProvinciaById(int id);
	public int deleteAllProvincia();
	public Provincia[] getAllProvincia() throws SQLException;
	public Provincia getProvinciaById(int id);
}