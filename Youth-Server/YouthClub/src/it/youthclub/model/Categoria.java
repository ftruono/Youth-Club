package it.youthclub.model;

import java.util.ArrayList;
import java.util.HashMap;
//Rappresenta l'astrazione delle categorie 
public final class Categoria {
  
  public final static String BAR="bar";
  public final static String ENOTECA="enoteca";
  public final static String DISCO="disco";
  public final static String PUB="pub";
	
	
  public  enum types{
	  BAR(1),
	  PUB(2),
	  DISCO(4),
	  ENOTECA(8),
	  UNDEFINED(16);
	  
	  private final int id;
	  
	  types(int id) { 
		  this.id = id; 
		  
	  }
	  public int getValue() { return id; }
	  
  }
  
  public static int getValueFromCategoriesNames(String category[]) {
	  int tot=0;
	  for(String sng:category) {
		  switch(sng) {
		  case "bar":
		    tot+=Categoria.types.BAR.getValue();
		    break;
		  case "pub":
			  tot+=Categoria.types.PUB.getValue();
			  break;
		  case "disco":
		      tot+=Categoria.types.DISCO.getValue();
		      break;
		  case "enoteca":
			  tot+=Categoria.types.ENOTECA.getValue();
			  break;
		     
		  }
	  }
	  if(tot==0) return Categoria.types.UNDEFINED.getValue();
	  return tot;
  }
  
  public static String[] getCategoriesFromValue(int value) {
	  if(value>15)
		  return new String[] {""};
	  else {
		      ArrayList<String> list=new ArrayList<>();
			  if(value>=types.ENOTECA.getValue()) {
				  value-=types.ENOTECA.getValue();
			      list.add(ENOTECA);
			  }
			  if(value>=types.DISCO.getValue()) {
				  value-=types.DISCO.getValue();
				  list.add(DISCO);
			  }
			  if(value>=types.PUB.getValue()) {
				  value-=types.PUB.getValue();
				  list.add(PUB);
			  }
			  if(value>=types.BAR.getValue()) {
				  value-=types.BAR.getValue();
				  list.add(BAR);
			  }
		    String rt[]=new String[list.size()];
		    return list.toArray(rt);	  
	  }
	  
  }
  
}
