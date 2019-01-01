package agendamento.gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import agendamento.VisitaTecnica;
import agendamento.dao.VisitaTecnicaDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private Label notice;
	@FXML
	private ContextMenu noticeSelect;
	@FXML
	private MenuItem noticeMenuItem;

	@FXML
	public void selectInicio(ActionEvent action) {
		Node n = (Node) action.getSource();
		Stage stage = (Stage) n.getScene().getWindow();
		String envio = "/agendamento/gui/GuiHome.fxml";
		(new CriarView()).criarTela(stage, envio);
	}

	@FXML
	public void selectButtonBuscar(ActionEvent action) {
		try {
			VisitaTecnica v = new VisitaTecnicaDao().buscarPorNumeroChamado(Integer.parseInt(numeroChamado.getText()));
			System.out.println(v.getLad());
			adicionarArrayList();
			tabela.setVisible(true);
			System.out.println(v.toString());
		} catch (RuntimeException e) {
			notice.setVisible(true);
			buscar.setVisible(false);
			System.out.println("não achei");

		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		

	}
	
	public void adicionarVisitaTableView(VisitaTecnica v) {
		if(!observableVisita.isEmpty()) {
			observableVisita.clear();
			System.out.println("não era vazia");
		}
		
		observableVisita.add(v);
		
		colunaTecnico.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, String>("tecnico"));
		colunaNumeroChamado.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, Integer>("numeroChamado"));
		colunaDataInicio.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, LocalDate>("dataInicio"));
		colunaDataFim.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, LocalDate>("dataFim"));
		colunaSituacao.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, String> ("situacao"));
		colunaCobrada.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, Boolean>("lad"));
		
		tabela.setItems(observableVisita);
	}// fim adicionar
	
	/**
	 * metodo popula array de visitas
	 * enche uma tabela
	 */
	public void adicionarArrayList() {
		if(!observableVisita.isEmpty()) {
			System.out.println("cheia");
			observableVisita.clear();
		}
		VisitaTecnicaDao v = new VisitaTecnicaDao();
		
		
		for(VisitaTecnica visita : v.listarTodos()) {
			observableVisita.add(visita);
			colunaTecnico.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, String>("tecnico"));
			colunaNumeroChamado.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, Integer>("numeroChamado"));
			colunaDataInicio.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, LocalDate>("dataInicio"));
			colunaDataFim.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, LocalDate>("dataFim"));
			colunaSituacao.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, String> ("situacao"));
			colunaCobrada.setCellValueFactory(new PropertyValueFactory<VisitaTecnica, Boolean>("lad"));
			
			tabela.setItems(observableVisita);
			
		}
	}
	
}
