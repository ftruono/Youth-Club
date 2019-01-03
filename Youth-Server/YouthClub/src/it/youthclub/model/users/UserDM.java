package it.youthclub.model.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import it.youthclub.model.DriverManagerConnectionPool;
public class UserDM implements Utentable{
	
	private Connection conn;
	
	@Override
	public void createUser(Utente t) {
		
		
		try {
			conn=DriverManagerConnectionPool.getConnection();
			PreparedStatement prep=conn.prepareStatement("INSERT INTO account (ID) VALUES (?)");
			prep.setString(1, t.getIdutente());
			prep.executeUpdate();
			conn.commit();
			prep.close();
			DriverManagerConnectionPool.releaseConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Utente getUserById(String id) {
		String sql="SELECT * FROM youth_club.account where ID=?";
		PreparedStatement preparedStatament=null;
		Utente ute=null;
		try {
			conn=DriverManagerConnectionPool.getConnection();
			preparedStatament=conn.prepareStatement(sql);
			preparedStatament.setString(1, id);
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



}
