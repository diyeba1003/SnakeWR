package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Utilisateur;
import dao.DaoFactory;
import dao.UtilisateurDao;
import forms.ConnexionForm;

/**
 * Servlet implementation class ServVerifieJoueur
 */
@WebServlet("/ServVerifieJoueur")
public class ServVerifieJoueur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurDao utilisateurDao;

	public static final String ATT_USER         = "utilisateur";
	public static final String ATT_FORM         = "form";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServVerifieJoueur() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException {

		/* recuperation d'une instance de dao utilisateur */
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.utilisateurDao = daoFactory.getUtilisateurDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
System.out.println("Je passe par lÃ ");
		
		String loginUser = (request.getParameter("login") != null) ? request.getParameter( "login" ) : "";
		String passwordUser = (request.getParameter( "password" ) != null) ? request.getParameter( "password" ) : "";
		System.out.println("le passsssworddddddddd   est " + passwordUser);
		PrintWriter printWriter = new PrintWriter(response.getWriter(), true);
		
		//verification de la connexion 
		if(utilisateurDao.verifConnexion(loginUser)!=null) {
			if(utilisateurDao.verifPassword(passwordUser)!=null)
			//PrintWriter printWriter = new PrintWriter(response.getWriter(), true);
			 printWriter.print("true");
			 System.out.println("true");
			 HttpSession session = request.getSession();
			
		}
		
		
		
		else {
			printWriter.print("true");
			System.out.println("False");	
		}
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
