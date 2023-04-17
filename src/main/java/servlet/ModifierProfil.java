package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BeanException;
import beans.Utilisateur;
import dao.DaoException;
import dao.DaoFactory;
import dao.UtilisateurDao;
import forms.FormValidation;
import forms.ModificationForm;

/**
 * Servlet implementation class ModifierProfil
 */
@WebServlet("/ModifierProfil")
public class ModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UtilisateurDao utilisateurDao;

	public void init() throws ServletException {

		/* recuperation d'une instance de dao utilisateur */
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.utilisateurDao = daoFactory.getUtilisateurDao();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifierProfil() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("utilisateurs", utilisateurDao.listeUser());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getServletContext().getRequestDispatcher("/WEB-INF/modifierProfil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String id= request.getParameter("id");

		ModificationForm form = new ModificationForm(utilisateurDao);
		Utilisateur utilisateur = null;
		try {
			utilisateur = form.updateUtilisateur(request);
		} catch (FormValidation e) { // TODO Auto-generated catch block
			request.setAttribute("erreur", e.getMessage());
			e.printStackTrace();
		}
		//vérifie si l'utilisateur n'est pas null
		if (utilisateur != null) {
			request.setAttribute("utilisateurs", utilisateur);
			String f = "modification éffectué avec succés";
			request.setAttribute("f", f);
			request.getServletContext().getRequestDispatcher("/WEB-INF/modifierProfil.jsp").forward(request, response);
		} else {
			request.setAttribute("form", form);
			request.setAttribute("utilisateur", utilisateur);
			request.getServletContext().getRequestDispatcher("/WEB-INF/modifierProfil.jsp").forward(request, response);

		}

		

	}

}
