package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Utilisateur;
import dao.DaoException;
import dao.DaoFactory;
import dao.UtilisateurDao;

/**
 * Servlet implementation class SuppresionUser
 */
@WebServlet("/SuppresionUser")
public class SuppresionUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UtilisateurDao utilisateurDao;
	public static final String VUE = "/WEB-INF/connexion.jsp";

		
		public void init()throws ServletException {
			
			/* recuperation d'une instance de dao utilisateur*/
			DaoFactory daoFactory = DaoFactory.getInstance();
			this.utilisateurDao = daoFactory.getUtilisateurDao();
		}
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuppresionUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur user = new Utilisateur();
		String idUser = getValeurParametre(request, "id");
		int id= Integer.parseInt(idUser);
		HttpSession session = request.getSession();
		if(id!=0 && user!=null) {
			user.setId(id);
			try{
			utilisateurDao.deleteUser(user);
			}catch(DaoException e) {
				e.printStackTrace();
			}
			session.setAttribute("sessionUser", user);
			
		}
		request.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
		//response.sendRedirect( request.getContextPath() + VUE );
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

	
	private static String getValeurParametre( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}
