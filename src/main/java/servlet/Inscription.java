package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanException;
import beans.Utilisateur;
import dao.DaoFactory;
import dao.UtilisateurDao;
import forms.FormValidation;
import forms.InscriptionForm;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurDao utilisateurDao;
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_FORM = "form";

	public void init() throws ServletException {

		/* recuperation d'une instance de dao utilisateur */
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.utilisateurDao = daoFactory.getUtilisateurDao();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Inscription() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		InscriptionForm form = new InscriptionForm(utilisateurDao);

		Utilisateur utilisateur = null;
		try {
			utilisateur = form.inscrireUtilisateur(request);
		} catch (FormValidation e) {
			// TODO Auto-generated catch block
			request.setAttribute("erreur", e.getMessage());
			e.printStackTrace();
		}
		// Utilisateur utilisateur;
		if (utilisateur!=null) {
			//request.setAttribute("ATT_FORM", form);
			request.setAttribute(ATT_USER, utilisateur);
			this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
		} else {
			request.setAttribute(ATT_FORM, form);
			request.setAttribute(ATT_USER, utilisateur);
			//String msg = "verifier les identifiant";
			//request.setAttribute("erreur", msg);
			this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
		}
	}

}
