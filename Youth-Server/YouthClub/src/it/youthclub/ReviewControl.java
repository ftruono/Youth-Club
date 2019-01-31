package it.youthclub;

import it.youthclub.model.recensioni.Recensione;
import it.youthclub.model.recensioni.RecensioneDM;

public class ReviewControl {

	private RecensioneDM dm;
	
	public ReviewControl() {
		dm=new RecensioneDM();
		
	}
	
	
	public void editRecensione(int id,String testo,String titolo,int votoQP,int votoS,int votoCibo) {
		Recensione s=new Recensione(id,testo,titolo,votoS,votoQP,votoCibo);
		dm.editRecensioneByID(s);
		
		
	}
	
	public void addRecensione(String account,int id_l,String testo,String titolo,int votoS,int votoQP,int votoCibo) {
	    Recensione s=new Recensione(account,id_l,testo,titolo,votoS,votoQP,votoCibo);
	    dm.addRecensione(s);
	}
}
