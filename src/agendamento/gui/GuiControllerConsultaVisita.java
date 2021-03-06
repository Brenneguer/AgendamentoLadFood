package agendamento.gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import agendamento.VisitaTecnica;
import agendamento.dao.ConsultaDao;
import agendamento.dao.Exportar;
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
import javafx.scene.control.Label;
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
	private CheckBox isImplantacao, isVisita;

	@FXML
	private TableView<VisitaTecnica> tabela;
	@FXML
	private TableColumn<VisitaTecnica, String> colunaTecnico;
	@FXML
	private TableColumn<VisitaTecnica, String> colunaTipo;
	@FXML
	private TableColumn<VisitaTecnica, Integer> colunaNumeroChamado;
	@FXML
	private TableColumn<VisitaTecnica, String> colunaEmpresa;
	@FXML
	private TableColumn<VisitaTecnica, LocalDate> colunaDataInicio;
	@FXML
	private TableColumn<VisitaTecnica, LocalDate> colunaDataFim;
	@FXML
	private TableColumn<VisitaTecnica, String> colunaSituacao;
	@FXML
	private TableColumn<VisitaTecnica, Boolean> colunaCobrada;
	@FXML
	private TableColumn<VisitaTecnica, String> colunaObs;
	@FXML
	private TableColumn<VisitaTecnica, Double> colunaValor;
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
	private CheckBox agruparTipo;
	@FXML
	private CheckBox agruparCobrada;
	@FXML
	private CheckBox agruparValor;
	@FXML
	private CheckBox agruparObs;
	
	@FXML
	private Button inicio;
	@FXML
	private Button editar;
	@FXML
	private Button exportar;

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
	private TextField observacaoEditar;
	@FXML
	private TextField valorEditar;
	@FXML
	private Label labelValorEditar;

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
	public void selectionTipo() {
		if (isVisita.isSelected() == true && isImplantacao.isSelected() == false) {
			isImplantacao.setVisible(false);
		} else if (isImplantacao.isSelected() == true && isVisita.isSelected() == false) {
			isVisita.setVisible(false);
		}

		if (isVisita.isSelected() == false) {
			isImplantacao.setVisible(true);
		}
		if (isImplantacao.isSelected() == false) {
			isVisita.setVisible(true);
		}
	}
	
	@FXML
	public void selectionCobrada() {
		if(isCobradaEditar.isSelected() == true) {
			labelValorEditar.setVisible(true);
			valorEditar.setVisible(true);
		}
		else {
			labelValorEditar.setVisible(false);
			valorEditar.setVisible(false);
		}
	}

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
			List<VisitaTecnica> v = pesquisar(dataInicio, dataFim, numeroChamado, tecnico, isImplantacao, isVisita);
			agruparPor(action);
			if (v == null) {
				throw new RuntimeException();
			}
			if (adicionarArrayList(v) == true) {
				
				tabela.setVisible(true);
				editar.setVisible(true);
			} else
				throw new RuntimeException();
		} catch (RuntimeException e) {
			Alert alert = new Alert(AlertType.INFORMATION, "Por favor, insira informa��es para consulta.");
			alert.setTitle("Lista vazia");
			alert.setHeaderText("N�o consiguimos localizar sua consulta.");
			alert.show();
			tabela.setVisible(false);
			editar.setVisible(false);
			e.getMessage();
		}
	}

	/**
	 * ativa a edi��o dos recursos
	 * 
	 * @param action
	 */
	@FXML
	public void selectEditar(ActionEvent action) {
		if (setarValoresTextView() == true) {
			telaEditarVisita.setVisible(true);
			telaConsultaVisita.setVisible(false);
		} else {
			telaConsultaVisita.setVisible(true);
		}

	}

	@FXML
	public void selectExportar() {
		if (listaVisita == null) {
			Alert alert = new Alert(AlertType.INFORMATION, "A tabela n�o pode estar vazia");
			alert.setTitle("Lista Vazia.");
			alert.setHeaderText("Algo inesperado ocorreu, tente novamente.");
			alert.show();
		}
		else {
			Exportar ex = new Exportar();
			ex.exportarExcel("C:/Agendamentos/lad food/lista visita.xlsx", listaVisita);
		}
		
	}
	@FXML
	public void selectNoticeBack(ActionEvent e) {
		notice.setVisible(false);
		labels.setVisible(true);
		textFilds.setVisible(true);
	}

	/**
	 * cancela a edi��o
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
	public List<VisitaTecnica> pesquisar(TextField dataInicio, TextField dataFim, TextField numeroChamado,
			TextField Tecnico, CheckBox implantacao, CheckBox visita) {

		ConsultaDao cd = new ConsultaDao();
		listaVisita = null;

		if (numeroChamado.getText().equals("") && dataInicio.getText().equals("") && dataFim.getText().equals("")
				&& Tecnico.getText().equals("")) {
			listaVisita = null;
			return listaVisita;

		} else if (!numeroChamado.getText().equals("") && !dataInicio.getText().equals("")
				&& !dataFim.getText().equals("")) {
			// consulta data fim, data inicio, numero chamado;
			List<VisitaTecnica> temp = cd.consultaPorData(
					LocalDate.parse(dataInicio.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
					LocalDate.parse(dataFim.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			listaVisita = cd.consultaPorChamado(temp, Integer.parseInt(numeroChamado.getText()));
			if (isImplantacao.isSelected() == true) 
				return listaVisita = cd.consultaTipo(listaVisita, "Implanta��o");
			else if (isVisita.isSelected() == true) 
				return listaVisita = cd.consultaTipo(listaVisita, "Visita T�cnica");
			else
				return listaVisita;

		} else if (!tecnico.getText().equals("") && !dataInicio.getText().equals("") && !dataFim.getText().equals("")) {
			// consulta data inicio, data fim e tecnico
			listaVisita = cd.consultaTecnico(
					cd.consultaPorData(LocalDate.parse(dataInicio.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
							LocalDate.parse(dataFim.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
					tecnico.getText());
			if (isImplantacao.isSelected() == true) {
				listaVisita = cd.consultaTipo(listaVisita, "Implanta��o");
				return listaVisita;
			} else if (isVisita.isSelected() == true) {
				listaVisita = cd.consultaTipo(listaVisita, "Visita T�cnica");
				return listaVisita;
			} else
				return listaVisita;

		} else if (!tecnico.getText().equals("") && dataInicio.getText().equals("") && dataFim.getText().equals("")) {
			// consulta Tecnico;
			if (isImplantacao.isSelected() == true) {
				listaVisita = cd.consultaTipo(cd.consultaTecnico(tecnico.getText()), "Implanta��o");
				return listaVisita;
			} else if (isVisita.isSelected() == true) {
				listaVisita = cd.consultaTipo(cd.consultaTecnico(tecnico.getText()), "Visita T�cnica");
				return listaVisita;
			} else
				listaVisita = cd.consultaTecnico(tecnico.getText());
			return listaVisita;
		} else if (!dataFim.getText().equals("") && !dataInicio.getText().equals("")) {
			// consulta data fim e data inicio
			listaVisita = cd.consultaPorData(
					LocalDate.parse(dataInicio.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
					LocalDate.parse(dataFim.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			if (isImplantacao.isSelected() == true)
				return listaVisita = cd.consultaTipo(listaVisita, "Implanta��o");
		
			else if (isVisita.isSelected() == true) 
				return listaVisita = cd.consultaTipo(listaVisita, "Visita T�cnica");
		
			else 
				return listaVisita;

		} else if (!numeroChamado.getText().equals("")) {
			// consulta numero chamado
			if (isImplantacao.isSelected() == true);
			listaVisita = cd.consultaPorChamado(Integer.parseInt(numeroChamado.getText()));

			return listaVisita;

		}
		return listaVisita;
	}

	/**
	 * adiciona as inform��es feitas pela consulta na table view
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
					colunaTipo.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, String>("tipo"));
					colunaEmpresa.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, String>("empresa"));
					colunaCobrada.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, Boolean>("lad"));
					colunaDataInicio
							.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, LocalDate>("dataInicio"));
					colunaDataFim.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, LocalDate>("dataFim"));
					colunaSituacao.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, String>("situacao"));
					colunaObs.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, String>("obs"));
					colunaValor.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, Double>("valor"));
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
			colunaTipo.setVisible(false);
			colunaEmpresa.setVisible(false);
			colunaCobrada.setVisible(false);
			colunaDataInicio.setVisible(false);
			colunaDataFim.setVisible(false);
			colunaSituacao.setVisible(false);
			colunaObs.setVisible(false);
		}

		if (agruparDataInicio.isSelected()) {
			colunaDataInicio.setVisible(true);
		}
		if (agruparTipo.isSelected()) {
			colunaTipo.setVisible(true);
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
		if (agruparObs.isSelected()) {
			colunaObs.setVisible(true);
		}
		if (agruparValor.isSelected()) {
			colunaValor.setVisible(true);
		}
	}

	/**
	 * Adiciona os valores ao text field que ser�o editados pelo usuario
	 */
	private boolean setarValoresTextView() {
		boolean bool = false;
		try {
			numeroChamadoEditar.setText("" + tabela.getSelectionModel().getSelectedItem().getNumeroChamado());
			tecnicoEditar.setText(tabela.getSelectionModel().getSelectedItem().getTecnico());
			dataInicioEditar.setText(tabela.getSelectionModel().getSelectedItem().getDataInicio().format(format));
			dataFimEditar.setText(tabela.getSelectionModel().getSelectedItem().getDataFim().format(format));
			tarefaPaiEditar.setText(tabela.getSelectionModel().getSelectedItem().getEmpresa());
			situacaoEditar.setText(tabela.getSelectionModel().getSelectedItem().getSituacao());
			isCobradaEditar.setSelected(tabela.getSelectionModel().getSelectedItem().getLad());
			idVisita = tabela.getSelectionModel().getSelectedItem().getIdVisitaTecnica();
			observacaoEditar.setText(tabela.getSelectionModel().getSelectedItem().getObs());
			valorEditar.setText("" + tabela.getSelectionModel().getSelectedItem().getValor());

			bool = true;
		} catch (NullPointerException e) {
			Alert alert = new Alert(AlertType.INFORMATION, "Para editar, voc� precisa selecionar uma linha da tabela.");
			alert.setTitle("ERRO.");
			alert.setHeaderText("Falha ao editar. Tente novamente!");
			alert.show();
		}
		return bool;
	}

	/**
	 * salva as edi��es feitas na visita
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
			v.setEmpresa(tarefaPaiEditar.getText());
			v.setSituacao(situacaoEditar.getText());
			v.setLad(isCobradaEditar.isSelected());
			v.setObs(observacaoEditar.getText());
			v.setValor(Double.parseDouble(valorEditar.getText()));
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


}
