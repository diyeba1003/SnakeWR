package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Utilisateur;
import static dao.DaoUtilitaire.*;

public class UtilisateurDaoImpl implements UtilisateurDao {

	private DaoFactory daoFactory;

	/**
	 * @param daoFactory
	 */
	public UtilisateurDaoImpl(DaoFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	private static final String SQL_INSERT = "INSERT INTO Utilisateur (nom,prenom, login ,password) VALUES (?, ?, ?, ?)";

	@Override
	public void ajoutUser(Utilisateur utilisateur) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initRequete(connexion, SQL_INSERT, true, utilisateur.getNom(), utilisateur.getPrenom(),
					utilisateur.getLogin(), utilisateur.getPassword());
			int statut = preparedStatement.executeUpdate();
			/* Analyse du statut retourné par la requête d'insertion */
			if (statut == 0) {
				throw new DaoException("Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table.");
			}
			/* Récupération de l'id auto-généré par la requête d'insertion */
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				/* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
				utilisateur.setId(valeursAutoGenerees.getInt(1));
			} else {
				throw new DaoException("Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné.");
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}

	}

	private static final String SQL_SELECT = "SELECT id, nom, prenom, login, password FROM Utilisateur ";

	@Override
	public List<Utilisateur> listeUser() throws DaoException {

		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		Connection connexion = null;
		// PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(SQL_SELECT);
			System.out.println(resultSet);
			resultSet = preparedStatement.executeQuery();
			System.out.println(resultSet);
			while (resultSet.next()) {
				utilisateurs.add( map( resultSet ) );

				int id = resultSet.getInt("id");
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				String login = resultSet.getString("login");
                
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setId(id);
				utilisateur.setNom(nom);
				utilisateur.setPrenom(prenom);
				utilisateur.setLogin(login);
				utilisateurs.add(utilisateur);
				System.out.println("la liste est:" + utilisateur);

			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return utilisateurs;
	}

	private static final String SQL_SELECT_PAR_EMAIL = "SELECT id, login, nom, prenom, password FROM Utilisateur WHERE login = ?";

	@Override
	public Utilisateur verifConnexion(String login) throws DaoException {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Utilisateur utilisateur = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initRequete(connexion, SQL_SELECT_PAR_EMAIL, false, login);
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if (resultSet.next()) {
				utilisateur = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return utilisateur;

	}

	@Override
	public Utilisateur updateProfil(Utilisateur utilisateur) throws DaoException {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		/* rÉCUPÉRATION D'UNE CONNEXION DEPUIS FACTORY */
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("UPDATE  Utilisateur SET login=? , password=? WHERE id=?;");
			System.out.println("requete modifff:" + preparedStatement);
			preparedStatement.setString(1, utilisateur.getLogin());
			preparedStatement.setString(2, utilisateur.getPassword());
			preparedStatement.setInt(3, utilisateur.getId());
			preparedStatement.executeUpdate();
			System.out.println("requete update:" + preparedStatement);
			connexion.commit();

		} catch (SQLException e) {
			try {
				try {
					if (connexion != null) {
						connexion.rollback();
					}
				} catch (SQLException e1) {
					throw new DaoException("Impossible de communiquer avec la base de données");

				}
			} catch (DaoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de communiquer avec la base de données");
			}
		}
		return utilisateur;

	}

	private static final String SQL_DELETE_PAR_ID = "DELETE FROM Utilisateur WHERE id=?";

	@Override
	public void deleteUser(Utilisateur user) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initRequete(connexion, SQL_DELETE_PAR_ID, true, user.getId());
			preparedStatement.setInt(1, user.getId());
			System.out.println(preparedStatement);
			int statut = preparedStatement.executeUpdate();
		
			System.out.println(statut);
			if (statut == 0) {
				throw new DaoException("Échec de la suppression du client, aucune ligne supprimée de la table.");
			} else {
				user.setId(0);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}

	}

	private static final String SQL_SELECT_PAR_PASS = "SELECT id, login, nom, prenom, password  FROM Utilisateur where password=?";

	@Override
	public Utilisateur verifPassword(String password) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Utilisateur utilisateur = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initRequete(connexion, SQL_SELECT_PAR_PASS, false, password);
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if (resultSet.next()) {
				utilisateur = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return utilisateur;
	}
	
	
	
}
