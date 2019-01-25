package it.youthclub.api;

import java.util.List;

import it.youthclub.model.Categoria;
import it.youthclub.model.Locali.Locale;
import it.youthclub.model.places.Place;

public interface Provider {
   public List<Locale> search(Place p);
   //public List<Locale> searchByName(Place p,String category[],String name);
}
