package dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;


public class DaoFactory {
	
	private String url;
	private String userName;
	private String password;
	/**
	 * @param url
	 * @param userName
	 * @param password
	 */
	public DaoFactory(String url, String userName, String password) {
		
		this.url = url;
		this.userName = userName;
		this.password = password;
	}
	
	
	
	public static DaoFactory getInstance() throws DaoConfiguration {
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("changement du driver!!");
		} catch (ClassNotFoundException e) {
			throw new DaoConfiguration("impossible de charger le drive");
		}
		
		DaoFactory instance = new DaoFactory("jdbc:mariadb://localhost:3306/snake", "root", "");
		return instance;
		
	}
	
	/* methode chargée de fournir une connexion à la base de données */
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,userName,password);
	}
	
	public UtilisateurDao getUtilisateurDao() {
		return new UtilisateurDaoImpl (this);
	}
	
	public UserGameDao getUserGameDao() {
		return new UserGameDaoImpl (this);
	}
}
