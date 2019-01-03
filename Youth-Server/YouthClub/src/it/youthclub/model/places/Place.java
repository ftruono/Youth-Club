package it.youthclub.model.places;

public class Place {
   public final static float RANGE=0.135135f;
   private int id;
   private float lat,lng;
   private String name;
   public Place(int id, float lat, float longi, String name) {
	
	this.id = id;
	this.lat = lat;
	this.lng = longi;
	this.name = name;
   }

   public Place(float lat, float longi, String name) {
		this.lat = lat;
		this.lng = longi;
		this.name = name;
		id=-1;
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

	@Override
	public String toString() {
		return "Place [id=" + id + ", lat=" + lat + ", lng=" + lng + ", name=" + name + "]";
	}

   
   
	
}
