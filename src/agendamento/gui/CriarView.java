package agendamento.gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CriarView {

	public void criarTela(Stage stage, String Loader) {

		try {
			Parent parent = (Parent) FXMLLoader.load(getClass().getResource(Loader));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("Lad App");
			stage.setMaximized(true);
			stage.show();
		} catch (IOException e) {
			System.err.println("deu ruim");
			e.printStackTrace();
		}

	}
}