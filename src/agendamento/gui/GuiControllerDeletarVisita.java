package agendamento.gui;

import java.net.URL;
import java.util.ResourceBundle;

import agendamento.VisitaTecnica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

public class GuiControllerDeletarVisita implements Initializable {
	@FXML
	private TitledPane principal;
	@FXML
	private TextField numeroChamado;
	@FXML
	private Button inicio;
	@FXML
	private Button buscar;
	@FXML
	private Button confirmarDelete;
	@FXML
	private TableView<VisitaTecnica> tabela;
	@FXML
	private TableColumn<VisitaTecnica, String> colunaTecnico;
	@FXML
	private TableColumn<VisitaTecnica, Integer> colunaNumeroChamado;
	@FXML
	private TableColumn<VisitaTecnica, String> colunaDataInicio;
	@FXML
	private TableColumn<VisitaTecnica, String> colunaDataFim;
	@FXML
	private TableColumn<VisitaTecnica, String> colunaSituacao;
	@FXML
	private TableColumn<VisitaTecnica, Boolean> colunaCobrada;

	public void selectInicio(ActionEvent action) {
		Node n = (Node) action.getSource();
		Stage stage = (Stage) n.getScene().getWindow();
		String envio = "/agendamento/gui/GuiHome.fxml";
		(new CriarView()).criarTela(stage, envio);
	}
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
