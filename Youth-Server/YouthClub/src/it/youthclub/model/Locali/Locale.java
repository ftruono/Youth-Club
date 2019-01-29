package it.youthclub.model.Locali;

import java.util.ArrayList;
import java.util.List;
import it.youthclub.model.Recensioni.Recensione;

public class Locale {
	private int id;	
	private int idPlace;
	private String nome;
	private String via;
	private String numeroCellulare;
	private int numVotanti;
	private int totVoti;
	private float lat,lng;
	private int category;
	private String fonte;
	private List<Recensione> recensioni;
	private String id_api;
	
	public Locale(String fonte) {
		
		numVotanti=0;
		totVoti=0;
		numeroCellulare="";
		this.fonte=fonte;
		recensioni=new ArrayList<>();
		
	}
	
	
	
	
	public Locale(int id, int idPlace, String nome, String via, String numeroCellulare, int numVotanti,
			int totVoti, float lat, float lng, int category, String fonte, String id_api) {
		
		this.id=id;
		this.idPlace = idPlace;
		this.nome = nome;
		this.via = via;
		this.numeroCellulare = numeroCellulare;
		this.numVotanti = numVotanti;
		this.totVoti = totVoti;
		this.lat = lat;
		this.lng = lng;
		this.category = category;
		this.fonte = fonte;
		this.recensioni = new ArrayList<>();
		this.id_api = id_api;
	}

	public boolean addReview(Recensione s) {
		return recensioni.add(s);
	}
	
	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public String getIdApi() {
		return id_api;
	}

	public void setIdApi(String id_api) {
		this.id_api = id_api;
	}

	
	
	
	public int getID() {
		return id;
	}
	public int getPlaceID() {
		return idPlace;
	}
	public void setPlaceID(int idPlace) {
		this.idPlace=idPlace;
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
	public String getPhone() {
		return numeroCellulare;
	}
	public void setPhone(String telefono) {
		numeroCellulare = telefono;
	}
	
	public int getNumeroVotanti() {
		return numVotanti;
	}
	public void setNumeroVotanti(int nv) {
		numVotanti = nv;
	}
	public int getTotVoti() {
		return totVoti;
	}
	public void setTotVoti(int totVoti) {
		this.totVoti = totVoti;
	}

	public void setID(int iD) {
		id = iD;
	}
	//TODO eqauls toString clone
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

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public List<Recensione> getRecensioni() {
		return recensioni;
	}




	public void setRecensioni(List<Recensione> recensioni) {
		this.recensioni = recensioni;
	}




	@Override
	public int hashCode() {
		return 31*17+nome.toLowerCase().hashCode();
	}
}
