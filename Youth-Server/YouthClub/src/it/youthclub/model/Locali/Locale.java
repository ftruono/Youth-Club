package it.youthclub.model.Locali;

public class Locale {
	private int id;	
	private int idPlace;
	private String nome;
	private String via;
	private String numeroCellulare;
	private String sitoWeb;
	private int numVotanti;
	private int totVoti;
	private float lat,lng;
	private int category;
	
	public Locale() {
		sitoWeb="";
		numVotanti=0;
		totVoti=0;
		numeroCellulare="";
		
	}
	
	public Locale(int ID,int IDPlace,String Nome,String Via,String telefono
			,String web,int nVoti ,int Tot_voti,float lat,float lng,int cat) {
		setID(ID);
		setPlaceID(IDPlace);
		setNome(Nome);
		setPhone(telefono);
		setNumeroVotanti(nVoti);
		setWebSite(web);
		setTotVoti(Tot_voti);
		setVia(Via);
		this.setLatitudine(lat);
		this.setLongitudine(lng);
		this.setCategory(cat);
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
	public String getWebSite() {
		return sitoWeb;
	}
	public void setWebSite(String web) {
		sitoWeb = web;
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

	@Override
	public int hashCode() {
		return 31*17+nome.toLowerCase().hashCode();
	}
}
