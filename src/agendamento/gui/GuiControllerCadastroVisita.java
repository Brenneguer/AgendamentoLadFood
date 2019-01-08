package agendamento.gui;

import agendamento.VisitaTecnica;
import agendamento.dao.VisitaTecnicaDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GuiControllerCadastroVisita {
	@FXML
	private Label title;
	@FXML
	private VBox labels;
	@FXML
	private VBox textFilds;
	
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
		Node n = (Node) e.getSource();
		Stage stage = (Stage) n.getScene().getWindow();
		String envio = "/agendamento/gui/GuiHome.fxml";
		(new CriarView()).criarTela(stage, envio);
	}

	@FXML
	public void salvarButton(ActionEvent event) {
		try {
			VisitaTecnica v = new VisitaTecnica();
			v.setNumeroChamado(Integer.parseInt(numeroChamado.getText()));
			v.setTecnico(tecnico.getText());
			v.setDataInicio(dataInicio.getText());
			v.setDataFim(dataFim.getText());
			v.setIdEmpresa(Integer.parseInt(tarefaPai.getText()));
			v.setSituacao(situacao.getText());
			v.setLad(isCobrada.isSelected());
			if (new VisitaTecnicaDao().salvar(v) == true) {
				numeroChamado.clear();
				tecnico.clear();
				dataInicio.clear();
				dataFim.clear();
				tarefaPai.clear();
				situacao.clear();
				isCobrada.setSelected(false);
			}
		} catch (RuntimeException e) {
			System.out.println("entrei no cath");
			isCobrada.setSelected(false);
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
		System.out.println("notice back");
	}

	public void cancelarButton(ActionEvent e) {
		labels.setVisible(false);
		textFilds.setVisible(false);
		isCobrada.setSelected(false);
	}

}