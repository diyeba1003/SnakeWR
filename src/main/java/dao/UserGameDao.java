package dao;

import java.util.ArrayList;
import java.util.List;

import beans.UserGame;
import beans.Utilisateur;

public interface UserGameDao {
	
	public void addHistory(UserGame user);
	public List<UserGame> listHistory();
	public UserGame afficheUser(String login);
	
	public List<UserGame> listUser(String login);
	public UserGame updateHistory(UserGame user);

}
