package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Utilisateur;
import dao.UtilisateurDao;

public class ModificationForm {

	private static final String CHAMP_LOGIN = "login";
	private static final String CHAMP_PASS = "password";
	private static final String CHAMP_REPEAT = "repeatpass";
	private static final String CHAMP_nom = "nom";
	private static final String CHAMP_prenom = "prenom";
	private String resultat;
	 
	private Map<String, String> erreurs = new HashMap<String, String>();

	
	private UtilisateurDao utilisateurDao;

	public ModificationForm(UtilisateurDao utilisateurDao) {
		this.utilisateurDao = utilisateurDao;
		//this.utilisateur = new Utilisateur();
	}

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Utilisateur updateUtilisateur(HttpServletRequest request) throws FormValidation {
		String login = getValeurChamp(request, CHAMP_LOGIN);
		String password = getValeurChamp(request, CHAMP_PASS);
		String repeat = getValeurChamp(request, CHAMP_REPEAT);
		String nom = getValeurChamp(request, CHAMP_nom);
		String prenom = getValeurChamp(request, CHAMP_prenom);
        String id=getValeurChamp(request, "id");
        int idUser = Integer.parseInt(id);
        HttpSession session = request.getSession();
         Utilisateur currentUser = (Utilisateur) session.getAttribute("sessionUser");
         
         
         if(login.isEmpty() || password.isEmpty()|| repeat.isEmpty()) {
        	 throw new FormValidation("les champs ne doivet pas être null!!");
    		  
         }
         
         
         //verifier que le login n'est pas utilisé
         if(!login.equals(currentUser.getLogin()) ) {
        	if(utilisateurDao.verifConnexion(login)!=null) {
        		 throw new FormValidation("login " + login  + " existe dèjà en base!!");
        		 
        	}
        	currentUser.setLogin(login);
         }
         if (!password.equals(repeat)){
        	 throw new FormValidation("les deux password doivent être identique!!");
    		 
         }
         
         //mise à jour de l'utilisateur
         currentUser.setId(idUser);
         currentUser.setPassword(password);
         utilisateurDao.updateProfil(currentUser);
        
        return currentUser;
        
        
        
        
        
	}
        
        
        
        //HttpSession session = request.getSession();
       // Utilisateur utilisateur = (Utilisateur) session.getAttribute("sessionUser");
        
		/*  
	        utilisateur.setId(idUser);
			traiterLogin(login, utilisateur);
			traiterPassword(password, repeat, utilisateur);
			
			if (erreurs.isEmpty()) {
				utilisateurDao.updateProfil(utilisateur);
				resultat = "Succès de l'inscription.";
			} else {
				resultat = "Échec de l'inscription.";
			}
		

		return utilisateur;
	}
	
	
	

	
	 Validation de l'adresse email 
	private void validLogin(String login) throws FormValidation {
		HttpSession session = request.getSession();
        utilisateur = (Utilisateur) session.getAttribute("sessionUser");
        
		if (login != utilisateur.getLogin()) {
			if (!(login.length() > 3)) {
				throw new FormValidation("Merci de saisir un login valide pas inferieur à 3.");
			}else if(utilisateurDao.verifConnexion(login) != null){
				throw new FormValidation("login dèjà utilisé!!");
			}
		}
		
		else{
			throw new FormValidation("Merci de saisir un login valid!!!");
		}
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
	
	
	
	 * Appel à la validation des mots de passe reçus, chiffrement du mot de passe et
	 * initialisation de la propriété motDePasse du bean
	 
	private void traiterPassword(String password, String repeat, Utilisateur utilisateur) throws FormValidation {

		validationPassword(password, repeat);
		utilisateur.setPassword(password);

	}
	
	private void traiterLogin(String login, Utilisateur utilisateur) throws FormValidation {

		validLogin(login);

		utilisateur.setLogin(login);
	}

	*/
	
	
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
}
