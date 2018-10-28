package it.youthclub.model;

public class Nazione {
	private String Nome;
	
	public Nazione (String nome) {
		setNome(nome);
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}
	public Nazione clone() {
		try {
			Nazione clone=(Nazione) super.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
}
