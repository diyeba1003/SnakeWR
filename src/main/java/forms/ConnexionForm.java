package forms;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.Utilisateur;
import dao.UtilisateurDao;

public class ConnexionForm {
	private static final String CHAMP_LOGIN = "login";
	private static final String CHAMP_PASS = "password";
	private UtilisateurDao utilisateurDao;
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public ConnexionForm(UtilisateurDao utilisateurDao) {
		this.utilisateurDao = utilisateurDao;

	}

	public Utilisateur connectUser(HttpServletRequest request) {

		String login = getValeurChamp(request, CHAMP_LOGIN);
		String password = getValeurChamp(request, CHAMP_PASS);

		Utilisateur utilisateur = new Utilisateur();

		try {
			utilisateur=validationLogin(login);
		} catch (Exception e) {
			setErreur(CHAMP_LOGIN, e.getMessage());

		}
		utilisateur.setLogin(login);

		try {
			validationPassword(password);
		} catch (Exception e) {
			setErreur(CHAMP_PASS, e.getMessage());

		}
		try {
			utilisateur.setPassword(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (erreurs.isEmpty()) {
			resultat = "Succès de la connexion.";
		} else {
			resultat = "Échec de la connexion.";
		}

		return utilisateur;

	}

	/**
	 * Valide l'adresse email saisie.
	 */
	private Utilisateur validationLogin(String login) throws Exception {
		if (login != null) {
			if (!(login.length() > 3)) {

				throw new Exception("Merci de saisir une adresse mail valide.");
			}
			Utilisateur user = utilisateurDao.verifConnexion(login);
			if (user == null) {

				throw new Exception("user existe pas en base.");
			}else {
				return user;
			}

		} else {
			throw new Exception("champ non null.");
		}
		
	}

	/**
	 * Valide le mot de passe saisi.
	 */
	private void validationPassword(String password) throws Exception {
		if (password != null) {
			if (password.length() < 3) {
				throw new Exception("Le mot de passe doit contenir au moins 3 caractères.");
			} else if (utilisateurDao.verifPassword(password) == null) {
				throw new Exception("password  existe pas en base.");
			}

		} else {
			throw new Exception("Merci de saisir votre mot de passe.");
		}
	}

	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}

}
