package it.youthclub.model;

public class Comune {
	private int ID;
	private int ID_Provincia;
	private String Nome;
	
	public Comune(int id,int id_provincia, String nome) {
		setID(id);
		setID_Provincia(id_provincia);
		setNome(nome);
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getID_Provincia() {
		return ID_Provincia;
	}
	public void setID_Provincia(int iD_Provincia) {
		ID_Provincia = iD_Provincia;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	
	public Comune clone() {
		try {
			Comune clone=(Comune)super.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
