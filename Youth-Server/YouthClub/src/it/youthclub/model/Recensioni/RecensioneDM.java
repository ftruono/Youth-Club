package it.youthclub.model.Recensioni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.youthclub.model.DriverManagerConnectionPool;

public class RecensioneDM implements Recensionable{
	private Connection con;
	@Override
	public void addRecensione(Recensione r) {
		try {
			con=DriverManagerConnectionPool.getConnection();
			String sql="INSERT INTO recensioni (Account_ID, ID_Locale, Testo, Titolo_recensione,Voto, Voto_Servizio, Voto_qualita_prezzo, Voto_cibo) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement prep=con.prepareStatement(sql);
			
			prep.setString(1,r.getAccountID());
			prep.setInt(2, r.getLocaleID());
			prep.setString(3, r.getTesto());
			prep.setString(4, r.getTitoloRecensione());
			prep.setFloat(5, r.getVoto());
			prep.setInt(6, r.getVotoServizio());
			prep.setInt(7, r.getVotoQP());
			prep.setInt(8, r.getVotoCibo());
			prep.executeUpdate();
		
			prep.close();
			DriverManagerConnectionPool.releaseConnection(con);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void editRecensioneByID(Recensione r) {
		try {
			con=DriverManagerConnectionPool.getConnection();
			PreparedStatement prep=con.prepareStatement("UPDATE recensioni SET Testo=?, Titolo_recensione=?, Voto=?, Voto_Servizio=?, Voto_qualita_prezzo=?, Voto_cibo=?  WHERE (ID =?)");
			prep.setString(1,r.getTesto());
			prep.setString(2, r.getTitoloRecensione());
			prep.setFloat(3, r.getVoto());
			prep.setInt(4, r.getVotoServizio());
			prep.setInt(5, r.getVotoQP());
			prep.setInt(6, r.getVotoCibo());
			prep.setInt(7, r.getId());
			prep.executeUpdate();
			prep.close();
			DriverManagerConnectionPool.releaseConnection(con);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public ArrayList<Recensione> getAllRecensione(int idLoc) throws SQLException {
		ArrayList<Recensione> lst=new ArrayList<Recensione>();
		con=DriverManagerConnectionPool.getConnection();
		PreparedStatement prep=null;
		String sql="SELECT * FROM youth_club.recensioni where(ID_Locale=?)";
		prep=con.prepareStatement(sql);
		prep.setInt(1, idLoc);
		ResultSet rs=prep.executeQuery();
		while(rs.next()) {
			Recensione recensione=new Recensione(rs.getInt("ID"),rs.getString("Account_ID"),rs.getInt("ID_locale"),rs.getString("testo"),
                    rs.getString("Titolo_recensione"),rs.getFloat("Voto"),rs.getInt("voto_Servizio"),rs.getInt("Voto_qualita_prezzo"),
                    rs.getInt("Voto_cibo"));
			
			lst.add(recensione);
		}
		prep.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return lst;
	}

	
	

}