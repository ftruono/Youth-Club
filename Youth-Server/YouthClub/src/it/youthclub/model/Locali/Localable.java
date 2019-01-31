package it.youthclub.model.Locali;


import java.util.List;

import it.youthclub.model.places.Place;

public interface Localable {
	public void addAllLocale(List<Locale> l);
	public List<Locale> getLocale(Place p,int category);
	public void updateLocale(List<Locale> l);

}

