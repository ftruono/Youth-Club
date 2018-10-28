package it.youthclub.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class NazioneDM implements Nazionable{
	private Connection con;
	@Override
	public String createNazione(Nazione n) {
		try {
			con=DriverManagerConnectionPool.getConnection();
			String sql="INSERT INTO nazione (nome) VALUES (?)";
			PreparedStatement prep=con.prepareStatement(sql);
			prep.setString(1,n.getNome());
			prep.executeQuery();//To do gestire l'errore
			con.commit();
			prep.close();
			DriverManagerConnectionPool.releaseConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "nazione aggiunto correttamente";
	}

	@Override
	public void editNazioneByID(String id, Nazione n) throws SQLException {
		con=DriverManagerConnectionPool.getConnection();
		PreparedStatement prep=con.prepareStatement("UPDATE nazione SET nome=? WHERE (ID =?)");
		prep.setString(1, n.getNome());
		prep.executeUpdate();
		con.commit();
		prep.close();
		DriverManagerConnectionPool.releaseConnection(con);
		
	}
	@Override
	public int deleteNazioneById(String id) {
		int rs=0;
		String deleteSQL = "DELETE FROM youth_club.nazione WHERE nome = ?";
		PreparedStatement preparedStatement;
		try {
			con=DriverManagerConnectionPool.getConnection();
			preparedStatement = con.prepareStatement(deleteSQL);
			preparedStatement.setString(1, id);
			rs=preparedStatement.executeUpdate(deleteSQL);
			preparedStatement.close();
			DriverManagerConnectionPool.releaseConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public int deleteAllNazioni() {
		int rs=10;
		String delete="delete * from youth_club.nazione "; 
		try {
			con=DriverManagerConnectionPool.getConnection();
			Statement stmt = con.createStatement();
			rs=stmt.executeUpdate(delete);
			stmt.close();
			DriverManagerConnectionPool.releaseConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}

	@Override
	public Nazione[] getAllNazione() throws SQLException {
		Nazione[] naz= null;
		
	    Vector<Nazione> vect=new Vector<Nazione>();
		con=DriverManagerConnectionPool.getConnection();
		PreparedStatement prep=null;
		String sql="SELECT * FROM youth_club.Nazione";
		prep=con.prepareStatement(sql);
		ResultSet rs=prep.executeQuery();
		while(rs.next()) {
			vect.add(new Nazione(rs.getString("nome")));
		}
		prep.close();
		DriverManagerConnectionPool.releaseConnection(con);
        naz=new Nazione[vect.size()];
        naz=(Nazione[])vect.toArray(naz);
		return  naz;
	}


	@Override
	public Nazione getNazioneById(String id) {
		String sql="SELECT * FROM youth_club.nazione where nome=?";
		PreparedStatement preparedStatament=null;
		Nazione naz=null;
		try {
			con=DriverManagerConnectionPool.getConnection();
			preparedStatament=con.prepareStatement(sql);
			preparedStatament.setString(1, id);
			ResultSet rs = preparedStatament.executeQuery();
			while(rs.next()) {
				naz=new Nazione(rs.getString("nome"));
			}
			preparedStatament.close();
			DriverManagerConnectionPool.releaseConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return naz;
	}

}

