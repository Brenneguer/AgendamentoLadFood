package agendamento.gui;

import java.time.LocalDate;

import agendamento.VisitaTecnica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

public class GuiControllerConsultaVisita {
	@FXML
	private TitledPane background;
	@FXML
	private TextField numeroChamado;
	@FXML
	private TextField tecnico;
	@FXML
	private TextField dataInicio;
	@FXML
	private TextField dataFim;
	@FXML
	private TextField tarefaPai;
	@FXML
	private TextField situacao;
	@FXML
	private CheckBox isCobrada;
	
	@FXML
	private TableView<VisitaTecnica> tabela;
	@FXML
	private TableColumn<VisitaTecnica, String> colunaTecnico;
	@FXML
	private TableColumn<VisitaTecnica, Integer> colunaNumeroChamado;
	@FXML
	private TableColumn<VisitaTecnica, LocalDate> colunaDataInicio;
	@FXML
	private TableColumn<VisitaTecnica, LocalDate> colunaDataFim;
	@FXML
	private TableColumn<VisitaTecnica, String> colunaSituacao;
	@FXML
	private TableColumn<VisitaTecnica, Boolean> colunaCobrada;
	@FXML
	private ObservableList<VisitaTecnica> observableVisita = FXCollections.observableArrayList();
	
	@FXML
	private Button inicio;
	
	@FXML
	public void selectInicio(ActionEvent action) {
		Node n = (Node) action.getSource();
		Stage stage = (Stage) n.getScene().getWindow();
		String envio = "/agendamento/gui/GuiHome.fxml";
		(new CriarView()).criarTela(stage, envio);
	}
	
}
