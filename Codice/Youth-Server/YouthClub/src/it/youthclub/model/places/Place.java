package it.youthclub.model.places;

import java.util.Date;

public class Place implements Cloneable {
   public final static float RANGE=0.135135f;
   private int id;
   private float lat,lng;
   private String name;
   private Date scadenza;
   private int status=0; /* 0: non esiste e scaduto
                            1: esiste ma è scaduto
                            2: esiste e non è scaduto
                          */
   
   public final static int NOTEXIST=0;
   public final static int EXIST=2;
   public final static int EXPIRED_EXIST=1;
   
   public Place(int id, float lat, float longi, String name,Date scadenza) {

	this.id = id;
	this.lat = lat;
	this.lng = longi;
	this.name = name;
	this.scadenza=scadenza; //prob rif.
	
   }

   public Place(float lat, float longi, String name,Date scadenza) {
		this.lat = lat;
		this.lng = longi;
		this.name = name;
		id=-1;
		this.scadenza=scadenza;
		
		
 }
   
	public float getLatitudine() {
		return lat;
	}
	public void setLatitudine(float lat) {
		this.lat = lat;
	}
	public float getLongitudine(){
		return lng;
	}
	public void setLongitudine(float longi) {
		this.lng = longi;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id=id;
	}

	public Date getScadenza() {
		return scadenza;
	}

	public void setScadenza(Date scadenza) {
		this.scadenza = scadenza;
	}

	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Place [id=" + id + ", lat=" + lat + ", lng=" + lng + ", name=" + name + "]";
	}

	@Override
	public Place clone() {
		try {
			Place cl=(Place)super.clone();
			cl.scadenza=(Date)scadenza.clone();
			return cl;
		}catch(CloneNotSupportedException ex) {
			
		}
		return null;
		
	}

   
   
	
}
