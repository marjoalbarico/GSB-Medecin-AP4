package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modele.Medecin;

public class ModifControleur implements Initializable {

	@FXML
	private TextField prenomField;

	@FXML
	private TextField speField;

	@FXML
	private TextField numeroField;

	@FXML
	private Button btnAnnuler;

	@FXML
	private TextField villeField;

	@FXML
	private AnchorPane paneID;

	@FXML
	private TextField telField;

	@FXML
	private TextField rueField;

	@FXML
	private TextField deptField;

	@FXML
	private TextField nomField;

	@FXML
	private TextField cpField;

	@FXML
	private Button btnValider;

	private int id;

	private ObservableList<Medecin> data = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		// forcer champ departement soit un numero
		this.deptField.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					deptField.setText(newValue.replaceAll("[^\\d]", ""));
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

	// modification
	@FXML
	public void buttonUpdate(ActionEvent event) {
		if (this.telField.getText().length()!=10) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
			alert.setHeaderText("Veuillez remplir le champ TELEPHONE (10 chiffres) correctement SVP. \n");
			alert.showAndWait();
		} else {
			this.data=modele.MedecinDAO.getMedsLikeAll(this.nomField.getText(), this.prenomField.getText(), this.adrForm(), this.telField.getText(), this.deptField.getText(), this.speField.getText());
			if (this.data.size()>0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
				alert.setHeaderText("Médecin existe déjà");
				alert.showAndWait();
				this.data.clear();
			} else {
				modele.MedecinDAO.updateMedById(this.getId(), this.nomField.getText(), this.prenomField.getText(), this.adrForm(), this.telField.getText(), this.deptField.getText(), this.speField.getText());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
				Stage stage = (Stage) this.btnAnnuler.getScene().getWindow();
				stage.close();
				alert.setHeaderText("Mis à jour terminé! Vous pouvez rechercher le médecin pour actualiser le tableau");
				alert.showAndWait();
				this.data.clear();
			}
		}

	}

	// fermeture de stage
	@FXML
	public void buttonAnnuler(ActionEvent event) {
		Stage stage = (Stage) this.btnAnnuler.getScene().getWindow();
		stage.close();
	}

	// set les prompt texts
	public void setNom(String text) {
		this.nomField.setText(text);
		// System.out.println("id: "+this.getId());
	}

	public void setPrenom(String text) {
		this.prenomField.setText(text);
	}

	public void setTel(String text) {
		this.telField.setText(text);
	}

	public void setDept(String text) {
		this.deptField.setText(text);
	}

	public void setSpe(String text) {
		this.speField.setText(text);
	}

	// separation d'adresse
	public void setAdr(String text) {
		String[] words = text.split("\\s");// splits the string based on string
		// using java foreach loop to print elements of string array
		// for(String w:words){
		// System.out.println(w);
		// }

		String rue = "";
		for (int i = 0; i < words.length; i++) {
			if (i == 0) {
				this.numeroField.setText(words[i]);
			} else if (i == words.length - 1) {
				this.cpField.setText(words[i]);
			} else if (i == words.length - 2) {
				this.villeField.setText(words[i]);
			} else if (i == words.length - 2) {
				rue = rue + words[i] + "SPACE";
			} else {
				rue = rue + words[i] + " ";
			}
		}
		this.rueField.setText(rue);

	}

	// set id de med
	public void setMedId(int id) {
		this.id = id;
	}

	// get id de med
	public int getId() {
		return this.id;
	}

	// get adresse
	public String adrForm() {
		return this.numeroField.getText() + " " + this.villeField.getText() + " " + this.villeField.getText() + " "
				+ this.cpField.getText();
	}
}
