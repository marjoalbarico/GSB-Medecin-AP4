package controleur;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modele.Admin;
import modele.Medecin;
import modele.User;

public class AccueilControleur implements Initializable {

	@FXML
	private TableView<Medecin> tableview;

	@FXML
	private TableColumn<Medecin, String> colNom;

	@FXML
	private TableColumn<Medecin, String> colPre;

	@FXML
	private TableColumn<Medecin, String> colAdr;

	@FXML
	private TableColumn<Medecin, String> colTel;

	@FXML
	private TableColumn<Medecin, String> colSpe;

	@FXML
	private TableColumn<Medecin, Integer> colDept;

	@FXML
	private Label labelID;

	@FXML
	private Label labelID1;

	@FXML
	private TextField searchSpefield;

	@FXML
	private TextField searchDeptfield;

	@FXML
	private TextField searchNomField;

	@FXML
	private Button menuButtonAjouter;

	@FXML
	private Label labelModSup;

	@FXML
	private Button buttonSupprimer;

	@FXML
	private Button buttonModifier;

	private ObservableList<Medecin> data = FXCollections.observableArrayList();

	@FXML
	private Button menuButtonFermer;

	private String userlogin;
	private String adminlogin;
	private User u;
	private Admin ad;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// récuperation des medecins
		this.data = modele.MedecinDAO.getAllMeds();
		// stocker les données dans tableview
		stockItems();
		// affichage des medecins
		this.tableview.setItems(this.data);

		// forcer champ departement soit un numero
		this.searchDeptfield.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					searchDeptfield.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

	}

	// afficher tous les médecins
	@FXML
	public void buttonAfficher(ActionEvent actionEvent) throws SQLException {
		// System.out.println("TOUS LES MEDECINS");
		this.searchNomField.clear();
		this.searchDeptfield.clear();
		this.searchSpefield.clear();
		this.labelID.setText("La liste de tous les médecins");
		this.data.clear();
		// récuperation des medecins
		this.data = modele.MedecinDAO.getAllMeds();
		// stocker les données
		stockItems();
		// affichage des medecins
		this.tableview.setItems(this.data);
	}

	// nouvelle fenêtre, ajouteMed
	@FXML
	public void buttonAjouter(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("../vue/ajouteMed.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 630, 400);
			String css = this.getClass().getResource("../vue/application.css").toExternalForm();
			scene.getStylesheets().add(css);
			Stage stage = new Stage();
			stage.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			System.out.println("FAILED CAN'T CREATE WINDOW AJOUTEMED");
		}
	}

	// liste ascendante de med par nom
	@FXML
	public void NomAZ(ActionEvent event) {
		if (this.searchNomField != null || this.searchDeptfield != null || this.searchSpefield != null) {
			this.colNom.setSortType(TableColumn.SortType.ASCENDING);
			this.tableview.getSortOrder().add(this.colNom);
			this.tableview.getOnSort();
		} else {
			this.data.clear();
			// récuperation des medecins
			this.data = modele.MedecinDAO.getMedsNomAsc();
			stockItems();
			// affichage des medecins
			this.tableview.setItems(this.data);
		}
	}

	// liste descendante de med par nom
	@FXML
	public void NomZA(ActionEvent event) {
		if (this.searchNomField != null || this.searchDeptfield != null || this.searchSpefield != null) {
			this.colNom.setSortType(TableColumn.SortType.DESCENDING);
			this.tableview.getSortOrder().add(this.colNom);
			this.tableview.getOnSort();
		} else {
			this.data.clear();
			// récuperation des medecins
			this.data = modele.MedecinDAO.getMedsNomDesc();
			stockItems();
			// affichage des medecins
			this.tableview.setItems(this.data);
		}
	}

	// liste ascendante de med par dept
	@FXML
	public void DeptAsc(ActionEvent event) {
		if (this.searchNomField != null || this.searchDeptfield != null || this.searchSpefield != null) {
			this.colDept.setSortType(TableColumn.SortType.ASCENDING);
			this.tableview.getSortOrder().add(this.colDept);
			this.tableview.getOnSort();
		} else {
			this.data.clear();
			// récuperation des medecins
			this.data = modele.MedecinDAO.getMedsDeptAsc();
			stockItems();
			// affichage des medecins
			this.tableview.setItems(this.data);
		}
	}

	// liste descendante de med par dept
	@FXML
	public void DepDes(ActionEvent event) {
		if (this.searchNomField != null || this.searchDeptfield != null || this.searchSpefield != null) {
			this.colDept.setSortType(TableColumn.SortType.DESCENDING);
			this.tableview.getSortOrder().add(this.colDept);
			this.tableview.getOnSort();
		} else {
			this.data.clear();
			// récuperation des medecins
			this.data = modele.MedecinDAO.getMedsDeptDesc();
			stockItems();
			// affichage des medecins
			this.tableview.setItems(this.data);
		}
	}

	// liste ascendante de med par spe
	@FXML
	public void SpeAZ(ActionEvent event) {
		if (this.searchNomField != null || this.searchDeptfield != null || this.searchSpefield != null) {
			this.colSpe.setSortType(TableColumn.SortType.ASCENDING);
			this.tableview.getSortOrder().add(this.colSpe);
			this.tableview.getOnSort();
		} else {
			this.data.clear();
			// récuperation des medecins
			this.data = modele.MedecinDAO.getMedsSpeAsc();

			stockItems();

			// affichage des medecins
			this.tableview.setItems(this.data);
		}
	}

	// liste descendante de med par spe
	@FXML
	public void speZA(ActionEvent event) {
		if (this.searchNomField != null || this.searchDeptfield != null || this.searchSpefield != null) {
			this.colSpe.setSortType(TableColumn.SortType.DESCENDING);
			this.tableview.getSortOrder().add(this.colSpe);
			this.tableview.getOnSort();
		} else {
			this.data.clear();
			// récuperation des medecins
			this.data = modele.MedecinDAO.getMedsSpeDesc();
			stockItems();
			// affichage des medecins
			this.tableview.setItems(this.data);
		}
	}

	// stocker les meds dans les colonnes de table
	public void stockItems() {
		this.colNom.setCellValueFactory(new PropertyValueFactory<Medecin, String>("nom"));
		this.colPre.setCellValueFactory(new PropertyValueFactory<Medecin, String>("prenom"));
		this.colAdr.setCellValueFactory(new PropertyValueFactory<Medecin, String>("adresse"));
		this.colTel.setCellValueFactory(new PropertyValueFactory<Medecin, String>("tel"));
		this.colSpe.setCellValueFactory(new PropertyValueFactory<Medecin, String>("specialite"));
		this.colDept.setCellValueFactory(new PropertyValueFactory<Medecin, Integer>("dept"));
	}

	// function dept adresse
	public String generateSqlWhere(String adrDept) {
		String res = "%" + adrDept;
		for (int i = adrDept.length(); i < 5; i++) {
			res = res + "_";
		}
		res = res + "%";
		return res;
	}

	// recherche avec nom, dept, spe
	public void searchAll() {
		this.labelID.setText("La listesssss des médecins par nom, département et spécialité recherché");
		this.data.clear();
		if (this.searchDeptfield.getText().length() == 1) {
			this.data = modele.MedecinDAO.getMedsLikeNomDeptSpe(this.searchNomField.getText(),
					this.searchDeptfield.getText(), this.searchSpefield.getText());
		} else { // departement adresse
			this.data = modele.MedecinDAO.getMedsLikeNomDeptAdrSpe(this.searchNomField.getText(),
					generateSqlWhere(this.searchDeptfield.getText()), this.searchSpefield.getText());
		}
		stockItems();
		this.tableview.setItems(this.data);
	}

	// recherche med par nom & dept adresse
	public void searchMedNomDeptAdr() {
		this.labelID.setText("La liste des médecins par nom et département recherché");
		this.data.clear();
		if (this.searchDeptfield.getText().length() == 1) {
			this.data = modele.MedecinDAO.getMedsLikeNomDept(this.searchNomField.getText(),
					this.searchDeptfield.getText());
		} else { // departement adresse
			this.data = modele.MedecinDAO.getMedsLikeNomDeptAdr(this.searchNomField.getText(),
					generateSqlWhere(this.searchDeptfield.getText()));
		}
		stockItems();
		this.tableview.setItems(this.data);
	}

	// recherche med par dept adresse
	public void searchMedDeptAdr() {
		this.labelID.setText("La liste des médecins par nom et département recherché");
		this.data.clear();
		if (this.searchDeptfield.getText().length() == 1) {
			this.data = modele.MedecinDAO.getMedsLikeDept(this.searchDeptfield.getText());
		} else { // departement adresse
			this.data = modele.MedecinDAO.getMedsLikeDeptAdr(generateSqlWhere(this.searchDeptfield.getText()));
		}
		stockItems();
		this.tableview.setItems(this.data);
	}

	// recherche med par dept adresse
	public void searchMedDeptAdrSpe() {
		this.labelID.setText("La liste des médecins par département et spécialité recherché");
		this.data.clear();
		if (this.searchDeptfield.getText().length() == 1) {
			this.data = modele.MedecinDAO.getMedsLikeDeptSpe(this.searchDeptfield.getText(),
					this.searchSpefield.getText());
		} else { // departement adresse
			this.data = modele.MedecinDAO.getMedsLikeDeptAdrSpe(generateSqlWhere(this.searchDeptfield.getText()), this.searchSpefield.getText());
		}
		stockItems();
		this.tableview.setItems(this.data);
	}

	// chercher un ou plusieurs médecins
	@FXML
	public void btnSearch(ActionEvent event) {
		// recherche avec nom, département et spécialité
		if (this.searchNomField.getText().isEmpty() == false && this.searchDeptfield.getText().isEmpty() == false
				&& this.searchSpefield.getText().isEmpty() == false) {
			// System.out.println("recherche med nom dept spe");
			searchAll();
		} else {
			if (this.searchNomField.getText().isEmpty() == false) {
				// recherche qu'avec nom
				if (this.searchDeptfield.getText().isEmpty() == true
						&& this.searchSpefield.getText().isEmpty() == true) {
					// System.out.println("recherche med nom");
					this.labelID.setText("La liste des médecins par nom recherché");
					this.data.clear();
					this.data = modele.MedecinDAO.getMedsLikeNom(this.searchNomField.getText());
					stockItems();
					this.tableview.setItems(this.data);
					// recherche nom et dept
				} else if (this.searchDeptfield.getText().isEmpty() == false
						&& this.searchSpefield.getText().isEmpty() == true) {
					// System.out.println("recherche med nom dept");
					searchMedNomDeptAdr();
					// recherche nom et spe
				} else if (this.searchDeptfield.getText().isEmpty() == true
						&& this.searchSpefield.getText().isEmpty() == false) {
					// System.out.println("recherche med nom spe");
					this.labelID.setText("La liste des médecins par nom et spécialité recherché");
					this.data.clear();
					this.data = modele.MedecinDAO.getMedsLikeNomSpe(this.searchNomField.getText(),
							this.searchSpefield.getText());
					stockItems();
					this.tableview.setItems(this.data);
				} else {
					System.out.println("1 MESSAGE D'ERREUR");
				}
			} else if (this.searchDeptfield.getText().isEmpty() == false) {
				// recherche med dept
				if (this.searchSpefield.getText().isEmpty() == true) {
					// System.out.println("recherche med dept");
					searchMedDeptAdr();
					// recherche med dept spe
				} else if (this.searchSpefield.getText().isEmpty() == false) {
					// System.out.println("recherche med dept spe");
					searchMedDeptAdrSpe();
				} else {
					System.out.println("2 MESSAGE D'ERREUR");
				}
				// recherche med spe
			} else if (this.searchSpefield.getText().isEmpty() == false) {
				// System.out.println("recherche med spe");
				this.labelID.setText("La liste des médecins par specialité recherché");
				this.data.clear();
				this.data = modele.MedecinDAO.getMedsLikeSpe(this.searchSpefield.getText());
				stockItems();
				this.tableview.setItems(this.data);
			} else {
				System.out.println("3 MESSAGE D'ERREUR");
			}
		}

	}

	// fermer l'application
	@FXML
	public void buttonFermer(ActionEvent event) {
		((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/login.fxml"));
			Parent rootLogin = loader.load();

			Stage stage = new Stage();
			stage.setScene(new Scene(rootLogin));
			stage.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// modification d'un médecin, affichage de vue modifMed.fxml
	@FXML
	public void modifierMed(ActionEvent event) {
		ObservableList<Medecin> selectedItems = tableview.getSelectionModel().getSelectedItems();

		if (selectedItems.isEmpty() == true) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
			alert.setHeaderText("Veuillez sélectionner un médecin SVP");
			alert.showAndWait();
		} else {
			Medecin med = this.tableview.getSelectionModel().getSelectedItem();
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/infoMed.fxml"));
				Parent root = loader.load();

				// The following both lines are the only addition we need to pass the arguments
				ModifControleur modifControl = loader.getController();
				modifControl.setMedId(med.getId());
				modifControl.setNom(med.getNom());
				modifControl.setPrenom(med.getPrenom());
				modifControl.setAdr(med.getAdresse());
				modifControl.setTel(med.getTel());
				modifControl.setDept(med.getDeptStr());
				modifControl.setSpe(med.getSpecialite());

				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.setTitle("MODIFIER UN MEDECIN");
				stage.show();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// Supprimer le médecin sélectionné
	@FXML
	public void supprimerMed(ActionEvent event) {
		ObservableList<Medecin> selectedItems = tableview.getSelectionModel().getSelectedItems();

		if (selectedItems.isEmpty() == true) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
			alert.setHeaderText("Veuillez sélectionner un médecin SVP");
			alert.showAndWait();
		} else {
			Medecin med = this.tableview.getSelectionModel().getSelectedItem();
			// message de confirmation avant de supprimer
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("SUPPRIMER UN MEDECIN");
			alert.setHeaderText("Etes-vous sûr de supprimer ce médecin? \n" + "NOM : " + med.getNom() + "\n"
					+ "PRENOM : " + med.getPrenom() + "\n");

			ButtonType non = new ButtonType("NON");
			ButtonType oui = new ButtonType("OUI");

			// Remove default ButtonTypes
			alert.getButtonTypes().clear();

			alert.getButtonTypes().addAll(non, oui);

			// option != null.
			Optional<ButtonType> option = alert.showAndWait();

			// option non
			if (option.get() == null || option.get() == non) {
				alert.close(); // fermer l'alert
			} else { // option oui
				int rs = modele.MedecinDAO.delMedById(med.getId());
				if (rs == 1) {
					Alert alertOK = new Alert(AlertType.INFORMATION);
					alertOK.setTitle("SUPPRIMER UN MEDECIN");
					this.tableview.getItems().remove(med);
					alertOK.setHeaderText("Médecin supprimé \n");
					alertOK.showAndWait();
				} else {
					Alert alertNON = new Alert(AlertType.INFORMATION);
					alertNON.setTitle("SUPPRIMER UN MEDECIN");
					alertNON.setHeaderText("Echoué!");
					alertNON.showAndWait();
				}
			}
		}

	}

	// profil admin
	@FXML
	public void buttonProfilAdmin(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/profilInfo.fxml"));
			Parent rootProAd = loader.load();

			// The following both lines are the only addition we need to pass the arguments
			ProfilControleur procon = loader.getController();
			ad = modele.AdminDAO.getAdminLoginC(this.adminlogin);
			procon.setAdmin(this.ad);

			Stage stage = new Stage();
			stage.setScene(new Scene(rootProAd));
			stage.setTitle("MON PROFIL");
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// add admin
	@FXML
	public void buttonAddAdmin(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("../vue/ajouteAdmin.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 600, 434);
			String css = this.getClass().getResource("../vue/application.css").toExternalForm();
			scene.getStylesheets().add(css);
			Stage stage = new Stage();
			stage.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			System.out.println("FAILED CAN'T CREATE WINDOW AJOUTEADMIN");
			e.printStackTrace();
		}
	}

	// nouvelle fenetre que s'affiche les info d'user
	@FXML
	public void buttonProfilUser(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/profilInfo.fxml"));
			Parent root = loader.load();

			// The following both lines are the only addition we need to pass the arguments
			ProfilControleur procon = loader.getController();
			u = modele.UserDAO.getUserLogin(this.userlogin);
			procon.setUser(u);

			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("MES INFORMATIONS");
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// set login de user
	public void setLoginUser(String login) {
		this.userlogin = login;
	}

	// set login d'admin
	public void setLoginAdmin(String login) {
		this.adminlogin = login;
	}

}
