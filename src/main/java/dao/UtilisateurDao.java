package dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import beans.Utilisateur;

public interface UtilisateurDao {
	
	public void ajoutUser (Utilisateur utilisateur) throws DaoException ;
	
	public List< Utilisateur>listeUser () throws DaoException ;
	
	public Utilisateur verifConnexion(String login);
	public Utilisateur verifPassword(String password);
	public Utilisateur updateProfil(Utilisateur utilisateur) throws DaoException;
	public void deleteUser(Utilisateur user) throws DaoException;

}
  