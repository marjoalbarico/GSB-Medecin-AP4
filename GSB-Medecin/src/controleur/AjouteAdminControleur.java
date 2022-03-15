package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modele.Admin;

public class AjouteAdminControleur {

    @FXML
    private TextField loginfield;

    @FXML
    private Button btnAnnuler;

    @FXML
    private TextField prenomfield;

    @FXML
    private TextField telfield;

    @FXML
    private AnchorPane paneID;

    @FXML
    private TextField nomfield;

    @FXML
    private PasswordField mdpfield;

    @FXML
    private Button btnValider;
    
	private ObservableList<Admin> dataAdmin = FXCollections.observableArrayList();

    @FXML
    public void valider(ActionEvent event) {
    	if (this.nomfield.getText().isEmpty()==true || this.prenomfield.getText().isEmpty()==true || this.telfield.getText().isEmpty()==true
    			|| this.loginfield.getText().isEmpty()==true || this.mdpfield.getText().isEmpty()==true) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
    		alert.setHeaderText("Veuillez remplir les champs correctement");
    		alert.showAndWait();
    	} else {
    		this.dataAdmin=modele.AdminDAO.getAdminLogin(this.loginfield.getText());
    		if(this.dataAdmin.size()>0) {
    			Alert alert = new Alert(AlertType.ERROR);
        		alert.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
        		alert.setHeaderText("Login déjà pris");
        		alert.showAndWait();
        		this.dataAdmin.clear();
    		} else {
    			int rs = modele.CompteDAO.addCompte(toTitleCase(this.nomfield.getText()), toTitleCase(this.prenomfield.getText()), this.loginfield.getText(), this.mdpfield.getText(), this.telfield.getText());
    			rs = rs+ modele.AdminDAO.addAdmin(this.loginfield.getText());
    			if (rs == 2) {
    				((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    				Alert alert = new Alert(AlertType.INFORMATION);
            		alert.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
            		alert.setHeaderText("Compte admin ajouté");
            		alert.showAndWait();
    			} else {
    				Alert alert = new Alert(AlertType.ERROR);
            		alert.setTitle("LABORATOIRE GALAXY SWISS BOURDIN");
            		alert.setHeaderText("Echoué");
            		alert.showAndWait();
    			}
    		}
    	}
    }

    @FXML
    public void annuler(ActionEvent event) {
		((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
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

}

