package it.youthclub;
import java.util.List;

import it.youthclub.api.ApiProvider;
import it.youthclub.model.Locali.Locale;
import it.youthclub.model.places.Place;
public class TaskSearch extends Thread {
   
    private int cat;
    private Place p;
	private ApiProvider provider;
	private onUpdateTask t;
	public TaskSearch(Place p,int category,ApiProvider api,onUpdateTask t) {
		this.p=p.clone();
		this.cat=category;
		this.provider=api;
		this.t=t;
	}
	
	
	
	
	public void run() {
		List<Locale> l=provider.search(p);
		synchronized(t) {
			t.update(l);
		}
		
	}
	
	public interface onUpdateTask{
		public void update(List<Locale> l);
		
	}
}
