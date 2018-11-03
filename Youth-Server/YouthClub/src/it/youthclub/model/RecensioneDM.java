package it.youthclub.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class RecensioneDM implements Recensionable{
	private Connection con;
	@Override
	public String createRecensione(Recensione r) {
		try {
			con=DriverManagerConnectionPool.getConnection();
			String sql="INSERT INTO recensione (ID, Account_ID, ID_Locale, Testo, Titolo_recensione, Voto, Voto_Servizio, Voto_qualità_prezzo, Voto_cibo) VALUES (?,?,?,?,?,?,?,?,?)";
			/*PreparedStatement prep=con.prepareStatement(sql);
			prep.setInt(1, r.getID());
			prep.setString(2,r.getAccount_ID());
			prep.setInt(3, r.getID_Locale());
			prep.setString(4, r.getTesto());
			prep.setString(5, r.getTitolo_recensione());
			prep.setInt(6, r.getVoto());
			prep.setInt(7, r.getVoto_Servizio());
			prep.setInt(8, r.getVoto_qualità_prezzo());
			prep.setInt(9, r.getVoto_cibo());
			prep.executeQuery();//To do gestire l'errore
			con.commit();
			prep.close();
			*/
			DriverManagerConnectionPool.releaseConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "comune aggiunto correttamente";
	}

	@Override
	public void editRecensioneByID(int id, Recensione r) throws SQLException {
		con=DriverManagerConnectionPool.getConnection();
		PreparedStatement prep=con.prepareStatement("UPDATE recensione SET ID=?, Account_ID=?, ID_Locale=?, Testo=?, Titolo_recensione=?, Voto=?, Voto_Servizio=?, Voto_qualità_prezzo=?, Voto_cibo=?  WHERE (ID =?)");
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

	@Override
	public Recensione[] getAllRecensione() throws SQLException {
		Recensione[] rec= null;
		
	    Vector<Recensione> vect=new Vector<Recensione>();
		con=DriverManagerConnectionPool.getConnection();
		PreparedStatement prep=null;
		String sql="SELECT * FROM youth_club.recensione";
		prep=con.prepareStatement(sql);
		ResultSet rs=prep.executeQuery();
		while(rs.next()) {
			//vect.add(new Recensione(rs.getInt("ID"), rs.getString("Account_ID"), rs.getInt("ID_Locale"), rs.getString("Testo"), rs.getString("Titolo_recensione"), rs.getInt("Voto"), rs.getInt("Voto_Servizio"), rs.getInt("Voto_qualità_prezzo"), rs.getInt("Voto_cibo")));
		}
		prep.close();
		DriverManagerConnectionPool.releaseConnection(con);
        rec=new Recensione[vect.size()];
        rec=(Recensione[])vect.toArray(rec);
		return  rec;
	}

	@Override
	public Recensione getRecensioneyId(int id) {
		String sql="SELECT * FROM youth_club.recensione where id=?";
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