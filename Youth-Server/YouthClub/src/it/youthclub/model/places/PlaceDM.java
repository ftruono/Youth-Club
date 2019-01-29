package it.youthclub.model.places;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import it.youthclub.model.DriverManagerConnectionPool;

public class PlaceDM implements Placing {

	private Connection con;
	
	@Override
	public Place addPlace(Place c) {
		try {
			con=DriverManagerConnectionPool.getConnection();
			String sql="INSERT INTO place (lat,lng,Name,Scadenza) VALUES (?,?,?,?)";
			PreparedStatement prep=con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			prep.setFloat(1, c.getLatitudine());
			prep.setFloat(2, c.getLongitudine());
			prep.setString(3, c.getName());
			prep.setDate(4, convertToSQL(c.getScadenza()));
			prep.executeUpdate();
			ResultSet set=prep.getGeneratedKeys();
			set.next();
			c.setID(set.getInt(1));			
			prep.close();
			
			DriverManagerConnectionPool.releaseConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}

	@Override
	public Place CheckIfExist(float lat, float lng) {
		float max_lat,min_lat,max_lng,min_lng;
		max_lng=lng+Place.RANGE;
		min_lng=lng-Place.RANGE;
		if(lat>0) {
		   max_lat=lat+Place.RANGE;
		   min_lat=lat-Place.RANGE;
		   
		}else {
			max_lat=lat-Place.RANGE;
			min_lat=lat+Place.RANGE;
		}
		String sql="SELECT * FROM youth_club.place where ((lat between ? and ?) and (lng between ? and ?))";
		PreparedStatement preparedStatament=null;
		Place p=null;
		try {
			con=DriverManagerConnectionPool.getConnection();
			preparedStatament=con.prepareStatement(sql);
			preparedStatament.setFloat(1, min_lat);
			preparedStatament.setFloat(2, max_lat);
			preparedStatament.setFloat(3, min_lng);
			preparedStatament.setFloat(4, max_lng);
			ResultSet rs = preparedStatament.executeQuery();
		   if(rs.next())
			   p=new Place(rs.getInt("ID"),rs.getFloat("lat"),rs.getFloat("lng"),rs.getString("Name"),rs.getDate("Scadenza"));
		   
			preparedStatament.close();
			DriverManagerConnectionPool.releaseConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}

	@Override
	public Place editPlace(Place edit) {
		try {
			con=DriverManagerConnectionPool.getConnection();
			String sql="UPDATE place SET lat=?,lng=?,Name=?,Scadenza=? WHERE(ID=?)";
			PreparedStatement prep=con.prepareStatement(sql);
			prep.setFloat(1, edit.getLatitudine());
			prep.setFloat(2, edit.getLongitudine());
			prep.setString(3, edit.getName());
			prep.setDate(4,convertToSQL(edit.getScadenza()));
			prep.setInt(5, edit.getID());
			prep.executeUpdate();		
			prep.close();
			return edit;
		} catch (SQLException e) {
			
		}
		return null;
	}
	
	
	private java.sql.Date convertToSQL(Date uDate) {
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
		
   }

}
