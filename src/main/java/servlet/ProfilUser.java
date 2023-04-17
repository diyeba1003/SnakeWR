package servlet;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class ProfilUser
 */
@WebServlet("/ProfilUser")
public class ProfilUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurDao utilisateurDao;
	private boolean ajout = false;
	private UserGameDao userDao;


	public void init() throws ServletException{
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.utilisateurDao = daoFactory.getUtilisateurDao();
		this.userDao = daoFactory.getUserGameDao();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfilUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	Utilisateur user =  (Utilisateur) session.getAttribute("sessionUser");
	   String login = user.getLogin();
		try {
			List<UserGame> utilisateurs = userDao.listUser(login);
		// utilisateurs = utilisateurDao.listeUser();
			//request.setAttribute("utilisateurs", utilisateurDao.listeUser());
			request.setAttribute("utilisateurs", utilisateurs);
			
			
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("pas marcher lister");
		} 
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/profilUser.jsp").forward(request,response);
	}
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
