package agendamento.gui;



import agendamento.dao.UsuarioDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class GuiLogin {
	public static boolean logado = false;
	@FXML
	private TextField email;
	@FXML
	private PasswordField senha;
	@FXML
	private VBox labels;
	@FXML
	private VBox textFields;
	@FXML
	private Hyperlink esqueciSenha;
	@FXML
	private Button login, newUser;
	
	@FXML
	public void selectLogin(ActionEvent action) {

		UsuarioDao ud = new UsuarioDao();
		if (ud.buscarUsuario(email.getText(), senha.getText()) == true) {
			logado = true;
			new GuiHome().chamarTelaHome(action);

		} else {
			Alert alert = new Alert(AlertType.INFORMATION, "Usuario ou senha invalidos.");
			alert.setTitle("Ooops");
			alert.setHeaderText("Usuario invalido.");
			alert.show();
		}
	}
	
	@FXML
	public void selectNewUser(ActionEvent action) {
		new GuiHome().chamarTelaCadastrarFuncionario(action);
	}
}
