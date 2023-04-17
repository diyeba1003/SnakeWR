package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.Utilisateur;
import dao.DaoException;
import dao.UtilisateurDao;

public final class InscriptionForm {
	private static final String CHAMP_LOGIN = "login";
	private static final String CHAMP_PASS = "password";
	private static final String CHAMP_REPEAT = "repeat";
	private static final String CHAMP_nom = "nom";
	private static final String CHAMP_prenom = "prenom";
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	private UtilisateurDao utilisateurDao;

	public InscriptionForm(UtilisateurDao utilisateurDao) {
		this.utilisateurDao = utilisateurDao;
	}

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Utilisateur inscrireUtilisateur(HttpServletRequest request) throws FormValidation {
		String login = getValeurChamp(request, CHAMP_LOGIN);
		String password = getValeurChamp(request, CHAMP_PASS);
		String repeat = getValeurChamp(request, CHAMP_REPEAT);
		String nom = getValeurChamp(request, CHAMP_nom);
		String prenom = getValeurChamp(request, CHAMP_prenom);

		Utilisateur utilisateur = new Utilisateur();

	
			traiterLogin(login, utilisateur);
			traiterPassword(password, repeat, utilisateur);
			traiterNom(nom, utilisateur);
			traiterPrenom(prenom, utilisateur);

			if (erreurs.isEmpty()) {
				utilisateurDao.ajoutUser(utilisateur);
				resultat = "Succès de l'inscription.";
			} else {
				resultat = "Échec de l'inscription.";
			}
		

		return utilisateur;
	}

	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur.trim();
		}
	}

	/*
	 * Appel à la validation de l'adresse email reçue et initialisation de la
	 * propriété email du bean
	 */
	private void traiterLogin(String login, Utilisateur utilisateur) throws FormValidation {

		validLogin(login);

		utilisateur.setLogin(login);
	}

	private void traiterNom(String nom, Utilisateur utilisateur) throws FormValidation {

		validationNom(nom);

		utilisateur.setNom(nom);
	}

	private void traiterPrenom(String prenom, Utilisateur utilisateur) throws FormValidation {

		validationPrenom(prenom);

		utilisateur.setPrenom(prenom);
	}

	/*
	 * Appel à la validation des mots de passe reçus, chiffrement du mot de passe et
	 * initialisation de la propriété motDePasse du bean
	 */
	private void traiterPassword(String password, String repeat, Utilisateur utilisateur) throws FormValidation {

		validationPassword(password, repeat);
		utilisateur.setPassword(password);

	}

	private void validationPassword(String password, String repeat) throws FormValidation {
		if (password != null && repeat != null) {
			if (!password.equals(repeat)) {
				throw new FormValidation("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
			} else if (password.length() < 3) {
				throw new FormValidation("Les mots de passe doivent contenir au moins 3 caractères.");
			}
		} else {
			throw new FormValidation("Merci de saisir et confirmer votre mot de passe.");
		}
	}

	/* Validation de l'adresse email */
	private void validLogin(String login) throws FormValidation {
		if (login != null) {
			if (!(login.length() > 3)) {
				throw new FormValidation("Merci de saisir un login valide pas inferieur à 3.");
			} else if (utilisateurDao.verifConnexion(login) != null) {
				throw new FormValidation("Cette adresse email est déjà utilisée, merci d'en choisir une autre.");
			}
		} else {
			throw new FormValidation("Merci de saisir une adresse mail.");
		}
	}

	private void validationNom(String nom) throws FormValidation {
		if (nom != null && nom.length() < 3) {
			throw new FormValidation("Le nom d'utilisateur doit contenir au moins 3 caractères.");
		}
	}

	private void validationPrenom(String prenom) throws FormValidation {
		if (prenom != null && prenom.length() < 3) {
			throw new FormValidation("Le nom d'utilisateur doit contenir au moins 3 caractères.");
		}
	}

	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

}
