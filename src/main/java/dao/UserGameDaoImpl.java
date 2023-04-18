package dao;

import static dao.DaoUtilitaire.fermeturesSilencieuses;
import static dao.DaoUtilitaire.initRequete;
import static dao.DaoUtilitaire.map;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.UserGame;
import beans.Utilisateur;

public class UserGameDaoImpl implements UserGameDao{
	
	private DaoFactory daoFactory;

	/**
	 * @param daoFactory
	 */
	public UserGameDaoImpl(DaoFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}
	
	
	private static final String SQL_INSERT = "INSERT INTO UserGame (login,nbGagner, nbJouer ,rang) VALUES (?, ?, ?, ?)";

	@Override
	public void addHistory(UserGame user) {
		// TODO Auto-generated method stub
		
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initRequete(connexion, SQL_INSERT, true, user.getLogin(), user.getNbGagner(),
					user.getNbJouer(), user.getRang());
			int statut = preparedStatement.executeUpdate();
			/* Analyse du statut retourné par la requête d'insertion */
			if (statut == 0) {
				throw new DaoException("Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table.");
			}
			/* Récupération de l'id auto-généré par la requête d'insertion */
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				/* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
				user.setId(valeursAutoGenerees.getInt(1));
			} else {
				throw new DaoException("Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné.");
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}

		
	}

	
	private static final String SQL_SELECT = "SELECT s.id, s.login, s.nbGagner,s.nbJouer, s.rang, u.login FROM Utilisateur u ,UserGame s where u.login=s.login ";

	@Override
	public List<UserGame> listHistory() {
		// TODO Auto-generated method stub
		

		
		List<UserGame> users = new ArrayList<UserGame>();
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
				
				int id = resultSet.getInt("id");
				int nbGagner = resultSet.getInt("nbGagner");
				int nbJouer = resultSet.getInt("nbJouer");
				String login = resultSet.getString("login");
				int rang = resultSet.getInt("rang");

				UserGame user = new UserGame();
				user.setId(id);
				user.setNbJouer(nbJouer);
				user.setNbGagner(nbGagner);
				user.setRang(rang);
				user.setLogin(login);
				users.add(user);
				System.out.println("la liste est:" + users);

			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return users;
		
	}

	//private static final String REQ_SELECT = "SELECT s.id, s.login, s.nbGagner,s.nbJouer, s.rang, u.login FROM Utilisateur u ,UserGame s where u.login=s.login ";

	
	@Override
	public UserGame afficheUser(String login) {
		Connection connexion = null;
		// PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		UserGame user = new UserGame();
		
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("SELECT * FROM UserGame WHERE login=?");
			preparedStatement.setString(1, login);
			System.out.println("le select de " + resultSet);
			
			resultSet = preparedStatement.executeQuery();
			System.out.println("le select2 de " + resultSet);
			
			System.out.println(resultSet);
			if(resultSet.next()) {
				//UserGame user = new UserGame();
				
				int idUser = resultSet.getInt("id");
				String log = resultSet.getString("login");
				int nbGagner = resultSet.getInt("nbGagner");
				int nbJouer = resultSet.getInt("nbJouer");
			
				int rang = resultSet.getInt("rang");
				user.setId(idUser);
				user.setNbJouer(nbJouer);
				user.setNbGagner(nbGagner);
				user.setRang(rang);
				user.setLogin(log);
				
				System.out.println("le userGame est:" + user);

			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		
		return user;
	}

	
	private static final String EXCEPT_SELECT = "SELECT s.id, s.login, s.nbGagner,s.nbJouer, s.rang, u.login FROM Utilisateur u ,UserGame s where u.login=s.login  and s.login !=?";

	
	@Override
	public List<UserGame> listUser(String login) {
		// TODO Auto-generated method stub
		List<UserGame> users = new ArrayList<UserGame>();
		Connection connexion = null;
		// PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(EXCEPT_SELECT);
			preparedStatement.setString(1, login);
			System.out.println(resultSet);
			resultSet = preparedStatement.executeQuery();
			System.out.println(resultSet);
			while (resultSet.next()) {

				UserGame user = new UserGame();
				int id = resultSet.getInt("id");
				int nbGagner = resultSet.getInt("nbGagner");
				int nbJouer = resultSet.getInt("nbJouer");
				String log = resultSet.getString("login");
				int rang = resultSet.getInt("rang");

				user.setId(id);
				user.setNbJouer(nbJouer);
				user.setNbGagner(nbGagner);
				user.setRang(rang);
				user.setLogin(log);
				users.add(user);
				System.out.println("la liste userGame  esssst:" + users);

			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return users;
	}

	@Override
	public UserGame updateHistory(UserGame user) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		/* rÉCUPÉRATION D'UNE CONNEXION DEPUIS FACTORY */
		try {
			connexion = daoFactory.getConnection();
			
			 // Mise à jour du rang de l'utilisateur
	        user.setNbJouer(user.getNbJouer() + 1);
            
	        
	        
	        
	        preparedStatement = connexion.prepareStatement("SELECT * FROM UserGame WHERE login = ?");
	        preparedStatement.setString(1, user.getLogin());
	        ResultSet resultSet = preparedStatement.executeQuery();
	        resultSet.next();
	        int pointsActuels = resultSet.getInt("nbJouer");
	        
	        int nouveau = pointsActuels + 1; 
	        
	        
	        
			preparedStatement = connexion.prepareStatement("UPDATE  UserGame SET nbJouer=? , nbPoint=? WHERE login=?;");
			System.out.println("requete modifff:" + preparedStatement);
			preparedStatement.setInt(1, nouveau);
			preparedStatement.setInt(2, user.getNbPoint());
			//preparedStatement.setString(2, utilisateur.getPassword());
			preparedStatement.setString(3, user.getLogin());
			//preparedStatement.setInt(3, user.getId());
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
		return user;
	}

	@Override
	public void miseAJourRang(UserGame user) {
		Connection connexion = null;
		// PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connexion = daoFactory.getConnection();
			
			String requete="SELECT nbPoint FROM UserGame WHERE login=?";
			preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setString(1, user.getLogin());
			System.out.println("le select de " + preparedStatement);
			
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			System.out.println("le select de " + resultSet);
			int pointsUtilisateur = resultSet.getInt("nbPoint");
			
			// recuperation du nombre total de point
			requete = "SELECT SUM(nbPoint) AS total_points FROM UserGame";
	        preparedStatement = connexion.prepareStatement(requete);
	        resultSet = preparedStatement.executeQuery();
	        resultSet.next();
	        System.out.println("le select 2 de " + resultSet);
	        int totalPoints = resultSet.getInt("total_points");

	        // Récupération de la liste des utilisateurs
	        requete = "SELECT login, nbPOint, rang FROM UserGame";
	        preparedStatement = connexion.prepareStatement(requete);
	        resultSet = preparedStatement.executeQuery();
	        System.out.println("le select 3 de " + resultSet);
			//parcours des utilisateur pour mettre à jour
	        
	        
	        
	        int rang = 0;
	        int pointsPrecedents = 0;
	        while (resultSet.next()) {
	            String login = resultSet.getString("login");
	            int points = resultSet.getInt("nbPoint");
	            int nouveauRang = resultSet.getInt("rang");

	            if (points > pointsPrecedents) {
	                rang++;
	                nouveauRang = rang;
	            }

	            if (login == user.getLogin()) {
	                if (points == pointsUtilisateur) {
	                    nouveauRang = rang;
	                } else if (points > pointsUtilisateur) {
	                    nouveauRang = rang - 1;
	                }
	            }

	            String requeteMiseAJour = "UPDATE UserGame SET rang = ? WHERE login = ?";
	            PreparedStatement preparedStatementMiseAJour = connexion.prepareStatement(requeteMiseAJour);
	            System.out.println("le select de " + preparedStatementMiseAJour);
	            preparedStatementMiseAJour.setInt(1, nouveauRang);
	            preparedStatementMiseAJour.setString(2, login);
	            preparedStatementMiseAJour.executeUpdate();
	            System.out.println("le select de " + preparedStatementMiseAJour);
	            System.out.println("le select 4 de " + preparedStatementMiseAJour);
	            preparedStatementMiseAJour.close();
	            System.out.println("le select 5de " + preparedStatementMiseAJour);
	            pointsPrecedents = points;
	        
	        
	       
				System.out.println("le userGame est:" + user);

			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		
	
		
	}
	
	
	

}
