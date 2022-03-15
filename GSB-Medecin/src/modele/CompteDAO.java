package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CompteDAO {
	
	private static ObservableList<Compte> data = FXCollections.observableArrayList();

	public static ObservableList<Compte> getAllCompte(String unLogin, String unMdp) {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();

		try {
			String sql = "SELECT * FROM compte WHERE login='" + unLogin + "' AND mdp='" + unMdp + "'";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				data.add(new Compte(resultset.getString(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4), resultset.getString(5)));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	// ajouter un nouveau user
		public static int addCompte(String unNom, String unPrenom, String unLogin, String unMdp, String unTel) {
			ConnectionClass connectionClass = new ConnectionClass();
			Connection connection = connectionClass.getConnection();
			try {
				String sql = "INSERT INTO compte (login, mdp, nom, prenom, tel) VALUES ('" + unLogin + "', '" + unMdp + "', '"
						+ unNom + "', '" + unPrenom + "', " + unTel + ")";
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
