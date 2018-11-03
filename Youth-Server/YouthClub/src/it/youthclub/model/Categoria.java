package it.youthclub.model;

import java.util.ArrayList;
//Rappresenta l'astrazione delle categorie , può risolvere anche più categorie.
public class Categoria {
  private int value;
  private String category[];
  public static enum types{
	  BAR(1),
	  PUB(2),
	  DISCO(4),
	  ENOTECA(8),
	  UNDEFINED(16);
	  
	  private final int id;
	  types(int id) { this.id = id; }
	  public int getValue() { return id; }
  }
  
  public Categoria(int value) {
	  this.value=value;
	  category=getCategoriesFromValue();
  }
  
  public Categoria(String cat[]) {
	  this.category=cat; //possibile problema con il clone!
	  this.value=getValueFromCategoriesNames();
  }
  
  public int getCategoryValue() {
	  return value;
  }
  
  public String[] getCategoriesNames() {
	  return category;
  }
  
  private int getValueFromCategoriesNames() {
	  int tot=0;
	  for(String sng:category) {
		  switch(sng) {
		  case "bar":
		    tot+=Categoria.types.BAR.getValue();
		  case "pub":
			  tot+=Categoria.types.PUB.getValue();
		  case "disco":
		      tot+=Categoria.types.DISCO.getValue();
		  case "enoteca":
			  tot+=Categoria.types.ENOTECA.getValue();
		     
		  }
	  }
	  if(tot==0) return Categoria.types.UNDEFINED.getValue();
	  return tot;
  }
  
  private String[] getCategoriesFromValue() {
	  if(value>15)
		  return new String[] {""};
	  else {
		      ArrayList<String> list=new ArrayList<>();
			  if(value>=types.ENOTECA.getValue()) {
				  value-=types.ENOTECA.getValue();
			      list.add("enoteca");
			  }
			  if(value>=types.DISCO.getValue()) {
				  value-=types.DISCO.getValue();
				  list.add("disco");
			  }
			  if(value>=types.PUB.getValue()) {
				  value-=types.PUB.getValue();
				  list.add("pub");
			  }
			  if(value>=types.BAR.getValue()) {
				  value-=types.BAR.getValue();
				  list.add("bar");
			  }
		    String rt[]=new String[list.size()];
		    return list.toArray(rt);	  
	  }
	  
  }
  
}
