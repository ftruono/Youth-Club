package it.youthclub.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class RegioneDM implements Regionable{
	private Connection con;
	@Override
	public String createRegione(Regione r) {
		try {
			con=DriverManagerConnectionPool.getConnection();
			String sql="INSERT INTO regione (Nome, Nome_Nazione) VALUES (?,?)";
			PreparedStatement prep=con.prepareStatement(sql);
			prep.setString(1,r.getNome());
			prep.setString(2,r.getNome_Nazione());
			prep.executeQuery();//To do gestire l'errore
			con.commit();
			prep.close();
			DriverManagerConnectionPool.releaseConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "comune aggiunto correttamente";
	}

	@Override
	public void editRegioneByID(String id, Regione r) throws SQLException {
		con=DriverManagerConnectionPool.getConnection();
		PreparedStatement prep=con.prepareStatement("UPDATE regione SET Nome=?, Nome_Nazione=?  WHERE (ID =?)");
		prep.setString(1, r.getNome());
		prep.setString(2, r.getNome_Nazione());
		prep.setString(3, id);
		prep.executeUpdate();
		con.commit();
		prep.close();
		DriverManagerConnectionPool.releaseConnection(con);
		
	}

	@Override
	public int deleteRegioneById(String id) {
		int rs=0;
		String deleteSQL = "DELETE FROM youth_club.regione WHERE ID = ?";
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
	public int deleteAllRegione() {
		int rs=10;
		String delete="delete * from youth_club.regione "; 
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
	public Regione[] getAllRegione() throws SQLException {
		Regione[] reg= null;
		
	    Vector<Regione> vect=new Vector<Regione>();
		con=DriverManagerConnectionPool.getConnection();
		PreparedStatement prep=null;
		String sql="SELECT * FROM youth_club.regione";
		prep=con.prepareStatement(sql);
		ResultSet rs=prep.executeQuery();
		while(rs.next()) {
			vect.add(new Regione(rs.getString("Nome"),rs.getString("Nome_Nazione")));
		}
		prep.close();
		DriverManagerConnectionPool.releaseConnection(con);
        reg=new Regione[vect.size()];
        reg=(Regione[])vect.toArray(reg);
		return  reg;
	}

	@Override
	public Regione Regione(String id) {
		String sql="SELECT * FROM youth_club.regione where id=?";
		PreparedStatement preparedStatament=null;
		Regione reg=null;
		try {
			con=DriverManagerConnectionPool.getConnection();
			preparedStatament=con.prepareStatement(sql);
			preparedStatament.setString(1, id);
			ResultSet rs = preparedStatament.executeQuery();
			while(rs.next()) {
				reg=new Regione(rs.getString("Nome"),rs.getString("Nome_Nazione"));
			}
			preparedStatament.close();
			DriverManagerConnectionPool.releaseConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reg;
	}

}
