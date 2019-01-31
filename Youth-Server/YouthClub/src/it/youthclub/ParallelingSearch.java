package it.youthclub;

import java.util.ArrayList;
import java.util.List;

import it.youthclub.TaskSearch.onUpdateTask;
import it.youthclub.api.FourSquareApi;
import it.youthclub.api.GoogleApi;
import it.youthclub.api.YelpApi;
import it.youthclub.model.locali.Locale;
import it.youthclub.model.locali.LocaleDM;
import it.youthclub.model.places.Place;

public class ParallelingSearch implements onUpdateTask{

	
	private ArrayList<Locale>lst;
	private Place p;
	private LocaleDM lm;
	private int category;
	
	public ParallelingSearch() {
		lm=new LocaleDM();
		lst=new ArrayList<>();
	}
	public List<Locale> search(Place p,int category) {
		this.p=p;
		this.category=category;
		lst.clear();
		int status=p.getStatus();	
		if(status==Place.NOTEXIST || status==Place.EXPIRED_EXIST) {
         	TaskSearch t1=new TaskSearch(p, new GoogleApi(), this);		
			TaskSearch t2=new TaskSearch(p,new YelpApi(),this);
			TaskSearch t3=new TaskSearch(p,new FourSquareApi(),this);
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
            return doUpdate();
            
            
            
		}else {
		    return lm.getLocale(p, category);
		     
		}
		
		
	}

	@Override
	public void update(List<Locale> l) {
		lst.addAll(l);
	}
	
	private List<Locale> doUpdate() {
		System.out.println("Sono finiti i 3thread");
		if(p.getStatus()==Place.NOTEXIST)
			lm.addAllLocale(lst);	
		else  //PLACE.EXPIRED_EXIST
			lm.updateLocale(lst);
		
		return lm.getLocale(p, category);
		
	}
	
}
