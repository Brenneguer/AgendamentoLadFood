package agendamento.gui;

import agendamento.Usuario;
import agendamento.dao.UsuarioDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GuiControllerCadastroUsuario {
	@FXML
	private Label title;
	@FXML
	private VBox labels;
	@FXML
	private VBox textFilds;

	@FXML
	private TextField nome;
	@FXML
	private TextField sobrenome;
	@FXML
	private TextField cpf;
	@FXML
	private TextField mail;
	@FXML
	private TextField senha;

	@FXML
	private Button save;
	@FXML
	private Button cancel;
	@FXML
	private Button inicio;
	@FXML
	private Button noticeBack;
	@FXML
	private Pane notice;

	public void selectInicio(ActionEvent e) {
		if (GuiLogin.logado == true)
			new GuiHome().chamarTelaHome(e);
		else
			GuiHome.chamarTelaLogin(e);
		;
	}

	@FXML
	public void salvarButton(ActionEvent event) {
		try {
			Usuario u = new Usuario();
			u.setNome(nome.getText());
			u.setMail(mail.getText());
			u.setSenha(senha.getText());
			u.setSobrenome(sobrenome.getText());
			u.setCpf(cpf.getText());
			if (new UsuarioDao().salvar(u) == true) {
				nome.clear();
				mail.clear();
				cpf.clear();
				senha.clear();
				sobrenome.clear();
			}
		} catch (RuntimeException e) {
			labels.setVisible(false);
			textFilds.setVisible(false);
			notice.setVisible(true);
		}
	}

	@FXML
	public void selectNoticeBack(ActionEvent e) {
		notice.setVisible(false);
		labels.setVisible(true);
		textFilds.setVisible(true);
	}

	public void cancelarButton(ActionEvent e) {
		labels.setVisible(false);
		textFilds.setVisible(false);
	}

}