package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserGame;
import beans.Utilisateur;
import dao.DaoFactory;
import dao.UserGameDao;
import dao.UtilisateurDao;

/**
 * Servlet implementation class ServHistorique
 */
@WebServlet("/ServHistorique")
public class ServHistorique extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserGameDao userDao;

	public void init() throws ServletException {

		/* recuperation d'une instance de dao utilisateur */
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.userDao = daoFactory.getUserGameDao();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServHistorique() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Dans le servHistorique");
		String login = (request.getParameter( "login" ) != null) ? request.getParameter( "login" ) : "";
		String password = (request.getParameter( "password" ) != null) ? request.getParameter( "password" ) : "";
		String history = (request.getParameter( "history" ) != null) ? request.getParameter( "history" ) : "";
	
		//on recupÃ¨re le joueur ensuite
		
		
		
		
		PrintWriter printWriter = new PrintWriter(response.getWriter(), true);
		
		//Utilisateur user = new Utilisateur();
		UserGame user =new UserGame();
		
		if (user != null) {
			user.setLogin(login);
			userDao.updateHistory(user);
			
			
			String[] myHistory = history.split("_");
			String date = myHistory[0];
			String statut;
			int maxJoueur = Integer.parseInt(myHistory[2]);
			
			if(maxJoueur == 1)
				statut = "Partie solo";
			else 
				statut = myHistory[1];
		System.out.println("date : " +date+", statut :"+statut+", nombre joueurs = "+maxJoueur);
		//	boolean add = HistoryDAO.addHistory(user.getIdUser(), hDate, hStatut, hMaxJoueur);
		//	if (add == true) {
		//		System.out.println("Succes de l'enregistrement de l'historique");
				printWriter.print("true");
				return ;
		//	}
		}else {
			System.out.println("Lol");
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
