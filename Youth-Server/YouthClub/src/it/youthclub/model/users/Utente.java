package it.youthclub.model.users;



public class Utente implements Cloneable{
	private String id;
	
	
	public Utente(String id) {
		setIdutente(id);
	}

	public String getIdutente() {
		return id;
	}

	public void setIdutente(String idutente) {
		this.id = idutente;
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
