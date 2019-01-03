package it.youthclub.model.places;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.youthclub.model.DriverManagerConnectionPool;
import it.youthclub.model.Recensione;

public class PlaceDM implements Placing {

	private Connection con;
	
	@Override
	public Place addPlace(Place c) {
		try {
			con=DriverManagerConnectionPool.getConnection();
			String sql="INSERT INTO place (lat,lng,Name) VALUES (?,?,?)";
			PreparedStatement prep=con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			prep.setFloat(1, c.getLatitudine());
			prep.setFloat(2, c.getLongitudine());
			prep.setString(3, c.getName());
			prep.executeUpdate();
			ResultSet set=prep.getGeneratedKeys();
			set.next();
			con.commit();
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
			   p=new Place(rs.getInt("ID"),rs.getFloat("lat"),rs.getFloat("lng"),rs.getString("Name"));
		   
			preparedStatament.close();
			DriverManagerConnectionPool.releaseConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}

}
