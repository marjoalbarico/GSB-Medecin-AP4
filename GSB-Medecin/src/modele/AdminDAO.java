package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdminDAO {

	private static ObservableList<Admin> data = FXCollections.observableArrayList();

	// recherche d'un med
	public static ObservableList<Admin> getAdmin(String unLogin, String unMdp) {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();

		try {
			String sql = "SELECT c.* FROM admin a, compte c WHERE a.login_compte=c.login AND a.login='" + unLogin + "' AND c.mdp='" + unMdp + "'";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				data.add(new Admin(resultset.getString(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4), resultset.getString(5)));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	// recherche d'un admin
	public static ObservableList<Admin> getAdminLogin(String unLogin) {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();
		try {
			String sql = "SELECT c.* FROM admin a, compte c WHERE a.login_compte=c.login AND a.login='" + unLogin + "'";
			// System.out.println(sql);
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				data.add(new Admin(resultset.getString(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4), resultset.getString(5)));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return data;
	}

	// ajouter un nouveau user
	public static int addAdmin(String unLogin) {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();
		try {
			String sql = "INSERT INTO admin VALUES ('" + unLogin + "', '" + unLogin + "')";
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

	// recherche d'un admin
	public static Admin getAdminLoginC(String unLogin) {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();
		Admin admin = null;
		try {
			String sql = "SELECT a.login, c.mdp, c.nom, c.prenom, c.tel FROM admin a, compte c WHERE a.login=c.login AND a.login='" + unLogin + "'";
			// System.out.println(sql);
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				admin = new Admin(resultset.getString(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4), resultset.getString(5));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return admin;
	}

	// update compte by id
	public static int updateAdminByOldLogin(String newLogin, String unmdp, String unnom, String unprenom, String untel,
			String oldLogin) {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();
		try {
			String sql = "UPDATE compte SET login='" + newLogin + "', mdp='" + unmdp + "', nom='" + unnom + "', prenom='"
					+ unprenom + "', tel='" + untel + "' WHERE login='" + oldLogin + "'";
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

	// update compte by id
	public static int updateAdminByLogin(String login, String unmdp, String unnom, String unprenom, String untel) {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();
		try {
			String sql = "UPDATE compte SET mdp='" + unmdp + "', nom='" + unnom + "', prenom='" + unprenom + "', tel='"
					+ untel + "' WHERE login='" + login + "'";
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
