package agendamento.gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import agendamento.VisitaTecnica;
import agendamento.dao.ConsultaDao;
import agendamento.dao.VisitaTecnicaDao;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuiControllerConsultaVisita {
	DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private int idVisita = 0;
	/**
	 * parte Consulta de visitas
	 */
	@FXML
	private HBox telaEditarVisita;
	
	@FXML
	private TextField numeroChamado;
	@FXML
	private TextField tecnico;
	@FXML
	private TextField dataInicio;
	@FXML
	private TextField dataFim;
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
	private CheckBox agruparDataInicio;
	@FXML
	private CheckBox agruparDataFim;
	@FXML
	private CheckBox agruparEmpresa;
	@FXML
	private CheckBox agruparSituacao;
	@FXML
	private CheckBox agruparTecnico;
	@FXML
	private CheckBox agruparCobrada;
	@FXML
	private Button inicio;
	@FXML
	private Button editar;

	/**
	 * parte editar visita
	 */
	@FXML
	private HBox telaConsultaVisita;
	@FXML
	private VBox labels;
	@FXML
	private VBox textFilds;

	@FXML
	private TextField numeroChamadoEditar;
	@FXML
	private TextField tecnicoEditar;
	@FXML
	private TextField dataInicioEditar;
	@FXML
	private TextField dataFimEditar;
	@FXML
	private TextField tarefaPaiEditar;
	@FXML
	private TextField situacaoEditar;

	@FXML
	private CheckBox isCobradaEditar;
	@FXML
	private Button save;
	@FXML
	private Button cancel;
	@FXML
	private Button noticeBack;
	@FXML
	private Pane notice;

	
	@FXML
	public void selectInicio(ActionEvent action) {
		Node n = (Node) action.getSource();
		Stage stage = (Stage) n.getScene().getWindow();
		String envio = "/agendamento/gui/GuiHome.fxml";
		(new CriarView()).criarTela(stage, envio);
	}

	@FXML
	public void selectConsulta(ActionEvent action) {
		try {
			List<VisitaTecnica> v = pesquisar(dataInicio, dataFim, numeroChamado, tecnico);
			agruparPor(action);
			adicionarArrayList(v);
			tabela.setVisible(true);
			editar.setVisible(true);
		} catch (RuntimeException e) {
			Alert alert = new Alert(AlertType.INFORMATION, "Por favor, insira informações para consulta.");
			alert.setTitle("Lista vazia");
			alert.setHeaderText("Consulta Invalida.");
			e.printStackTrace();
		}
	}

	@FXML
	public void selectEditar(ActionEvent action) {
		System.out.println(tabela.getSelectionModel().getSelectedItem().getIdVisitaTecnica());
		telaConsultaVisita.setVisible(false);
		setarValoresTextView();
		telaEditarVisita.setVisible(true);
	}
	
	@FXML
	public void salvarButton(ActionEvent event) {
		System.out.println(idVisita);
		try {
			VisitaTecnica v = new VisitaTecnica();
			v.setIdVisitaTecnica(idVisita);
			v.setNumeroChamado(Integer.parseInt(numeroChamadoEditar.getText()));
			v.setTecnico(tecnicoEditar.getText());
			v.setDataInicio(dataInicioEditar.getText());
			v.setDataFim(dataFimEditar.getText());
			v.setIdEmpresa(Integer.parseInt(tarefaPaiEditar.getText()));
			v.setSituacao(situacaoEditar.getText());
			v.setLad(isCobradaEditar.isSelected());
			if (new VisitaTecnicaDao().salvar(v) == true) {
				numeroChamadoEditar.clear();
				tecnicoEditar.clear();
				dataInicioEditar.clear();
				dataFimEditar.clear();
				tarefaPaiEditar.clear();
				situacaoEditar.clear();
				isCobradaEditar.setSelected(false);
			}
		} catch (RuntimeException e) {
			System.out.println("entrei no cath");
			isCobradaEditar.setSelected(false);
			labels.setVisible(false);
			textFilds.setVisible(false);
			notice.setVisible(true);
		}
	}
	
	public void setarValoresTextView() {
		numeroChamadoEditar.setText(""+tabela.getSelectionModel().getSelectedItem().getNumeroChamado());
		tecnicoEditar.setText(tabela.getSelectionModel().getSelectedItem().getTecnico());
		dataInicioEditar.setText(tabela.getSelectionModel().getSelectedItem().getDataInicio().format(format));
		dataFimEditar.setText(tabela.getSelectionModel().getSelectedItem().getDataFim().format(format));
		tarefaPaiEditar.setText(""+tabela.getSelectionModel().getSelectedItem().getIdEmpresa());
		situacaoEditar.setText(tabela.getSelectionModel().getSelectedItem().getSituacao());
		isCobradaEditar.setSelected(tabela.getSelectionModel().getSelectedItem().getLad());
		idVisita = tabela.getSelectionModel().getSelectedItem().getNumeroChamado();
		
	}
	@FXML
	public void selectNoticeBack(ActionEvent e) {
		notice.setVisible(false);
		labels.setVisible(true);
		textFilds.setVisible(true);
		
	}
	
	public void cancelarButton(ActionEvent e) {
		telaConsultaVisita.setVisible(true);
		telaEditarVisita.setVisible(false);
		
	}
	
	public void adicionarArrayList(List<VisitaTecnica> lista) {
		if (!observableVisita.isEmpty()) {
			observableVisita.clear();
		}
		try {
			for (VisitaTecnica v : lista) {
				observableVisita.add(v);

				colunaTecnico.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, String>("tecnico"));
				colunaNumeroChamado
						.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, Integer>("numeroChamado"));
				colunaEmpresa.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, Integer>("idEmpresa"));
				colunaCobrada.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, Boolean>("lad"));
				colunaDataInicio.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, LocalDate>("dataInicio"));
				colunaDataFim.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, LocalDate>("dataFim"));
				colunaSituacao.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, String>("situacao"));
				tabela.setItems(observableVisita);
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * faz as pesquisas
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @param numeroChamado
	 * @param Tecnico
	 * @return
	 */
	public List<VisitaTecnica> pesquisar(TextField dataInicio, TextField dataFim, TextField numeroChamado,
			TextField Tecnico) {

		ConsultaDao cd = new ConsultaDao();
		List<VisitaTecnica> listaVisita = null;

		if (numeroChamado.getText().equals("") && dataInicio.getText().equals("") && dataFim.getText().equals("")
				&& Tecnico.getText().equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION,
					"Você precisa inserir informações para consulta, insira um intervalo de datas.");
			alert.setTitle("Consulta invalida");
			alert.setHeaderText("Informação util.");
			alert.show();

		} else if (!numeroChamado.getText().equals("") && !dataInicio.getText().equals("")
				&& !dataFim.getText().equals("")) {
			List<VisitaTecnica> temp = cd.consultaPorData(
					LocalDate.parse(dataInicio.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
					LocalDate.parse(dataFim.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			return cd.consultaPorChamado(temp, Integer.parseInt(numeroChamado.getText()));
		} else if (!numeroChamado.getText().equals("")) {
			return cd.consultaPorChamado(Integer.parseInt(numeroChamado.getText()));
		} else if (!dataFim.getText().equals("") && !dataInicio.getText().equals("")) {
			return cd.consultaPorData(LocalDate.parse(dataInicio.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
					LocalDate.parse(dataFim.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		} else if (!tecnico.getText().equals("") && !dataInicio.getText().equals("") && !dataFim.getText().equals("")) {
			return cd.consultaTecnico(
					cd.consultaPorData(LocalDate.parse(dataInicio.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
							LocalDate.parse(dataFim.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
					tecnico.getText());
		} else if (!tecnico.getText().equals("") && dataInicio.getText().equals("") && dataFim.getText().equals("")) {
			return cd.consultaTecnico(tecnico.getText());
		}
		return listaVisita;
	}

	/**
	 * Faz os filtros
	 */
	public void agruparPor(ActionEvent e) {

		if (observableVisita.isEmpty() == false) {
			colunaTecnico.setVisible(false);
			colunaEmpresa.setVisible(false);
			colunaCobrada.setVisible(false);
			colunaDataInicio.setVisible(false);
			colunaDataFim.setVisible(false);
			colunaSituacao.setVisible(false);
		}

		if (agruparDataInicio.isSelected()) {
			colunaDataInicio.setVisible(true);
		}
		if (agruparDataFim.isSelected()) {
			colunaDataFim.setVisible(true);
		}
		if (agruparTecnico.isSelected()) {
			colunaTecnico.setVisible(true);
		}
		if (agruparEmpresa.isSelected()) {
			colunaEmpresa.setVisible(true);
		}
		if (agruparCobrada.isSelected()) {
			colunaCobrada.setVisible(true);
		}
		if (agruparSituacao.isSelected()) {
			colunaSituacao.setVisible(true);
		}
	}
}
