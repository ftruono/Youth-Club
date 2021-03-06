package it.youthclub.model.recensioni;



public class Recensione {
	
	private int id; 
	private String accountID;
	private int localeID;
	private String testo;
	private String titoloRecensione;
	private float voto;
	private int votoServizio;
	private int votoQP;
	private int votoCibo;
	
	public Recensione() {
	    
	}
	 
	public Recensione(int id, String accountID, int localeID, String testo, String titoloRecensione, float voto,
			int votoServizio, int votoQP, int votoCibo) {
		super();
		this.id = id;
		this.accountID = accountID;
		this.localeID = localeID;
		this.testo = testo;
		this.titoloRecensione = titoloRecensione;
		this.voto = voto;
		this.votoServizio = votoServizio;
		this.votoQP = votoQP;
		this.votoCibo = votoCibo;
	}
	
	public Recensione(int id,int idL,String testo,String titolo,int votoServizio,int votoQP,int votoCibo) {
		this.id=id;
		this.localeID=idL;
		this.testo=testo;
		this.titoloRecensione=titolo;
		this.votoServizio=votoServizio;
		this.votoQP=votoQP;
		this.votoCibo=votoCibo;
		this.voto=(votoServizio+votoQP+votoCibo)/3.0f;
		
		
	}
	
	public Recensione(String account,int idL,String testo,String titolo,int votoServizio,int votoQP,int votoCibo) {
		this.accountID=account;
		this.localeID=idL;
		this.testo=testo;
		this.titoloRecensione=titolo;
		this.votoServizio=votoServizio;
		this.votoQP=votoQP;
		this.votoCibo=votoCibo;
		this.voto=(votoServizio+votoQP+votoCibo)/3.0f;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountID() {
		return accountID;
	}
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	public int getLocaleID() {
		return localeID;
	}
	public void setLocaleID(int localeID) {
		this.localeID = localeID;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public String getTitoloRecensione() {
		return titoloRecensione;
	}
	public void setTitoloRecensione(String titoloRecensione) {
		this.titoloRecensione = titoloRecensione;
	}
	public float getVoto() {
		return voto;
	}
	
	public int getVotoServizio() {
		return votoServizio;
	}
	public void setVotoServizio(int votoServizio) {
		this.votoServizio = votoServizio;
	}
	public int getVotoQP() {
		return votoQP;
	}
	public void setVotoQP(int votoQP) {
		this.votoQP = votoQP;
	}
	public int getVotoCibo() {
		return votoCibo;
	}
	public void setVotoCibo(int votoCibo) {
		this.votoCibo = votoCibo;
	}
	
	
	

	
}
