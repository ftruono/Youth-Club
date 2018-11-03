package it.youthclub.model;

public class Locale {
	private int id;	
	private int idComune;
	private String nome;
	private String via;
	private String numeroCellulare;
	private String sitoWeb;
	private int numVotanti;
	private int totVoti;
	private float lat,lng;
	
	
	public Locale(int ID,int ID_Comune,String Nome,String Via,String Numero_telefono
			,String Sito_web,int Numero_votanti,int Tot_voti,float lat,float lng) {
		setID(ID);
		setID_Comune(ID_Comune);
		setNome(Nome);
		setNumero_telefono(Numero_telefono);
		setNumero_votanti(Numero_votanti);
		setSito_web(Sito_web);
		setTot_voti(Tot_voti);
		setVia(Via);
		this.setLatitudine(lat);
		this.setLongitudine(lng);
	}
	public int getID() {
		return id;
	}
	public int getID_Comune() {
		return idComune;
	}
	public void setID_Comune(int iD_Comune) {
		idComune = iD_Comune;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public String getNumero_telefono() {
		return numeroCellulare;
	}
	public void setNumero_telefono(String numero_telefono) {
		numeroCellulare = numero_telefono;
	}
	public String getSito_web() {
		return sitoWeb;
	}
	public void setSito_web(String sito_web) {
		sitoWeb = sito_web;
	}
	public int getNumero_votanti() {
		return numVotanti;
	}
	public void setNumero_votanti(int numero_votanti) {
		numVotanti = numero_votanti;
	}
	public int getTot_voti() {
		return totVoti;
	}
	public void setTot_voti(int tot_voti) {
		totVoti = tot_voti;
	}

	public void setID(int iD) {
		id = iD;
	}
	//To do eqauls toString clone
	public float getLatitudine() {
		return lat;
	}
	public void setLatitudine(float lat) {
		this.lat = lat;
	}
	public float getLongitudine() {
		return lng;
	}
	public void setLongitudine(float lng) {
		this.lng = lng;
	}
}
