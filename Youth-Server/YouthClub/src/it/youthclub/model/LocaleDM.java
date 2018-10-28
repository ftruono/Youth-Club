package it.youthclub.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class LocaleDM implements Localable{
	private Connection con;
	@Override
	public String createLocale(Locale l) {
		try {
			con=DriverManagerConnectionPool.getConnection();
			String sql="INSERT INTO locale (ID, ID_Comune, Nome, Via, Numero_telefono, Sito_web, Numero_votanti, Tot_voti) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement prep=con.prepareStatement(sql);
			prep.setInt(1, l.getID());
			prep.setInt(2, l.getID_Comune());
			prep.setString(3, l.getNome());
			prep.setString(4, l.getVia());
			prep.setString(5, l.getNumero_telefono());
			prep.setString(6, l.getSito_web());	
			prep.setInt(7, l.getNumero_votanti());
			prep.setInt(8, l.getTot_voti());
			
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
	public void editLocaleByID(int id, Locale l) throws SQLException {
		con=DriverManagerConnectionPool.getConnection();
		PreparedStatement prep=con.prepareStatement("UPDATE locale SET ID=?, ID_Comune=?, Nome=?, Via=?, Numero_telefono=?, Sito_web=?, Numero_votanti=?, Tot_voti=?  WHERE (ID =?)");
		prep.setInt(1, l.getID());
		prep.setInt(2, l.getID_Comune());
		prep.setString(3, l.getNome());
		prep.setString(4, l.getVia());
		prep.setString(5, l.getNumero_telefono());
		prep.setString(6, l.getSito_web());	
		prep.setInt(7, l.getNumero_votanti());
		prep.setInt(8, l.getTot_voti());
		prep.setInt(9, id);
		prep.executeUpdate();
		con.commit();
		prep.close();
		DriverManagerConnectionPool.releaseConnection(con);
		
	}

	@Override
	public int deleteLocaleById(int id) {
		int rs=0;
		String deleteSQL = "DELETE FROM youth_club.locale WHERE ID = ?";
		PreparedStatement preparedStatement;
		try {
			con=DriverManagerConnectionPool.getConnection();
			preparedStatement = con.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, id);
			rs=preparedStatement.executeUpdate(deleteSQL);
			preparedStatement.close();
			DriverManagerConnectionPool.releaseConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public int deleteAllLocale() {
		int rs=10;
		String delete="delete * from youth_club.locale "; 
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
	public Locale[] getAllLocale() throws SQLException {
		Locale[] loc= null;
		
	    Vector<Locale> vect=new Vector<Locale>();
		con=DriverManagerConnectionPool.getConnection();
		PreparedStatement prep=null;
		String sql="SELECT * FROM youth_club.locale";
		prep=con.prepareStatement(sql);
		ResultSet rs=prep.executeQuery();
		while(rs.next()) {
			vect.add(new Locale(rs.getInt("ID"),rs.getInt("ID_Comune"), rs.getString("Nome"), rs.getString("Via"), rs.getString("Numero_telefono"), rs.getString("Sito_web"), rs.getInt("Numero_votanti"), rs.getInt("Tot_voti")));
		}
		prep.close();
		DriverManagerConnectionPool.releaseConnection(con);
        loc=new Locale[vect.size()];
        loc=(Locale[])vect.toArray(loc);
		return  loc;
	}

	@Override
	public Locale getLocaleById(int id) {
		String sql="SELECT * FROM youth_club.locale where id=?";
		PreparedStatement preparedStatament=null;
		Locale loc=null;
		try {
			con=DriverManagerConnectionPool.getConnection();
			preparedStatament=con.prepareStatement(sql);
			preparedStatament.setInt(1, id);
			ResultSet rs = preparedStatament.executeQuery();
			while(rs.next()) {
				loc=new Locale(rs.getInt("ID"),rs.getInt("ID_Comune"), rs.getString("Nome"), rs.getString("Via"), rs.getString("Numero_telefono"), rs.getString("Sito_web"), rs.getInt("Numero_votanti"), rs.getInt("Tot_voti"));
			}
			preparedStatament.close();
			DriverManagerConnectionPool.releaseConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return loc;
	}

}
