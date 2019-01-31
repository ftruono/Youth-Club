package it.youthclub;
import java.util.List;

import it.youthclub.api.ApiProvider;
import it.youthclub.model.locali.Locale;
import it.youthclub.model.places.Place;
public class TaskSearch extends Thread {
   
    private Place p;
	private ApiProvider provider;
	private onUpdateTask t;
	public TaskSearch(Place p,ApiProvider api,onUpdateTask t) {
		this.p=p.clone();
		
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
