package it.youthclub;

import java.util.ArrayList;
import java.util.List;

import it.youthclub.model.recensioni.Recensione;
import it.youthclub.model.recensioni.RecensioneDM;

public class ReviewControl {

	private RecensioneDM dm;
	
	public ReviewControl() {
		dm=new RecensioneDM();
		
	}
	
	
	public boolean editRecensione(int id,int localeID,String testo,String titolo,int votoQP,int votoS,int votoCibo,float oldVote) {
		Recensione s=new Recensione(id,localeID,testo,titolo,votoS,votoQP,votoCibo);
		return dm.editRecensioneByID(s,oldVote);
		
		
	}
	
	public boolean addRecensione(String account,int id_l,String testo,String titolo,int votoS,int votoQP,int votoCibo) {
	    Recensione s=new Recensione(account,id_l,testo,titolo,votoS,votoQP,votoCibo);
	   return dm.addRecensione(s);
	}
	
	public ArrayList<Recensione> getRecensioniByUser(String user){
	   return dm.getRecensioniByAccount(user);
	}
}
