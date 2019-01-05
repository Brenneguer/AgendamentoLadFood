package agendamento.gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import agendamento.VisitaTecnica;
import agendamento.dao.ConsultaDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private CheckBox isCobrada;
	@FXML
	private Button consulta;

	@FXML
	private TableView<VisitaTecnica> tabela;
	@FXML
	private TableColumn<VisitaTecnica, String> colunaTecnico;
	@FXML
	private TableColumn<VisitaTecnica, Integer> colunaNumeroChamado;
	@FXML
	private TableColumn<VisitaTecnica, Integer> colunaEmpresa;
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

	@FXML
	public void selectConsulta(ActionEvent action) {

		System.out.println(pesquisar(dataInicio, dataFim, numeroChamado, tecnico, isCobrada).toString());
		tabela.setVisible(true);
	}

	public void adicionarArrayList(List<VisitaTecnica> lista) {
		if (!observableVisita.isEmpty()) {
			observableVisita.clear();
			System.out.println("não era vazia");
		}
		for (VisitaTecnica v : lista) {
			observableVisita.add(v);

			colunaTecnico.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, String>("tecnico"));
			colunaNumeroChamado.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, Integer>("numeroChamado"));
			colunaEmpresa.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, Integer>("idEmpresa"));
			colunaCobrada.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, Boolean>("lad"));
			colunaDataInicio.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, LocalDate>("dataInicio"));
			colunaDataFim.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, LocalDate>("dataFim"));
			colunaSituacao.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, String>("situacao"));
			tabela.setItems(observableVisita);
		}
	}

	public List<VisitaTecnica> pesquisar(TextField dataInicio, TextField dataFim, TextField numeroChamado, TextField Tecnico,
			CheckBox caixaSelect) {
		ConsultaDao cd = new ConsultaDao();
		List<VisitaTecnica> listaVisita = null;
		if (numeroChamado.getText().equals("") && dataInicio.getText().equals("") && dataFim.getText().equals("")
				&& Tecnico.getText().equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION,
					"Você precisa inserir informações para consulta, insira um intervalo de datas.");
			alert.setTitle("Consulta invalida");
			alert.setHeaderText("Informação util.");
			alert.show();
		}
		
		if (!numeroChamado.getText().equals("") && !dataInicio.getText().equals("") && !dataFim.getText().equals("")) {
			//return//
			List<VisitaTecnica> temp = cd.consultaPorData(LocalDate.parse(dataInicio.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
					LocalDate.parse(dataFim.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			System.out.printf("Consultando por temp %s",temp.toString());
			return cd.consultaPorChamado(temp, Integer.parseInt(numeroChamado.getText()));
		} else if (!numeroChamado.getText().equals("")) {
			return cd.consultaPorChamado(Integer.parseInt(numeroChamado.getText()));
		}
		
		return listaVisita;
	}

}
