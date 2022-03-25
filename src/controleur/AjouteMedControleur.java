package controleur;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modele.Medecin;

public class AjouteMedControleur implements Initializable {

	@FXML
	private TextField prenomField;

	@FXML
	private TextField speField;

	@FXML
	private TextField numeroField;

	@FXML
	private TextField villeField;

	@FXML
	private AnchorPane paneID;

	@FXML
	private TextField telField;

	@FXML
	private TextField rueField;

	@FXML
	private TextField nomField;

	@FXML
	private TextField cpField;

	@FXML
	private TextField depField;

	@FXML
	private Button btnAnnuler;

	@FXML
	private Button btnValider;

	private String adr;

	private String nom;

	private String prenom;

	private String tel;

	private String dept;

	private String spe;

	private ObservableList<Medecin> data = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		// forcer champ departement soit un numero
		this.depField.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					depField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		// forcer champ cp soit un numero
		this.cpField.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					cpField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		// forcer champ tel soit un numero
		this.telField.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					telField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		// forcer champ tel soit un numero
		this.numeroField.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					numeroField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
	}

	// button ajouter un nouveau med
	@FXML
	public void buttonValider(ActionEvent event) {

		// vérifier que tous les champs sont bien rempli
		if (this.nomField.getText().isEmpty() == true || this.prenomField.getText().isEmpty() == true
				|| this.numeroField.getText().isEmpty() || this.villeField.getText().isEmpty() == true
				|| this.cpField.getText().isEmpty() || this.telField.getText().isEmpty() == true
				|| this.depField.getText().isEmpty() == true || this.telField.getText().length() != 10 || // telephone
																											// contient
																											// 10chiffres
				Pattern.matches("[a-zA-Z]+", this.telField.getText()) == true || // tel contiennt une/des lettre/s
				this.cpField.getText().length() != 5 || // code postal contient 5 chiffres
				Pattern.matches("[a-zA-Z]+", this.cpField.getText()) == true || // cp contient une/des lettre/s
				Pattern.matches("[a-zA-Z]+", this.depField.getText()) == true || // dep contient une/des lettre/s
				Pattern.matches("[a-zA-Z]+", this.numeroField.getText()) == true // numero contient une/des lettres
		) {
			messageErreur();
		} else {
			this.adr = (this.numeroField.getText().trim() + " " + this.rueField.getText().trim() + " "
					+ this.villeField.getText().toUpperCase().trim() + " " + this.cpField.getText().trim());
			this.nom = this.nomField.getText().trim();
			this.nom = toTitleCase(nom);
			this.prenom = this.prenomField.getText().trim();
			this.prenom = toTitleCase(prenom);
			this.spe = this.speField.getText().toUpperCase().trim();
			this.tel = this.telField.getText();
			this.dept = this.depField.getText();
			
			if(this.spe != "") {
				data = modele.MedecinDAO.getMedsLikeAll(this.nom, this.prenom, this.adr, this.tel, this.dept, this.spe);
			} else if (this.spe == "") {
				data = modele.MedecinDAO.getMedsSpeNull(this.nom, this.prenom, this.adr, this.tel, this.dept);
			} else {
				System.out.println("out of case");
			}

			if (this.data.isEmpty() == false) {
				messageDuplicate();
				this.data.clear();
			} else {
				int rs = modele.MedecinDAO.addMed(this.nom, this.prenom, this.adr, this.tel, this.dept, this.spe);
				if (rs == 1) {
					Stage stage = (Stage) this.btnAnnuler.getScene().getWindow();
					messageRefresh();
					stage.close();
				}
			}
		}
	}

	// fermeture de la fenêtre
	@FXML
	public void buttonAnnuler(ActionEvent event) {
		Stage stage = (Stage) this.btnAnnuler.getScene().getWindow();
		stage.close();
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

	// affichage d'un dialog box, contient le message d'erreur
	public void messageErreur() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
		alert.setHeaderText("Veuillez remplir les champs correctement SVP");
		alert.showAndWait();
	}

	// dialog box, après un nouveau med
	public void messageRefresh() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
		alert.setHeaderText("Médecin ajouté avec succès \n" + "Vous pouvez retrouver le médecin en le recherchant");
		alert.showAndWait();
	}

	// dialog box, après un nouveau med
	public void messageDuplicate() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
		alert.setHeaderText("Médecin existe déjà");
		alert.showAndWait();
	}

}
