package it.youthclub.model.Locali;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import it.youthclub.model.Categoria;
import it.youthclub.model.DriverManagerConnectionPool;
import it.youthclub.model.Recensioni.Recensione;
import it.youthclub.model.Recensioni.RecensioneDM;
import it.youthclub.model.places.Place;

public class LocaleDM implements Localable{

	private Connection con;
	@Override
	public void addAllLocale(List<Locale> list) {
		try {
			con=DriverManagerConnectionPool.getConnection();
		
		for(Locale l : list) {
			try {
				String sql="INSERT INTO locale (Fonte,id_api,ID_place,Nome,Via,Numero_telefono,Categoria,lat,lng) VALUES (?,?,?,?,?,?,?,?,?)";
				PreparedStatement prep=con.prepareStatement(sql);
				prep.setString(1,l.getFonte());
				prep.setString(2, l.getIdApi());
				prep.setInt(3,l.getPlaceID());
				prep.setString(4, l.getNome());
				prep.setString(5, l.getVia());
				prep.setString(6, l.getPhone());
				prep.setInt(7, l.getCategory());
				prep.setFloat(8, l.getLatitudine());
				prep.setFloat(9,l.getLongitudine());
				prep.executeUpdate();
				prep.close();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
			DriverManagerConnectionPool.releaseConnection(con);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		
	}

	@Override
	public List<Locale> getLocale(Place p, int category) {
		String sql="";
		PreparedStatement preparedStatament=null;
		ArrayList<Locale> list=new ArrayList<>();
		try {
			con=DriverManagerConnectionPool.getConnection();
			if(category>0) {
				   Integer singleCat[]=Categoria.getSingleCategoriesFromValue(category);
				   String compose="("; 
				   if(singleCat.length>1) {
					   for(int i=0;i<singleCat.length-1;++i) {
						   compose+="locale.Categoria=" + singleCat[i] + " or ";
					   }
					   compose+="locale.Categoria=" + singleCat[singleCat.length-1] + ")";
				   }else
					   compose="locale.Categoria=" + singleCat[0];
				   
				   sql="SELECT * FROM youth_club.locale where (" + compose + " and locale.ID_Place=?)";
				   preparedStatament=con.prepareStatement(sql);
				   preparedStatament.setInt(1, p.getID());
			}else {
					sql="SELECT * FROM youth_club.locale where (locale.ID_Place=?)";
					preparedStatament=con.prepareStatement(sql);
					preparedStatament.setInt(1, p.getID());
			}
			
			ResultSet rs = preparedStatament.executeQuery();
		    while(rs.next()) {
			   Locale l=new Locale(rs.getInt("ID"),rs.getInt("ID_Place"),rs.getString("Nome"),rs.getString("Via"),rs.getString("Numero_telefono"),
					   rs.getInt("Tot_recensioni"),rs.getInt("Tot_voti"),rs.getFloat("lat"),rs.getFloat("lng"),rs.getInt("Categoria"),
					   rs.getString("Fonte"),rs.getString("id_api"));
			
			   
			   ArrayList<Recensione> recensioni=new RecensioneDM().getAllRecensione(l.getID());
			   l.setRecensioni(recensioni);
			   list.add(l);
		   
		   }
			   
		   
			preparedStatament.close();
			DriverManagerConnectionPool.releaseConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void updateLocale(List<Locale> lst) {
		
		
		ArrayList<Locale> added=new ArrayList<>();
		try {
			con=DriverManagerConnectionPool.getConnection();
			for(Locale l : lst) {
					String sql="UPDATE locale set Nome=?,Via=?,Numero_telefono=?,Categoria=? where(Fonte=? and id_api=?)";
					PreparedStatement preparedStatament=con.prepareStatement(sql);
				    preparedStatament.setString(1, l.getNome());
				    preparedStatament.setString(2, l.getVia());
				    preparedStatament.setString(3, l.getPhone());
				    preparedStatament.setInt(4, l.getCategory());
				    preparedStatament.setString(5, l.getFonte());
				    preparedStatament.setString(6, l.getIdApi());
				    int edit=preparedStatament.executeUpdate();
				    if(edit==0)
				    	added.add(l); //locale che va aggiunto , perchè nuovo!
				    preparedStatament.close();
			}
		  DriverManagerConnectionPool.releaseConnection(con);
		  addAllLocale(added);
		}catch (SQLException e) {
				e.printStackTrace();
		}
			
		}
}
	


