package it.youthclub.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ProvinciaDM implements Provincable{
	private Connection con;
	@Override
	public String createProvincia(Provincia p) {
		try {
			con=DriverManagerConnectionPool.getConnection();
			String sql="INSERT INTO comune (ID_Provincia, Nome, Nome_Regione) VALUES (?,?,?)";
			PreparedStatement prep=con.prepareStatement(sql);
			prep.setInt(1,p.getID_Provincia());
			prep.setString(2, p.getNome());
			prep.setString(3, p.getNome_Regione());
			prep.executeQuery();//To do gestire l'errore
			con.commit();
			prep.close();
			DriverManagerConnectionPool.releaseConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Provincia aggiunto correttamente";
	}

	@Override
	public void editProvinciaByID(int id, Provincia p) throws SQLException {
		con=DriverManagerConnectionPool.getConnection();
		PreparedStatement prep=con.prepareStatement("UPDATE provincia SET ID_Provincia=?, Nome=?, Nome_Regione=?  WHERE (ID =?)");
		prep.setInt(1, p.getID_Provincia());
		prep.setString(2, p.getNome());
		prep.setString(3, p.getNome_Regione());
		prep.setInt(4, id);
		prep.executeUpdate();
		con.commit();
		prep.close();
		DriverManagerConnectionPool.releaseConnection(con);
		
	}

	@Override
	public int deleteProvinciaById(int id) {
		int rs=0;
		String deleteSQL = "DELETE FROM youth_club.provincia WHERE ID = ?";
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
	public int deleteAllProvincia() {
		int rs=10;
		String delete="delete * from youth_club.provincia "; 
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
	public Provincia[] getAllProvincia() throws SQLException {
		Provincia[] prov= null;
		
	    Vector<Provincia> vect=new Vector<Provincia>();
		con=DriverManagerConnectionPool.getConnection();
		PreparedStatement prep=null;
		String sql="SELECT * FROM youth_club.provincia";
		prep=con.prepareStatement(sql);
		ResultSet rs=prep.executeQuery();
		while(rs.next()) {
			vect.add(new Provincia(rs.getInt("ID_Provincia"),rs.getString("Nome"),rs.getString("Nome_Regione")));
		}
		prep.close();
		DriverManagerConnectionPool.releaseConnection(con);
        prov=new Provincia[vect.size()];
        prov=(Provincia[])vect.toArray(prov);
		return  prov;
	}
	@Override
	public Provincia getProvinciaById(int id) {
		String sql="SELECT * FROM youth_club.provincia where id=?";
		PreparedStatement preparedStatament=null;
		Provincia prov=null;
		try {
			con=DriverManagerConnectionPool.getConnection();
			preparedStatament=con.prepareStatement(sql);
			preparedStatament.setInt(1, id);
			ResultSet rs = preparedStatament.executeQuery();
			while(rs.next()) {
				prov=new Provincia(rs.getInt("ID_Provincia"),rs.getString("Nome"),rs.getString("Nome_Regione"));
			}
			preparedStatament.close();
			DriverManagerConnectionPool.releaseConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return prov;
	}

}
