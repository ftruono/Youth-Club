package it.youthclub;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import it.youthclub.api.GeoException;



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
		//TODO:Controllo autenticazione!
		elaborateSearchs(request, response);
		
	}
	
	
	private void elaborateSearchs(HttpServletRequest request,HttpServletResponse response) {
		String name=request.getParameter("name"); // se è presente il nome del locale
		SearchController controller;
		if(name!=null) {
			controller=new SearchController(name);
		}else {
			String search=request.getParameter("search");
			if(search==null) return;
			String category=request.getParameter("cat"); //categorie richieste
			String mode=request.getParameter("mode"); //0:gps , 1:testo
			int cat=-1;
			boolean md=true;
			try {
				if (category!=null)
					cat=Integer.parseInt(category);
				if(mode!=null)
					md=mode.equals("0") ? false : true;
			}catch(NumberFormatException ex) {
				return ;
			}
		    controller=new SearchController(search, cat, md);
		}
		JSONObject json;
		try {
			json = controller.search();
		} catch (GeoException ex) {
			json=new JSONObject();
			json.put("error", ex.getMessage());
		}
		response.setContentType("application/json");
		try {
			response.getWriter().println(json);
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //TODO:controllo autenticazione!
       String review=request.getParameter("review");
       ReviewControl control=new ReviewControl();
       String testo,titolo;
       int votoService,votoQP,votoCibo;
       if(review!=null) {
    	   testo=request.getParameter("testo");
    	   titolo=request.getParameter("titolo");
    	   try {
	    	   votoService=Integer.parseInt(request.getParameter("votoServizio"));
	    	   votoQP=Integer.parseInt(request.getParameter("votoQP"));
	    	   votoCibo=Integer.parseInt(request.getParameter("votoCibo"));
    	   }catch(NumberFormatException ex) {
    		   return ;
    		   
    	   }
       }else
    	   return;
       
       if(review.equals("add")) {
    	   String account=request.getParameter("account");
    	   int id_l=Integer.parseInt(request.getParameter("idLocale"));
    	   control.addRecensione(account, id_l, testo, titolo, votoService, votoQP, votoCibo);
       }else if(review.equals("edit")) {
    	   int id=Integer.parseInt(request.getParameter("id"));
           control.editRecensione(id, testo, titolo, votoQP, votoService, votoCibo);
       }
		
	}
	
	
	
	

}
