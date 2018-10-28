package it.youthclub.model;

public class Provincia {
	private int ID_Provincia;
	private String Nome;
	private String Nome_Regione;
	
	public Provincia(int ID_Provincia,String Nome,String Nome_Regione) {
		setID_Provincia(ID_Provincia);
		setNome(Nome);
		setNome_Regione(Nome_Regione);
	}
	public int getID_Provincia() {
		return ID_Provincia;
	}
	public void setID_Provincia(int iD_Provincia) {
		ID_Provincia = iD_Provincia;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getNome_Regione() {
		return Nome_Regione;
	}
	public void setNome_Regione(String nome_Regione) {
		Nome_Regione = nome_Regione;
	}
	public Provincia clone() {
		try {
			Provincia clone=(Provincia) super.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
