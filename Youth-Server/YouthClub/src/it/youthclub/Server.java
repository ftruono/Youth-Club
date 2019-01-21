package it.youthclub;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.youthclub.api.Geocoding;
import it.youthclub.model.places.Place;
import it.youthclub.model.users.UserDM;
import it.youthclub.model.users.Utente;

/**
 * Servlet implementation class Server
 */
@WebServlet("/Server")
public class Server extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Server() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		elaborateSearchs(request, response);
	}
	
	
	private void elaborateSearchs(HttpServletRequest request,HttpServletResponse response) {
		String search=request.getParameter("search");
		String name=request.getParameter("name"); // se è presente il nome del locale
		String category=request.getParameter("cat"); //categorie richieste
		String mode=request.getParameter("mode"); //0:gps , 1:testo
		Geocoding geo=null;
		if(mode.equals("0")) {
			String lat=search.substring(0,search.indexOf(","));
			String lng=search.substring(search.indexOf(",")+1,search.length());
			System.out.println(lat + " " + lng);
			try {
				geo=new Geocoding(Float.parseFloat(lat),Float.parseFloat(lng));
			}catch(NumberFormatException e) {
				
			}
			
		}else {
			geo=new Geocoding(search);
			
		}
		Place p=geo.searchAndLearn();
		int cat=Integer.parseInt(category);
		
	}

	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
       
		
	}

}
