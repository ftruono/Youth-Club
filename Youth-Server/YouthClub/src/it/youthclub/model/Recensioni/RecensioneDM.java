package it.youthclub.model.Recensioni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import it.youthclub.model.DriverManagerConnectionPool;

public class RecensioneDM implements Recensionable{
	private Connection con;
	@Override
	public void createRecensione(Recensione r) {
		try {
			con=DriverManagerConnectionPool.getConnection();
			String sql="INSERT INTO recensioni (ID, Account_ID, ID_Locale, Testo, Titolo_recensione, Voto, Voto_Servizio, Voto_qualita�_prezzo, Voto_cibo) VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement prep=con.prepareStatement(sql);
			prep.setInt(1,r.getId());
			prep.setString(2,r.getAccountID());
			prep.setInt(3, r.getLocaleID());
			prep.setString(4, r.getTesto());
			prep.setString(5, r.getTitoloRecensione());
			prep.setFloat(6, r.getVoto());
			prep.setInt(7, r.getVotoServizio());
			prep.setInt(8, r.getVotoQP());
			prep.setInt(9, r.getVotoCibo());
			prep.executeUpdate();//To do gestire l'errore
			con.commit();
			prep.close();
			DriverManagerConnectionPool.releaseConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public void editRecensioneByID(int id, Recensione r) throws SQLException {
		con=DriverManagerConnectionPool.getConnection();
		PreparedStatement prep=con.prepareStatement("UPDATE recensione SET Testo=?, Titolo_recensione=?, Voto=?, Voto_Servizio=?, Voto_qualita_prezzo=?, Voto_cibo=?  WHERE (ID =?)");
		/*prep.setInt(1, r.getID());
		prep.setString(2,r.getAccount_ID());
		prep.setInt(3, r.getID_Locale());
		prep.setString(4, r.getTesto());
		prep.setString(5, r.getTitolo_recensione());
		prep.setInt(6, r.getVoto());
		prep.setInt(7, r.getVoto_Servizio());
		prep.setInt(8, r.getVoto_qualità_prezzo());
		prep.setInt(9, r.getVoto_cibo());
		prep.setInt(10, id);
		prep.executeUpdate();
		con.commit();
		*/
		prep.close();
		DriverManagerConnectionPool.releaseConnection(con);
		
	}
/*
	@Override
	public int deleteRecensioneById(int id) {
		int rs=0;
		String deleteSQL = "DELETE FROM youth_club.recensione WHERE ID = ?";
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
	public int deleteAllRecensione() {
		int rs=10;
		String delete="delete * from youth_club.recensione "; 
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
*/
	
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

	
	public Recensione getRecensioneyId(int id) {
		String sql="SELECT * FROM youth_club.recensioni where id=?";
		PreparedStatement preparedStatament=null;
		Recensione rec=null;
		try {
			con=DriverManagerConnectionPool.getConnection();
			preparedStatament=con.prepareStatement(sql);
			preparedStatament.setInt(1, id);
			ResultSet rs = preparedStatament.executeQuery();
			while(rs.next()) {
				//rec=new Recensione(rs.getInt("ID"), rs.getString("Account_ID"), rs.getInt("ID_Locale"), rs.getString("Testo"), rs.getString("Titolo_recensione"), rs.getInt("Voto"), rs.getInt("Voto_Servizio"), rs.getInt("Voto_qualità_prezzo"), rs.getInt("Voto_cibo"));
			}
			preparedStatament.close();
			DriverManagerConnectionPool.releaseConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rec;
	}

}