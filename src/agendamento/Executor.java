package agendamento;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Executor extends Application {

	public void start(Stage stage) {
		try {
			Screen screen = Screen.getPrimary();
			Rectangle2D bound = screen.getVisualBounds();
			Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/agendamento/gui/GuiLogin.fxml"));
			Scene scene = new Scene(parent);
			stage.setX(bound.getMinX());
			stage.setY(bound.getMinY());
			stage.setWidth(bound.getWidth());
			stage.setHeight(bound.getHeight());
			stage.setScene(scene);
			stage.setTitle("LAD CAD");
			stage.setMaximized(true);
			stage.show();
<<<<<<< HEAD
=======
			stage.setMaximized(true);
>>>>>>> 85b8557aad7dfed9b3fdc3f8332cab0b5eda9f13
		} catch (IOException e) {
			System.err.println("deu ruim");
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		launch(args);
	}

}
