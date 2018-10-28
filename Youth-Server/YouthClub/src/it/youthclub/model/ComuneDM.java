package it.youthclub.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ComuneDM implements Comunable{
	private Connection con;
	@Override
	public String createComune(Comune t) {
		try {
			con=DriverManagerConnectionPool.getConnection();
			String sql="INSERT INTO comune (ID, ID_Provincia,Nome) VALUES (?,?,?)";
			PreparedStatement prep=con.prepareStatement(sql);
			prep.setInt(1,t.getID());
			prep.setInt(2,t.getID_Provincia());
			prep.setString(3, t.getNome());
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
	public void editComuneByID(int id, Comune t) throws SQLException {
		con=DriverManagerConnectionPool.getConnection();
		PreparedStatement prep=con.prepareStatement("UPDATE comune SET ID=?, ID_Provincia=?, Nome=?  WHERE (ID =?)");
		prep.setInt(1, t.getID());
		prep.setInt(2, t.getID_Provincia());
		prep.setString(3, t.getNome());
		prep.setInt(4, id);
		prep.executeUpdate();
		con.commit();
		prep.close();
		DriverManagerConnectionPool.releaseConnection(con);
		
	}

	@Override
	public int deleteComuneById(int id) {
		int rs=0;
		String deleteSQL = "DELETE FROM youth_club.comune WHERE ID = ?";
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
	public int deleteAllComune() {
		int rs=10;
		String delete="delete * from youth_club.comune "; 
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
	public Comune[] getAllComune() throws SQLException {
		Comune[] comun= null;
		
	    Vector<Comune> vect=new Vector<Comune>();
		con=DriverManagerConnectionPool.getConnection();
		PreparedStatement prep=null;
		String sql="SELECT * FROM youth_club.comune";
		prep=con.prepareStatement(sql);
		ResultSet rs=prep.executeQuery();
		while(rs.next()) {
			vect.add(new Comune(rs.getInt("ID"),rs.getInt("ID_Provincia"),rs.getString("Nome")));
		}
		prep.close();
		DriverManagerConnectionPool.releaseConnection(con);
        comun=new Comune[vect.size()];
        comun=(Comune[])vect.toArray(comun);
		return  comun;
	}

	@Override
	public Comune getComuneById(int id) {
		String sql="SELECT * FROM youth_club.comune where id=?";
		PreparedStatement preparedStatament=null;
		Comune comun=null;
		try {
			con=DriverManagerConnectionPool.getConnection();
			preparedStatament=con.prepareStatement(sql);
			preparedStatament.setInt(1, id);
			ResultSet rs = preparedStatament.executeQuery();
			while(rs.next()) {
				comun=new Comune(rs.getInt("ID"),rs.getInt("ID_Provincia"),rs.getString("Nome"));
			}
			preparedStatament.close();
			DriverManagerConnectionPool.releaseConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return comun;
	}

}
