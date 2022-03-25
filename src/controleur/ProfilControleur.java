package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modele.Admin;
import modele.User;

public class ProfilControleur implements Initializable {

	@FXML
	private TextField prenomField;

	@FXML
	private PasswordField mdpCField;

	@FXML
	private Button btnAnnuler;

	@FXML
	private PasswordField mdpField;

	@FXML
	private AnchorPane paneID;

	@FXML
	private TextField telField;

	@FXML
	private TextField nomField;

	@FXML
	private TextField loginField;

	@FXML
	private Button btnValider;

	private User user;
	private Admin admin;
	String compte;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		// forcer champ departement soit un numero
		this.telField.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					telField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

	}

	// mettre à jour les info
	@FXML
	public void mettreAJour(ActionEvent event) {
		if (this.mdpCField.getText().isEmpty() == true) {
			messageMdp();
		} else {
			if (getCompte() == "U") {
				// mdp correct
				if (this.mdpCField.getText().contentEquals(this.user.getMdp())) {
					String newLogin = this.loginField.getText();
					// l'user ne veut pas de changer de login
					if (this.user.getLogin().contentEquals(newLogin)) {
						int resultat1 = modele.UserDAO.updateUserByLogin(this.loginField.getText(),
								this.mdpField.getText(), this.nomField.getText(), this.prenomField.getText(),
								this.telField.getText());
						if (resultat1 == 1) {
							messageReussi();
							this.mdpCField.setText(null);
						} else {
							messageEchec();
						}
						// si l'user veut changer de login
						// faut d'abord verifier si login exist ou pas
					} else {
						// System.out.println("changement de login");
						ObservableList<User> listUs = modele.UserDAO.getUserLoginExist(newLogin);
						if (listUs.isEmpty() == true) {
							int resultat2 = modele.UserDAO.updateUserByOldLogin(newLogin, this.mdpField.getText(),
									this.nomField.getText(), this.prenomField.getText(), this.telField.getText(),
									this.user.getLogin());
							if (resultat2 == 1) {
								messageReussi();
							} else {
								messageEchec();
							}
						} else {
							messageLoginExiste();
							listUs.clear();
						}
					}
					// mdp pas correct
				} else {
					// System.out.println(" mdp pas correct");
					messageMdpC();
				}
			} else if (getCompte() == "A") {
				// mdp correct
				if (this.mdpCField.getText().contentEquals(this.admin.getMdp())) {
					String newLoginAd = this.loginField.getText();
					// l'admin ne veut pas de changer de login
					if (this.admin.getLogin().contentEquals(newLoginAd)) {
						int resultat3 = modele.AdminDAO.updateAdminByLogin(this.loginField.getText(),
								this.mdpField.getText(), this.nomField.getText(), this.prenomField.getText(),
								this.telField.getText());
						if (resultat3 == 1) {
							messageReussi();
							this.mdpCField.setText(null);
						} else {
							messageEchec();
						}
						// si l'admin veut changer de login
						// faut d'abord verifier si login exist ou pas
					} else {
						// System.out.println("changement de login");
						ObservableList<Admin> listAd = modele.AdminDAO.getAdminLogin(newLoginAd);
						if (listAd.isEmpty() == true) {
							int resultat4 = modele.AdminDAO.updateAdminByOldLogin(newLoginAd, this.mdpField.getText(),
									this.nomField.getText(), this.prenomField.getText(), this.telField.getText(),
									this.admin.getLogin());
							if (resultat4 == 1) {
								messageReussi();
							} else {
								messageEchec();
							}
						} else {
							messageLoginExiste();
							listAd.clear();
						}
					}
					// mdp pas correct
				} else {
					// System.out.println(" mdp pas correct");
					messageMdpC();
				}
			} else {
				System.out.println("String compte not defined");
			}
		}
		
	}

	// fermer la fenetre actuelle
	@FXML
	public void annuler(ActionEvent event) {
		Stage stage = (Stage) this.btnAnnuler.getScene().getWindow();
		stage.close();
	}

	// messages (dialog box)
	public void messageLoginExiste() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
		alert.setHeaderText("Login déjà pris");
		alert.showAndWait();
	}

	public void messageMdp() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
		alert.setHeaderText("Pour confirmer la modification, veuillez remplir votre mot de passe actuel SVP");
		alert.showAndWait();
	}

	public void messageMdpC() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
		alert.setHeaderText("Veuillez remplir votre mot de passe actuel SVP");
		alert.showAndWait();
	}

	public void messageReussi() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
		alert.setHeaderText("Vos informations sont mises à jour!");
		alert.showAndWait();
	}

	public void messageEchec() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
		alert.setHeaderText("Mis à jour pas effectué");
		alert.showAndWait();
	}

	// setters
	public void setUser(User u) {
		this.user = u;
		updateInfoUser(u);
		this.compte = "U";
	}

	public void setAdmin(Admin ad) {
		this.admin = ad;
		updateInfoAdmin(ad);
		this.compte = "A";
	}

	public String getCompte() {
		return this.compte;
	}

	// stocker les info dans les fields/aux champs
	public void updateInfoUser(User u) {
		this.nomField.setText(u.getNom());
		this.prenomField.setText(u.getPrenom());
		this.telField.setText(u.getTel());
		this.loginField.setText(u.getLogin());
		this.mdpField.setText(u.getMdp());
	}

	public void updateInfoAdmin(Admin ad) {
		this.nomField.setText(ad.getNom());
		this.prenomField.setText(ad.getPrenom());
		this.telField.setText(ad.getTel());
		this.loginField.setText(ad.getLogin());
		this.mdpField.setText(ad.getMdp());
	}
}
