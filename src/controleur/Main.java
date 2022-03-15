package controleur;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.SQLException;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../vue/login.fxml"));
		primaryStage.setTitle("Laboratoire Galaxy Swiss Bourdin");
		Scene scene = new Scene(root, 723, 437);
		String css = this.getClass().getResource("../vue/application.css").toExternalForm();
		scene.getStylesheets().add(css);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) throws SQLException {
		launch(args);
	}
}
