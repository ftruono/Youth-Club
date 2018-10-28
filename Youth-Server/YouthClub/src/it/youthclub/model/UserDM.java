package it.youthclub.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


public class UserDM implements Utentable{
	private Connection conn;
	
	@Override
	public String createUser(Utente t) {
		
		
		try {
			conn=DriverManagerConnectionPool.getConnection();
			PreparedStatement prep=conn.prepareStatement("INSERT INTO account (ID) VALUES (?)");
			prep.setString(1, t.getIdutente());
			prep.executeUpdate();//To do gestire l'errore
			conn.commit();
			prep.close();
			DriverManagerConnectionPool.releaseConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Utente creato con successo";
	}

	@Override
	public void editUserByID(String id, Utente t) throws SQLException {
		conn=DriverManagerConnectionPool.getConnection();
		PreparedStatement prep=conn.prepareStatement("UPDATE account SET ID=? WHERE (ID =?)");
		prep.setString(1,id );
		prep.setString(2, t.getIdutente());
		prep.executeUpdate();
		conn.commit();
		prep.close();
		DriverManagerConnectionPool.releaseConnection(conn);
		
	}
	
	@Override
	public int deleteAllUser() {
		int rs=10;
		String delete="delete * from youth_club.account "; 
		try {
			conn=DriverManagerConnectionPool.getConnection();
			Statement stmt = conn.createStatement();
			rs=stmt.executeUpdate(delete);
			stmt.close();
			DriverManagerConnectionPool.releaseConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}

	@Override
	public Utente[] getAllUser() throws SQLException {
		Utente[] ute = null;
	    Vector<Utente> vect=new Vector<Utente>();
		conn=DriverManagerConnectionPool.getConnection();
		PreparedStatement prep=null;
		String sql="SELECT * FROM youth_club.account";
		prep=conn.prepareStatement(sql);
		ResultSet rs=prep.executeQuery();
		while(rs.next()) {
			vect.add(new Utente(rs.getString("ID")));
		}
		prep.close();
		DriverManagerConnectionPool.releaseConnection(conn);
        ute=new Utente[vect.size()];
        ute=(Utente[])vect.toArray(ute);
		return  ute;
	}


	@Override
	public Utente getUserById(int id) {
		String sql="SELECT * FROM youth_club.account where idutenti=?";
		PreparedStatement preparedStatament=null;
		Utente ute=null;
		try {
			conn=DriverManagerConnectionPool.getConnection();
			preparedStatament=conn.prepareStatement(sql);
			preparedStatament.setInt(1, id);
			ResultSet rs = preparedStatament.executeQuery();
			while(rs.next()) {
				ute=new Utente(rs.getString("ID"));
			}
			preparedStatament.close();
			DriverManagerConnectionPool.releaseConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ute;
	}

	@Override
	public int deleteUserById(String id) {
		int rs=0;
		String deleteSQL = "DELETE FROM youth_club.account WHERE CODE = ?";
		PreparedStatement preparedStatement;
		try {
			conn=DriverManagerConnectionPool.getConnection();
			preparedStatement = conn.prepareStatement(deleteSQL);
			preparedStatement.setString(1, id);
			rs=preparedStatement.executeUpdate(deleteSQL);
			preparedStatement.close();
			DriverManagerConnectionPool.releaseConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

}
