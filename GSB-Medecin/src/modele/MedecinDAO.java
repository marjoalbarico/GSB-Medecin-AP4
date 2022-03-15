package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MedecinDAO {

	private static ObservableList<Medecin> data = FXCollections.observableArrayList();
	private static ObservableList<Medecin> dataBis = FXCollections.observableArrayList();

	// récupération de tous les medecins
	public static ObservableList<Medecin> getAllMeds() {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();

		try {
			String sql = "SELECT * FROM medecin";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				data.add(new Medecin(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getInt(7)));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	// liste de medecins descendante par nom
	public static ObservableList<Medecin> getMedsNomDesc() {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();

		try {
			String sql = "SELECT * FROM medecin ORDER BY nom DESC";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				data.add(new Medecin(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getInt(7)));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	// liste de medecins ascendante par nom
	public static ObservableList<Medecin> getMedsNomAsc() {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();

		try {
			String sql = "SELECT * FROM medecin ORDER BY nom";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				data.add(new Medecin(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getInt(7)));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	// liste de medecins ascendante par departement
	public static ObservableList<Medecin> getMedsDeptAsc() {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();

		try {
			String sql = "SELECT * FROM medecin ORDER BY departement";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				data.add(new Medecin(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getInt(7)));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	// liste de medecins descendante par departement
	public static ObservableList<Medecin> getMedsDeptDesc() {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();

		try {
			String sql = "SELECT * FROM medecin ORDER BY departement DESC";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				data.add(new Medecin(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getInt(7)));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	// liste de medecins ascendante par spécialité
	public static ObservableList<Medecin> getMedsSpeAsc() {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();

		try {
			String sql = "SELECT * FROM medecin ORDER BY specialiteComplementaire";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				data.add(new Medecin(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getInt(7)));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	// liste de medecins ascendante par spécialité
	public static ObservableList<Medecin> getMedsSpeDesc() {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();

		try {
			String sql = "SELECT * FROM medecin ORDER BY specialiteComplementaire DESC";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				data.add(new Medecin(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getInt(7)));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	// recherche de medecins par nom
	public static ObservableList<Medecin> getMedsLikeNom(String unNom) {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();

		try {
			String sql = "SELECT * FROM medecin WHERE nom LIKE '" + unNom + "%'";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				data.add(new Medecin(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getInt(7)));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	// recherche de medecins par departement
	public static ObservableList<Medecin> getMedsLikeDept(String unDept) {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();

		try {
			String sql = "SELECT * FROM medecin WHERE departement=" + unDept;
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				data.add(new Medecin(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getInt(7)));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	// recherche de medecins par departement adresse
		public static ObservableList<Medecin> getMedsLikeDeptAdr(String unDeptAdr) {
			ConnectionClass connectionClass = new ConnectionClass();
			Connection connection = connectionClass.getConnection();

			try {
				String sql = "SELECT * FROM medecin WHERE adresse LIKE '" + unDeptAdr +"'";
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultset = statement.executeQuery();
				while (resultset.next()) {
					data.add(new Medecin(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
							resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getInt(7)));
				}
				connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return data;
		}
	
	// recherche de medecins par specialité
	public static ObservableList<Medecin> getMedsLikeSpe(String uneSpe) {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();

		try {
			String sql = "SELECT * FROM medecin WHERE specialiteComplementaire LIKE '" + uneSpe + "%'";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				data.add(new Medecin(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getInt(7)));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	// recherche de medecins par nom et departement
	public static ObservableList<Medecin> getMedsLikeNomDept(String unNom, String unDept) {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();

		try {
			String sql = "SELECT * FROM medecin WHERE nom LIKE '" + unNom + "%' and departement=" + unDept;
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				data.add(new Medecin(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getInt(7)));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	// recherche de medecins par nom et departement adresse
		public static ObservableList<Medecin> getMedsLikeNomDeptAdr(String unNom, String unDeptAdr) {
			ConnectionClass connectionClass = new ConnectionClass();
			Connection connection = connectionClass.getConnection();

			try {
				String sql = "SELECT * FROM medecin WHERE nom LIKE '" + unNom + "%' and adresse LIKE '" + unDeptAdr +"'";
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultset = statement.executeQuery();
				while (resultset.next()) {
					data.add(new Medecin(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
							resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getInt(7)));
				}
				connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return data;
		}

	// recherche de medecins par nom et departement
	public static ObservableList<Medecin> getMedsLikeNomSpe(String unNom, String uneSpe) {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();

		try {
			String sql = "SELECT * FROM medecin WHERE nom LIKE '" + unNom + "%' AND specialiteComplementaire LIKE '"
					+ uneSpe + "%'";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				data.add(new Medecin(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getInt(7)));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	// recherche de medecins par departement et spécialité
	public static ObservableList<Medecin> getMedsLikeDeptSpe(String unDept, String uneSpe) {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();

		try {
			String sql = "SELECT * FROM medecin WHERE departement=" + unDept + " AND specialiteComplementaire LIKE '"
					+ uneSpe + "%'";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				data.add(new Medecin(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getInt(7)));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	// recherche de medecins par departement adresse et spécialité
		public static ObservableList<Medecin> getMedsLikeDeptAdrSpe(String unDept, String uneSpe) {
			ConnectionClass connectionClass = new ConnectionClass();
			Connection connection = connectionClass.getConnection();

			try {
				String sql = "SELECT * FROM medecin WHERE adresse like '" + unDept + "' AND specialiteComplementaire LIKE '"
						+ uneSpe + "%'";
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultset = statement.executeQuery();
				while (resultset.next()) {
					data.add(new Medecin(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
							resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getInt(7)));
				}
				connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return data;
		}

	// recherche de medecins par nom & departement & spécialité
	public static ObservableList<Medecin> getMedsLikeNomDeptSpe(String unNom, String unDept, String uneSpe) {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();

		try {
			String sql = "SELECT * FROM medecin WHERE nom LIKE '" + unNom + "%' AND departement=" + unDept
					+ " AND specialiteComplementaire LIKE '" + uneSpe + "%'";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				data.add(new Medecin(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getInt(7)));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	// recherche de medecins par nom & departement ADR & spécialité
		public static ObservableList<Medecin> getMedsLikeNomDeptAdrSpe(String unNom, String unDeptAdr, String uneSpe) {
			ConnectionClass connectionClass = new ConnectionClass();
			Connection connection = connectionClass.getConnection();

			try {
				String sql = "SELECT * FROM medecin WHERE nom LIKE '" + unNom + "%' AND adresse LIKE '" + unDeptAdr
						+ "' AND specialiteComplementaire LIKE '" + uneSpe + "%'";
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultset = statement.executeQuery();
				while (resultset.next()) {
					data.add(new Medecin(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
							resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getInt(7)));
				}
				connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return data;
		}

	// ajouter un nouveau med
	public static int addMed(String unNom, String unPrenom, String uneAdr, String unTel, String unDept, String uneSpe) {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();
		try {
			String sql = "INSERT INTO medecin (nom, prenom, adresse, tel, specialiteComplementaire, departement) VALUES ('"
					+ unNom + "', '" + unPrenom + "', '" + uneAdr + "', '" + unTel + "', '" + uneSpe + "', " + unDept
					+ ")";
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

	// recherche d'un med
	public static ObservableList<Medecin> getMedsLikeAll(String unNom, String unPrenom, String uneAdr, String unTel,
			String unDept, String uneSpe) {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();

		try {
			String sql = "SELECT * FROM medecin WHERE nom='" + unNom + "' AND prenom='" + unPrenom + "' AND adresse='"
					+ uneAdr + "' AND tel='" + unTel + "' AND specialiteComplementaire='" + uneSpe
					+ "' AND departement=" + unDept;
			//System.out.println(sql);
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				dataBis.add(new Medecin(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getInt(7)));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataBis;
	}
	
	// recherche d'un med avec spe null
		public static ObservableList<Medecin> getMedsSpeNull(String unNom, String unPrenom, String uneAdr, String unTel,
				String unDept) {
			ConnectionClass connectionClass = new ConnectionClass();
			Connection connection = connectionClass.getConnection();

			try {
				String sql = "SELECT * FROM medecin WHERE nom='" + unNom + "' AND prenom='" + unPrenom + "' AND adresse='"
						+ uneAdr + "' AND tel='" + unTel + "' AND departement=" + unDept + " AND specialiteComplementaire IS NULL";
				//System.out.println(sql);
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultset = statement.executeQuery();
				while (resultset.next()) {
					dataBis.add(new Medecin(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
							resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getInt(7)));
				}
				connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return dataBis;
		}

	// supprimer med
	public static int delMedById(int id) {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();
		try {
			String sql = "DELETE FROM medecin WHERE id=" + id;
			// System.out.println(sql);
			PreparedStatement statement = connection.prepareStatement(sql);
			int resultset = statement.executeUpdate();
			connection.close();
			return resultset;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	// update nom
	public static void updateMedById(int unId, String unNom, String unPrenom, String uneAdr, String unTel,
			String unDept, String uneSpe) {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();
		try {
			String sql = "UPDATE medecin SET nom='" + unNom + "', prenom='" + unPrenom + "', adresse='" + uneAdr
					+ "', tel='" + unTel + "', specialiteComplementaire='" + uneSpe + "', departement=" + unDept
					+ " WHERE id=" + unId;
			// System.out.println(sql);
			PreparedStatement statement = connection.prepareStatement(sql);
			int resultset = statement.executeUpdate();
			connection.close();
			// return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			// return 0;
		}
	}

}
