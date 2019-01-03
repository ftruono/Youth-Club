package it.youthclub.model.places;

public interface Placing {
    public Place addPlace(Place c);
    public Place CheckIfExist(float lat,float lng); //restituisce -1 se non lo trova e >0 l'id se c'è già!
	
	
}
