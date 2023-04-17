package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import beans.Utilisateur;

public class DaoUtilitaire {

	
	
	
	static Utilisateur map(ResultSet resultSet) throws SQLException{
		
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(resultSet.getInt("id"));
		utilisateur.setNom(resultSet.getString("nom"));
		utilisateur.setPrenom(resultSet.getString("prenom"));
		utilisateur.setLogin(resultSet.getString("login"));
		utilisateur.setPassword(resultSet.getString("password"));
		return utilisateur;
		
	}
	
	
	
	
	public  static PreparedStatement initRequete(Connection connexion, String sql, boolean returnG, Object...objets)throws SQLException{
		
		PreparedStatement preparedStatement = connexion.prepareStatement( sql, returnG ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
	    for ( int i = 0; i < objets.length; i++ ) {
	        preparedStatement.setObject( i + 1, objets[i] );
	    }
	    return preparedStatement;
		
	}
	
	
	
	
	/* Fermeture silencieuse du resultset */
	 static void fermetureSilencieuse( ResultSet resultSet ) {
	    if ( resultSet != null ) {
	        try {
	            resultSet.close();
	        } catch ( SQLException e ) {
	            System.out.println( "Échec de la fermeture du ResultSet : " + e.getMessage() );
	        }
	    }
	}

	/* Fermeture silencieuse du statement */
	public static void fermetureSilencieuse( Statement statement ) {
	    if ( statement != null ) {
	        try {
	            statement.close();
	        } catch ( SQLException e ) {
	            System.out.println( "Échec de la fermeture du Statement : " + e.getMessage() );
	        }
	    }
	}

	/* Fermeture silencieuse de la connexion */
	public static void fermetureSilencieuse( Connection connexion ) {
	    if ( connexion != null ) {
	        try {
	            connexion.close();
	        } catch ( SQLException e ) {
	            System.out.println( "Échec de la fermeture de la connexion : " + e.getMessage() );
	        }
	    }
	}

	/* Fermetures silencieuses du statement et de la connexion */
	public static void fermeturesSilencieuses( Statement statement, Connection connexion ) {
	    fermetureSilencieuse( statement );
	    fermetureSilencieuse( connexion );
	}

	/* Fermetures silencieuses du resultset, du statement et de la connexion */
	public static void fermeturesSilencieuses( ResultSet resultSet, Statement statement, Connection connexion ) {
	    fermetureSilencieuse( resultSet );
	    fermetureSilencieuse( statement );
	    fermetureSilencieuse( connexion );
	}
}
