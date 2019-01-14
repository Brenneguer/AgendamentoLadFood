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
	List<VisitaTecnica> listaVisita = null;
	static int cont = 0;
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
		String envio = "/agendamento/gui/fxml/GuiHome.fxml";
		(new CriarView()).criarTela(stage, envio);
	}

	@FXML
	public void selectConsulta(ActionEvent action) {
		try {
			List<VisitaTecnica> v = pesquisar(dataInicio, dataFim, numeroChamado, tecnico);
			agruparPor(action);
			if (adicionarArrayList(v) == true) {
				tabela.setVisible(true);
				editar.setVisible(true);
			} else
				throw new RuntimeException();
		} catch (RuntimeException e) {
			Alert alert = new Alert(AlertType.INFORMATION, "Por favor, insira informações para consulta.");
			alert.setTitle("Lista vazia");
			alert.setHeaderText("Não consiguimos localizar sua consulta.");
			alert.show();
			tabela.setVisible(false);
			editar.setVisible(false);
			e.getMessage();
		}
	}

	/**
	 * ativa a edição dos recursos
	 * 
	 * @param action
	 */
	@FXML
	public void selectEditar(ActionEvent action) {
		if (setarValoresTextView() == true) {
			telaEditarVisita.setVisible(true);
			telaConsultaVisita.setVisible(false);
		}
		else {
			telaConsultaVisita.setVisible(true);
		}
			
	}

	@FXML
	public void selectNoticeBack(ActionEvent e) {
		notice.setVisible(false);
		labels.setVisible(true);
		textFilds.setVisible(true);
	}

	/**
	 * cancela a edição
	 * 
	 * @param ActionEvent action
	 */
	public void cancelarButton(ActionEvent e) {
		telaConsultaVisita.setVisible(true);
		telaEditarVisita.setVisible(false);
	}

	/**
	 * faz as pesquisas no banco de dados de acordo com os parametros passados
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @param numeroChamado
	 * @param Tecnico
	 * @return
	 */
	private List<VisitaTecnica> pesquisar(TextField dataInicio, TextField dataFim, TextField numeroChamado,
			TextField Tecnico) {

		ConsultaDao cd = new ConsultaDao();
		listaVisita = null;

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

			listaVisita = cd.consultaPorChamado(temp, Integer.parseInt(numeroChamado.getText()));
			return listaVisita;

		} else if (!numeroChamado.getText().equals("")) {

			listaVisita = cd.consultaPorChamado(Integer.parseInt(numeroChamado.getText()));
			return listaVisita;

		} else if (!dataFim.getText().equals("") && !dataInicio.getText().equals("")) {

			listaVisita = cd.consultaPorData(
					LocalDate.parse(dataInicio.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
					LocalDate.parse(dataFim.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			return listaVisita;

		} else if (!tecnico.getText().equals("") && !dataInicio.getText().equals("") && !dataFim.getText().equals("")) {

			listaVisita = cd.consultaTecnico(
					cd.consultaPorData(LocalDate.parse(dataInicio.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
							LocalDate.parse(dataFim.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
					tecnico.getText());

			return listaVisita;

		} else if (!tecnico.getText().equals("") && dataInicio.getText().equals("") && dataFim.getText().equals("")) {

			listaVisita = cd.consultaTecnico(tecnico.getText());
			return listaVisita;
		}
		return listaVisita;
	}

	/**
	 * adiciona as informções feitas pela consulta na table view
	 * 
	 * @param lista
	 */
	private boolean adicionarArrayList(List<VisitaTecnica> lista) {
		boolean bool = false;
		if (!observableVisita.isEmpty()) {
			observableVisita.clear();
		}
		try {
			if (!lista.isEmpty()) {
				for (VisitaTecnica v : lista) {
					observableVisita.add(v);

					colunaTecnico.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, String>("tecnico"));
					colunaNumeroChamado
							.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, Integer>("numeroChamado"));
					colunaEmpresa.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, Integer>("idEmpresa"));
					colunaCobrada.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, Boolean>("lad"));
					colunaDataInicio
							.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, LocalDate>("dataInicio"));
					colunaDataFim.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, LocalDate>("dataFim"));
					colunaSituacao.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, String>("situacao"));
					tabela.setItems(observableVisita);

				}
				bool = true;
			} else
				bool = false;

		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		return bool;
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

	/**
	 * Adiciona os valores ao text field que serão editados pelo usuario
	 */
	private boolean setarValoresTextView() {
		boolean bool = false;
		try {
			numeroChamadoEditar.setText("" + tabela.getSelectionModel().getSelectedItem().getNumeroChamado());
			tecnicoEditar.setText(tabela.getSelectionModel().getSelectedItem().getTecnico());
			dataInicioEditar.setText(tabela.getSelectionModel().getSelectedItem().getDataInicio().format(format));
			dataFimEditar.setText(tabela.getSelectionModel().getSelectedItem().getDataFim().format(format));
			tarefaPaiEditar.setText("" + tabela.getSelectionModel().getSelectedItem().getIdEmpresa());
			situacaoEditar.setText(tabela.getSelectionModel().getSelectedItem().getSituacao());
			isCobradaEditar.setSelected(tabela.getSelectionModel().getSelectedItem().getLad());
			idVisita = tabela.getSelectionModel().getSelectedItem().getIdVisitaTecnica();
			
			bool = true;
		} catch (NullPointerException e) {
			Alert alert = new Alert(AlertType.INFORMATION, "Para editar, você precisa selecionar uma linha da tabela.");
			alert.setTitle("ERRO.");
			alert.setHeaderText("Falha ao editar. Tente novamente!");
			alert.show();
		}
		return bool;
	}

	/**
	 * salva as edições feitas na visita
	 * 
	 * @param event
	 */
	@FXML
	public void salvarButton(ActionEvent event) {

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
			if (new VisitaTecnicaDao().update(v)) {
				numeroChamadoEditar.clear();
				tecnicoEditar.clear();
				dataInicioEditar.clear();
				dataFimEditar.clear();
				tarefaPaiEditar.clear();
				situacaoEditar.clear();
				isCobradaEditar.setSelected(false);
				telaConsultaVisita.setVisible(true);
				telaEditarVisita.setVisible(false);
				if (!observableVisita.isEmpty())
					observableVisita.clear();
			}
		} catch (RuntimeException e) {
			isCobradaEditar.setSelected(false);
			labels.setVisible(false);
			textFilds.setVisible(false);
			notice.setVisible(true);
		}
	}

//	@FXML
//	public void exportar(ActionEvent action) {
//		System.out.println("exportando");
//		String caminho = "C:\\agendamentos\\visitaTecnica.csv";
////		Path path =  
//		try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho, true))) {
//			for (VisitaTecnica v : listaVisita) {
//				if (cont == 0) {
//					System.out.println("ta dizendo que não existe");
//					bw.write("tecnico; numero chamado; empresa; data inicio; data fim; situaca; cobrada");
//					bw.newLine();
//					bw.write(v.toString());
//					bw.newLine();
//					cont++;
//				} else {
//					bw.write(v.toString());
//					bw.newLine();
//				}
//
//			}
//		} catch (IOException io) {
//			io.getMessage();
//		}
//	}

}
