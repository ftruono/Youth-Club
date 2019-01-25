package it.youthclub;

import java.util.ArrayList;
import java.util.List;

import it.youthclub.TaskSearch.onUpdateTask;
import it.youthclub.api.FourSquareApi;
import it.youthclub.api.GoogleApi;
import it.youthclub.api.YelpApi;
import it.youthclub.model.Locali.Locale;
import it.youthclub.model.places.Place;

public class ParallelingSearch implements onUpdateTask{

	
	private ArrayList<Locale>lst=new ArrayList<>();
	private int status;
	
	public void search(Place p,int category) {
		int status=p.getStatus();
		if(status==Place.NOTEXIST || status==Place.EXPIRED_EXIST) {
         	TaskSearch t1=new TaskSearch(p, category, new GoogleApi(), this);		
			TaskSearch t2=new TaskSearch(p,category,new YelpApi(),this);
			TaskSearch t3=new TaskSearch(p,category,new FourSquareApi(),this);
            t1.run();
            t2.run();
            t3.run();
            try {
				t3.join();
				t2.join();
	            t1.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            doUpdate();
            
            //aggiorna i dati del database
            
		}else {
			//leggi dal db
		}
		
		
	}

	@Override
	public void update(List<Locale> l) {
		lst.addAll(l);
		System.out.println("lst:" + lst.size());
	}
	
	private void doUpdate() {
		System.out.println("Sono finiti i 3thread");
		/*if(status==Place.NOTEXIST)
		//TODO : inserisci i locali
	else
		//TODO : aggiorna i locali
		 * 
		 */
	
	//recupera la lista corretta
	}
	
}
