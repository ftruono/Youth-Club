package it.youthclub.model;

public class Locale {
	private int ID;	
	private int ID_Comune;
	private String Nome;
	private String Via;
	private String Numero_telefono;
	private String Sito_web;
	private int Numero_votanti;
	private int Tot_voti;
	
	public Locale(int ID,int ID_Comune,String Nome,String Via,String Numero_telefono,String Sito_web,int Numero_votanti,int Tot_voti) {
		setID(ID);
		setID_Comune(ID_Comune);
		setNome(Nome);
		setNumero_telefono(Numero_telefono);
		setNumero_votanti(Numero_votanti);
		setSito_web(Sito_web);
		setTot_voti(Tot_voti);
		setVia(Via);
	}
	public int getID() {
		return ID;
	}
	public int getID_Comune() {
		return ID_Comune;
	}
	public void setID_Comune(int iD_Comune) {
		ID_Comune = iD_Comune;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getVia() {
		return Via;
	}
	public void setVia(String via) {
		Via = via;
	}
	public String getNumero_telefono() {
		return Numero_telefono;
	}
	public void setNumero_telefono(String numero_telefono) {
		Numero_telefono = numero_telefono;
	}
	public String getSito_web() {
		return Sito_web;
	}
	public void setSito_web(String sito_web) {
		Sito_web = sito_web;
	}
	public int getNumero_votanti() {
		return Numero_votanti;
	}
	public void setNumero_votanti(int numero_votanti) {
		Numero_votanti = numero_votanti;
	}
	public int getTot_voti() {
		return Tot_voti;
	}
	public void setTot_voti(int tot_voti) {
		Tot_voti = tot_voti;
	}

	public void setID(int iD) {
		ID = iD;
	}
	//To do eqauls toString clone
}
