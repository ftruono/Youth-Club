package it.youthclub.model;

public class Regione {
	private String Nome;
	private String Nome_Nazione;
	
	public Regione(String Nome,String Nome_Nazione) {
		setNome(Nome);
		setNome_Nazione(Nome_Nazione);
	}
	
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getNome_Nazione() {
		return Nome_Nazione;
	}
	public void setNome_Nazione(String nome_Nazione) {
		Nome_Nazione = nome_Nazione;
	}
}
