package agendamento.gui;



import agendamento.dao.UsuarioDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class GuiLogin {
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
	private Button login;
	
	@FXML
	public void selectLogin(ActionEvent action) {
		UsuarioDao ud = new UsuarioDao();
		
		if (ud.buscarUsuario(email.getText(), senha.getText()) == true) {
			new GuiHome().chamarTelaHome(action);
		};
		
	}
}
