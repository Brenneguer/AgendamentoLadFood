package agendamento.gui;

import agendamento.VisitaTecnica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

// Referenced classes of package agendamento.gui:
//            CriarView, GuiHome

public class GuiControllerCadastroVisita {
    @FXML
    private AnchorPane cadastroVisita;
    @FXML
    private Label title;
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


	public void selectInicio(ActionEvent e) {
		Node n = (Node) e.getSource();
		Stage stage = (Stage) n.getScene().getWindow();
		String envio = "/agendamento/gui/GuiHome.fxml";
		(new CriarView()).criarTela(stage, envio);
	}

	@FXML
	public void salvarButton(ActionEvent e) {
		VisitaTecnica v = new VisitaTecnica();
		v.setNumeroChamado(Integer.parseInt(numeroChamado.getText()));
		v.setTecnico(tecnico.getText());
		v.setDataInicio(dataInicio.getText());
		v.setDataFim(dataFim.getText());
		v.setIdEmpresa(Integer.parseInt(tarefaPai.getText()));
		v.setSituacao(situacao.getText());
		v.setLad(isCobrada.isSelected());
		System.out.println(v.toString());
		System.out.println(v.isLad());
		cancelarButton(e);
	}

	public void cancelarButton(ActionEvent e) {
		numeroChamado.clear();
		tecnico.clear();
		dataInicio.clear();
		dataFim.clear();
		tarefaPai.clear();
		situacao.clear();
		isCobrada.setSelected(false);
		new GuiHome().chamarTelaHome(e);
	}

}