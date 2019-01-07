package agendamento;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Executor extends Application {

	public void start(Stage stage) {
		try {
			Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/agendamento/gui/GuiHome.fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("LAD CAD");
			stage.setMaxWidth(1280D);
			stage.setMaxHeight(720D);
			stage.show();
		} catch (IOException e) {
			System.err.println("deu ruim");
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		launch(args);
	}

}
