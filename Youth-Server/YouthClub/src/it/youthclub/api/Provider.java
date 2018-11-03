package it.youthclub.api;

import java.util.List;

import it.youthclub.model.Categoria;
import it.youthclub.model.Locale;

public interface Provider {
   public List<Locale> search(String in,Categoria cat);
   
}
