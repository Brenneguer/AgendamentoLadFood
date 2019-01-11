package agendamento.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

// Referenced classes of package agendamento.gui:
//            CriarView

public class GuiHome {
	@FXML
	Node node = null;
	@FXML
	Parent root = null;
	@FXML
	Scene cena = null;
	@FXML
	Stage stage = null;
	@FXML
	private Button cadastrarVisitaBotao, cadastrarFuncionarioBotao;
	@FXML
	private Button consultarVisitaBotao;
	@FXML
	private Button deletarVisitaBotao;
	String envia;


	public void chamarTelaHome(ActionEvent e) {
		node = (Node) e.getSource();
		stage = (Stage) node.getScene().getWindow();
		envia = "/agendamento/gui/GuiHome.fxml";
		System.out.println(GuiLogin.logado);
		if (GuiLogin.logado == true) {
			(new CriarView()).criarTela(stage, envia);
			envia = "";
		} else {
			chamarTelaLogin(e);
		}
	}
	
	public void chamarTelaConsultaVisita(ActionEvent e) {
		node = (Node) e.getSource();
		stage = (Stage) node.getScene().getWindow();
		envia = "/agendamento/gui/GuiConsultaVisitaHome.fxml";
		if (GuiLogin.logado == true) {
			(new CriarView()).criarTela(stage, envia);
			envia = "";
		} else {
			chamarTelaLogin(e);
		}
	}
	@FXML
	public void chamarTelacadastrarVisita(ActionEvent e) {
		node = (Node) e.getSource();
		stage = (Stage) node.getScene().getWindow();
		envia = "/agendamento/gui/GuiCadastroVisitaTecnica.fxml";
		if (GuiLogin.logado == true) {
			(new CriarView()).criarTela(stage, envia);
			envia = "";
		} else {
			chamarTelaLogin(e);
		}
	}
	
	@FXML
	public void chamarTelaCadastrarFuncionario(ActionEvent e) {
		node = (Node) e.getSource();
		stage = (Stage) node.getScene().getWindow();
		envia = "/agendamento/gui/GuiCadastroUsuario.fxml";
		(new CriarView()).criarTela(stage, envia);
		envia = "";
	}

	public void chamarTelaDeletarVisita(ActionEvent e) {
		node = (Node) e.getSource();
		stage = (Stage) node.getScene().getWindow();
		envia = "/agendamento/gui/GuiDeletarVisita.fxml";
		if (GuiLogin.logado == true) {
			(new CriarView()).criarTela(stage, envia);
			envia = "";
		} else {
			chamarTelaLogin(e);
		}
	}
	
	public static void chamarTelaLogin(ActionEvent e) {
		Node node = (Node) e.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		String envia = "/agendamento/gui/GuiLogin.fxml";
		(new CriarView()).criarTela(stage, envia);
		envia = "";		
	}
}