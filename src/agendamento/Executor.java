package agendamento;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Executor extends Application {

	public void start(Stage stage) {
		try {
			Screen screen = Screen.getPrimary();
			Rectangle2D bound = screen.getVisualBounds();
			Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/agendamento/gui/fxml/GuiLogin.fxml"));
			Image icon = new Image("/agendamento/gui/icons/icon.png");
			stage.getIcons().add(icon);
			Scene scene = new Scene(parent);
			stage.setX(bound.getMinX());
			stage.setY(bound.getMinY());
			stage.setWidth(bound.getWidth());
			stage.setHeight(bound.getHeight());
			stage.setScene(scene);
			stage.setTitle("LAD CAD");
			stage.setMaximized(true);
			stage.show();
			stage.setMaximized(true);

		} catch (IOException e) {
			e.getMessage();
			System.err.println("deu ruim");
			e.printStackTrace();
		}
	}


	public static void main(String args[]) {
		launch(args);
	}

}
