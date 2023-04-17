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
 * Servlet implementation class UserHistory
 */
@WebServlet("/UserHistory")
public class UserHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserGameDao userDao;
	private boolean ajout = false;


	public void init() throws ServletException{
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.userDao = daoFactory.getUserGameDao();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserGame user = new UserGame();
		String idUser = request.getParameter("id");
		int id= Integer.parseInt(idUser);
		//HttpSession session = request.getSession();
		if(id!=0 && user!=null) {
			//request.setAttribute("user", userDao.afficheUser(id));
		}
		request.getServletContext().getRequestDispatcher("/WEB-INF/profilUser.jsp").forward(request, response);
		//response.sendRedirect( request.getContextPath() + VUE );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
