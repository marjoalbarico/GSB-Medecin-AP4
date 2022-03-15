package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserDAO {

	private static ObservableList<User> data = FXCollections.observableArrayList();

	// recherche d'un user
	public static ObservableList<User> getUser(String unLogin, String unMdp) {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();

		try {
			String sql = "SELECT c.* FROM user u, compte c WHERE u.login=c.login and u.login='" + unLogin + "' AND c.mdp='" + unMdp + "'";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				data.add(new User(resultset.getString(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4), resultset.getString(5)));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	// recherche d'un user
	public static ObservableList<User> getUserLoginExist(String unLogin) {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();

		try {
			String sql = "SELECT c.* FROM user u, compte c WHERE u.login=c.login and u.login='" + unLogin + "'";
			//System.out.println(sql);
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				data.add(new User(resultset.getString(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4), resultset.getString(5)));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	// ajouter un nouveau user
	public static int addUser(String unLogin) {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();
		try {
			String sql = "INSERT INTO user VALUES ('" + unLogin + "')";
			// System.out.println(sql);
			PreparedStatement statement = connection.prepareStatement(sql);
			int resultset = statement.executeUpdate();
			connection.close();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	// recherche d'un user
	public static User getUserLogin(String unLogin) {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();
		User user = null;
		try {
			String sql = "SELECT u.login, c.mdp, c.nom, c.prenom, c.tel FROM user u, compte c WHERE u.login=c.login and u.login='" + unLogin + "'";
			// System.out.println(sql);
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				user = new User(resultset.getString(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4), resultset.getString(5));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	// update compte by id
	public static int updateUserByOldLogin(String newLogin, String unmdp, String unnom, String unprenom, String untel, String oldLogin) {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();
		try {
			String sql = "UPDATE compte SET login='" + newLogin + "', mdp='" + unmdp + "', nom='" + unnom + "', prenom='"
					+ unprenom + "', tel='" + untel + "' WHERE login='" + oldLogin + "'";
			//System.out.println(sql);
			PreparedStatement statement = connection.prepareStatement(sql);
			int resultset = statement.executeUpdate();
			connection.close();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	// update compte by id
		public static int updateUserByLogin(String login, String unmdp, String unnom, String unprenom, String untel) {
			ConnectionClass connectionClass = new ConnectionClass();
			Connection connection = connectionClass.getConnection();
			try {
				String sql = "UPDATE compte SET mdp='" + unmdp + "', nom='" + unnom + "', prenom='"
						+ unprenom + "', tel='" + untel + "' WHERE login='" + login + "'";
				// System.out.println(sql);
				PreparedStatement statement = connection.prepareStatement(sql);
				int resultset = statement.executeUpdate();
				connection.close();
				return 1;
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
		}


}
