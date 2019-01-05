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
	private Button cadastrarVisitaBotao;
	@FXML
	private Button consultarVisitaBotao;
	@FXML
	private Button deletarVisitaBotao;
	String envia;


	public void chamarTelaHome(ActionEvent e) {
		node = (Node) e.getSource();
		stage = (Stage) node.getScene().getWindow();
		envia = "/agendamento/gui/GuiHome.fxml";
		(new CriarView()).criarTela(stage, envia);
		envia = "";
	}
	
	public void chamarTelaConsultaVisita(ActionEvent e) {
		node = (Node) e.getSource();
		stage = (Stage) node.getScene().getWindow();
		envia = "/agendamento/gui/GuiConsultaVisitaHome.fxml";
		(new CriarView()).criarTela(stage, envia);
	}
	@FXML
	public void chamarTelacadastrarVisita(ActionEvent e) {
		node = (Node) e.getSource();
		stage = (Stage) node.getScene().getWindow();
		envia = "/agendamento/gui/GuiCadastroVisitaTecnica.fxml";
		(new CriarView()).criarTela(stage, envia);
		envia = "";
	}



	public void chamarTelaDeletarVisita(ActionEvent e) {
		node = (Node) e.getSource();
		stage = (Stage) node.getScene().getWindow();
		envia = "/agendamento/gui/GuiDeletarVisita.fxml";
		(new CriarView()).criarTela(stage, envia);
		envia = "";
	}
}