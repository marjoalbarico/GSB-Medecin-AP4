package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modele.Admin;
import modele.User;

public class LoginControleur {

	@FXML
	private TextField prenomCre;

	@FXML
	private TextField telCre;

	@FXML
	private PasswordField mdpField;

	@FXML
	private PasswordField mdpCre;

	@FXML
	private TextField nomCre;

	@FXML
	private AnchorPane paneID;

	@FXML
	private TextField loginCre;

	@FXML
	private TextField loginField;

	@FXML
	private Button btnSeConnecter;

	private ObservableList<User> dataUser = FXCollections.observableArrayList();
	private ObservableList<Admin> dataAdmin = FXCollections.observableArrayList();

	// se coonecter à un compte
	@FXML
	public void seConnecterButton(ActionEvent event) {
		this.dataAdmin = modele.AdminDAO.getAdmin(this.loginField.getText(), this.mdpField.getText());
		this.dataUser = modele.UserDAO.getUser(this.loginField.getText(), this.mdpField.getText());

		// compte n'existe pas
		if (this.dataAdmin.isEmpty() == true && this.dataUser.isEmpty() == true) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
			alert.setHeaderText("COMPTE N'EXISTE PAS \n" + "Votre login et/ou mot de passe est/sont incorrect/s");
			alert.showAndWait();
			// compte ADMIN
		} else if (this.dataAdmin.isEmpty() == false && this.dataUser.isEmpty() == true) {
			showAlertAdmin();
			appAdmin();
			this.dataAdmin.clear();
			// compte USER
		} else if (this.dataAdmin.isEmpty() == true && this.dataUser.isEmpty() == false) {
			showAlertUser();
			appUser();
			this.dataUser.clear();
		} else {
			//System.out.println("COMPTE EXISTE USER & ADMIN");
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("SUPPRIMER UN MEDECIN");
			alert.setHeaderText("Vous avez un compte admin et un compte user. Quel compte vous voulez utiliser?");

			ButtonType admin = new ButtonType("COMPTE ADMIN");
			ButtonType user = new ButtonType("COMPTE USER");

			// Remove default ButtonTypes
			alert.getButtonTypes().clear();

			alert.getButtonTypes().addAll(admin, user);

			// option != null.
			Optional<ButtonType> option = alert.showAndWait();

			// option admin
			if (option.get() == admin) {
				showAlertAdmin();
				appAdmin();
				
			} else { // option user
				showAlertUser();
				appUser();
			}
			this.dataAdmin.clear();
			this.dataUser.clear();
		}
	}

	//creer un nouveau compte user
	@FXML
	public void creerButton(ActionEvent event) {

		// verifier que tous les champs sont bien remplis
		if (this.nomCre.getText().isEmpty() == true || this.prenomCre.getText().isEmpty() == true
				|| this.loginCre.getText().isEmpty() == true || this.mdpCre.getText().isEmpty() == true
				|| this.telCre.getText().isEmpty() == true || this.loginCre.getText().length() > 25
				|| this.mdpCre.getText().length() > 12) {
			showAlertError();
		} else {
			this.dataUser = modele.UserDAO.getUserLoginExist(this.loginCre.getText());
			if (this.dataUser.size() >= 1) {
				showAlertCompteExist();
				this.dataUser.clear();
			} else {
				String nom = toTitleCase(this.nomCre.getText());
				String prenom = toTitleCase(this.prenomCre.getText());
				int i = modele.CompteDAO.addCompte(nom, prenom, this.loginCre.getText(), this.mdpCre.getText(), this.telCre.getText());
				i = i+ modele.UserDAO.addUser(this.loginCre.getText());
				if (i == 2) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
					alert.setHeaderText("Compte user ajouté avec succès");
					alert.showAndWait();
					this.nomCre.setText(null);
					this.prenomCre.setText(null);
					this.loginCre.setText(null);
					this.mdpCre.setText(null);
					this.telCre.setText(null);
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
					alert.setHeaderText("échoué");
					alert.showAndWait();
				}
			}
		}
	}

	// mettre en majuscule la 1ere lettre de mot
	public static String toTitleCase(String givenString) {
		String[] arr = givenString.split(" ");
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < arr.length; i++) {
			sb.append(Character.toUpperCase(arr[i].charAt(0))).append(arr[i].substring(1)).append(" ");
		}
		return sb.toString().trim();
	}

	// dialog box à afficher avant lancement d'application
	public void showAlertError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
		alert.setHeaderText("Veuillez remplir les champs correctement");
		alert.showAndWait();
	}

	// dialog box à afficher avant lancement d'application
	public void showAlertCompteExist() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
		alert.setHeaderText("Login déjà pris");
		alert.showAndWait();
	}

	// dialog box à afficher avant lancement d'application
	public void showAlertAdmin() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("LABORATOIRE GALAXY SWISS BOURDIN - COMPTE ADMIN");
		alert.setHeaderText("BIENVENUE ! \n " + "\n" + "Voici tout ce que vous pouvez faire avec l'application GSB: \n "
				+ "- Consulter la liste des médecins \n "
				+ "- Trier la liste ascendante ou descendante par nom ou departement ou spécialité \n "
				+ "- Rechercher un médecin par nom et/ou departement et/ou spécialité \n " + "- Ajouter un médecin\n "
				+ "- Modifier une ou plusieurs information(s) concernant un médecin\n " + "- Supprimer un médecin\n ");
		alert.showAndWait();
	}

	// dialog box à afficher avant lancement d'application
	public void showAlertUser() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("LABORATOIRE GALAXY SWISS BOURDIN - COMPTE USER");
		alert.setHeaderText("BIENVENUE ! \n " + "\n" + "Voici tout ce que vous pouvez faire avec l'application GSB: \n "
				+ "- Consulter la liste des médecins \n "
				+ "- Trier la liste ascendante ou descendante par nom ou departement ou spécialité \n "
				+ "- Rechercher un médecin par nom et/ou departement et/ou spécialité \n ");
		alert.showAndWait();
	}

	// lancer l'application ADMIN
	public void appAdmin() {
		Stage stage = (Stage) this.btnSeConnecter.getScene().getWindow();
		stage.close();
		try {
			URL fxmlfile = this.getClass().getResource("../vue/accueilAdmin.fxml");
			// Create the scene graph
			FXMLLoader loader = new FXMLLoader( fxmlfile );
			//FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/accueilAdmin.fxml"));
			Parent root = loader.load();

			// The following both lines are the only addition we need to pass the arguments
			AccueilControleur accueilCon = loader.getController();
			accueilCon.setLoginAdmin(this.loginField.getText());

			Stage stageUser = new Stage();
			stageUser.setScene(new Scene(root));
			stageUser.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
			stageUser.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// lancer l'application USER
	public void appUser() {
		Stage stage = (Stage) this.btnSeConnecter.getScene().getWindow();
		stage.close();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/accueilUser.fxml"));
			Parent root = loader.load();

			// The following both lines are the only addition we need to pass the arguments
			AccueilControleur accueilCon = loader.getController();
			accueilCon.setLoginUser(this.loginField.getText());

			Stage stageUser = new Stage();
			stageUser.setScene(new Scene(root));
			stageUser.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
			stageUser.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
