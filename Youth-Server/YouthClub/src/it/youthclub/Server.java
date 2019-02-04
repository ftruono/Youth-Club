package it.youthclub;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import it.youthclub.api.GeoException;
import it.youthclub.model.users.UserDM;
import it.youthclub.model.users.Utente;
import it.youthclub.util.Formatter;



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
		if(authentication(request, response))
			elaborateSearchs(request, response);
		
	}
	
	private boolean authentication(HttpServletRequest request,HttpServletResponse response) {
		if(request.getSession().getAttribute("utente")==null) {		
			String requestString=request.getParameter("auth");
		    if(requestString!=null) {
				UserDM us=new UserDM();
				Utente t=us.getUserById(requestString);
				if(t==null) {
					t=new Utente(requestString);
					us.createUser(t);
				}
				request.getSession().setAttribute("utente", t);
				return true;
		    }else 
		    	return false;
		}else 
			return true;
		
		
	}
	
	
	private synchronized void elaborateSearchs(HttpServletRequest request,HttpServletResponse response) {
		String name=request.getParameter("name"); // se è presente il nome del locale
		SearchController controller;
		if(name!=null) {
			controller=new SearchController(name);
		}else {
			String search=request.getParameter("search");
			if(search==null) return;
			String category=request.getParameter("cat"); //categorie richieste
			String mode=request.getParameter("mode"); //0:gps , 1:testo
			int cat=0;
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
		JSONObject json=null;
		response.setContentType("application/json");
		try {
			json = controller.search();
			
		} catch (GeoException ex) {
			try {
				response.getWriter().println(Formatter.getJSON(ex));
				response.getWriter().flush();
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//stampa risultati
		try {
			response.getWriter().println(json);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   if(authentication(request, response)) {
	       String review=request.getParameter("review");
	       ReviewControl control=new ReviewControl();
	       String testo,titolo;
	       int votoService,votoQP,votoCibo,idL;
	       boolean esito;
	       if(review!=null) {
	    	    if(review.equals("edit")||review.equals("add")) {
	    	    	testo=request.getParameter("testo");
	    	    	titolo=request.getParameter("titolo");
	    	    	   try {
	    		    	   votoService=Integer.parseInt(request.getParameter("votoServizio"));
	    		    	   votoQP=Integer.parseInt(request.getParameter("votoQP"));
	    		    	   votoCibo=Integer.parseInt(request.getParameter("votoCibo"));
	    		    	   idL=Integer.parseInt(request.getParameter("idLocale"));
	    	    	   }catch(NumberFormatException ex) {
	    	    		   return ;
	    	    		   
	    	    	   }
	    	        if(review.equals("add")) {
	    	        	 String account=request.getParameter("account");
	    	        	 if(account!=null) {
		    	        	 esito=control.addRecensione(account, idL, testo, titolo, votoService, votoQP, votoCibo);
		    	        	 response.getWriter().println(Formatter.getJSON(esito));
	    	        	 }
	    	        }else if(review.equals("edit")) {
	    	            try {
		    	        	int id=Integer.parseInt(request.getParameter("id"));
		    	            float oldVote=Float.parseFloat(request.getParameter("old"));
		    	            esito=control.editRecensione(id,idL, testo, titolo, votoQP, votoService, votoCibo,oldVote);
		    	            response.getWriter().println(Formatter.getJSON(esito));
	    	            }catch(NumberFormatException ex) {
	    	            	return ;
	    	            }
	    	            
	    	        }
	    	    	
	    	    }else if(review.equals("get")) {
	    	    	String account=request.getParameter("account");
	    	    	if(account!=null) {
	    	    		response.getWriter().println(Formatter.getJSON(control.getRecensioniByUser(account)));
	    	    	}
	    	    	
	    	    }
	    	   
	    	   
	    	   
	       }
       
	   }
		
	}
	
	

	
	
	

}
