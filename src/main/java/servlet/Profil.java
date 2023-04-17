package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserGame;
import beans.Utilisateur;
import dao.DaoException;
import dao.DaoFactory;
import dao.UserGameDao;
import dao.UtilisateurDao;

/**
 * Servlet implementation class Profil
 */
@WebServlet("/Profil")
public class Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserGameDao userDao;

private UtilisateurDao utilisateurDao;

	public void init()throws ServletException {
		
		/* recuperation d'une instance de dao utilisateur*/
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.utilisateurDao = daoFactory.getUtilisateurDao();
		this.userDao= daoFactory.getUserGameDao();
	}
       
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profil() {
        super();
        // TODO Auto-generated constructor stub
    }
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//récupération de la session utilisateur
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("sessionUser");
		 //verifie si l'utilisateur récuperé est connecté:
		
		if(user== null) {
			System.out.println("user is null");
			//request.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
			return;
		}
		
		//on affiche les infos du  game de cet user connecté
		UserGame userGame = userDao.afficheUser(user.getLogin());
		   request.setAttribute("userGame", userGame);
		/*
		 * try { request.setAttribute("utilisateurs", utilisateurDao.listeUser()); }
		 * catch (DaoException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		request.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	
    
	
	
}
