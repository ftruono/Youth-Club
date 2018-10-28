package it.youthclub.model;



public class Utente implements Cloneable{
	private String idutente;
	
	public Utente(String id) {
		setIdutente(id);
	}

	public String getIdutente() {
		return idutente;
	}

	public void setIdutente(String idutente) {
		this.idutente = idutente;
	}
	public Utente clone() {
		try {
			Utente clone=(Utente) super.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//To do Equals e ToString

}
