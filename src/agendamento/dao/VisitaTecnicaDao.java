package agendamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import agendamento.VisitaTecnica;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class VisitaTecnicaDao implements IDao<VisitaTecnica> {
	private Connection conn;
	private PreparedStatement query;
	private ResultSet rs;
	private String sql;
	private Alert alert;
	public boolean salvar(VisitaTecnica obj) {
		VisitaTecnica v = buscarPorNumeroChamado(obj.getNumeroChamado());
		conn = Connector.abrirConexao();
		sql = "INSERT INTO visita_tecnica (numero_chamado, data_inicio, data_fim, id_empresa, tecnico, is_lad, situacao) VALUES (?,?,?,?,?,?,?)";
		boolean resultado = false;
		if (v != null) {
			alert = new Alert(AlertType.INFORMATION, "Já temos uma visita com esse chamado, crie uma nova tarefa.");
			alert.setTitle("PROBLEMA AO SALVAR");
			alert.show();
			return resultado;
		}
		try {
			query = conn.prepareStatement(sql);
			query.setInt(1, obj.getNumeroChamado());
			query.setString(2, obj.getDataInicio().toString());
			query.setString(3, obj.getDataFim().toString());
			query.setInt(4, obj.getIdEmpresa());
			query.setString(5, obj.getTecnico());
			query.setBoolean(6, obj.getLad());
			query.setString(7, obj.getSituacao());
			int insert = query.executeUpdate();
			if (insert > 0) {
				alert = new Alert(AlertType.INFORMATION, "Visita Tecnica Salva.");
				alert.setTitle("Sucesso");
				alert.setHeaderText("Yes!");
				alert.show();
				resultado = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Connector.fecharConexao(conn, query, rs);
		}
		return resultado;
	}

	public boolean deletar(int numero) {
		VisitaTecnica v = buscarPorNumeroChamado(numero);
		conn = Connector.abrirConexao();
		sql = "DELETE FROM visita_tecnica where numero_chamado = ?";
		boolean bool = false;
		if (v == null) {
			alert = new Alert(AlertType.INFORMATION, "Visita inexistente");
			alert.setTitle("Ops!");
			alert.setHeaderText("Falha ao salvar");
			alert.show();
			return bool;
		}
		int resposta = JOptionPane.showConfirmDialog(null, "Realmente deseja deletar? Não existe opção desfazer.",
				"CONFIRMAÇÃO DE EXCLUSÃO", 0);
		if (resposta != 0) {
			return bool;
		}
		try {
			query = conn.prepareStatement(sql);
			query.setInt(1, numero);
			int delete = query.executeUpdate();
			if (delete > 0) {
				JOptionPane.showMessageDialog(null, "Visita Tecnica excluida", "Exclusao.", 2);
				bool = true;
			}
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		
		} finally {
			Connector.fecharConexao(conn, query, rs);
		}
		return bool;
	}

	public List<VisitaTecnica> listarTodos() {
		conn = Connector.abrirConexao();
		sql = "SELECT * FROM visita_tecnica";
		List<VisitaTecnica> listaVisitas = new ArrayList<>();
		VisitaTecnica v = null;
		try {
			query = conn.prepareStatement(sql);
			rs = query.executeQuery();
			while (rs.next()) {
				v = new VisitaTecnica();
				v.setIdVisitaTecnica(rs.getInt("id_visita_tecnica"));
				v.setNumeroChamado(rs.getInt("numero_chamado"));
				v.setIdEmpresa(rs.getInt("id_empresa"));
				v.setTecnico(rs.getString("tecnico"));
				v.setLad(rs.getBoolean(	"is_lad"));
				v.setSituacao(rs.getString("situacao"));
				v.setDataFim(LocalDate.parse(rs.getString("data_fim"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				v.setDataInicio(
						LocalDate.parse(rs.getString("data_inicio"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				listaVisitas.add(v);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Connector.fecharConexao(conn, query, rs);
		}
		return listaVisitas;
	}

	public VisitaTecnica buscarPorNumeroChamado(int numeroChamado) {
		VisitaTecnica v = null;
		conn = Connector.abrirConexao();
		sql = "SELECT * FROM visita_tecnica WHERE numero_chamado = ?";
		try {
			query = conn.prepareStatement(sql);
			query.setInt(1, numeroChamado);
			rs = query.executeQuery();
			if (rs.next()) {
				v = new VisitaTecnica();
				v.setIdVisitaTecnica(rs.getInt("id_visita_tecnica"));
				v.setNumeroChamado(rs.getInt("numero_chamado"));
				v.setIdEmpresa(rs.getInt("id_empresa"));
				v.setTecnico(rs.getString("tecnico"));
				v.setLad(rs.getBoolean("is_lad"));
				v.setSituacao(rs.getString("situacao"));
				v.setDataFim(LocalDate.parse(rs.getString("data_fim"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				v.setDataInicio(
						LocalDate.parse(rs.getString("data_inicio"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Connector.fecharConexao(conn, query, rs);
		}
		return v;
	}

	public List<VisitaTecnica> testBuscarin() {
		List<VisitaTecnica> lista = new ArrayList<>();
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement query = null;
		VisitaTecnica vs = null;
		try {
			conn = Connector.abrirConexao();
			String sql = "select * from visita_tecnica where numero_chamado in ("
					+ JOptionPane.showInputDialog("Qual o numero do chamado") + ")";
			query = conn.prepareStatement(sql);
			System.out.println();
			rs = query.executeQuery();
			while (rs.next()) {
				vs = new VisitaTecnica();
				vs.setIdVisitaTecnica(rs.getInt("id_visita_tecnica"));
				vs.setNumeroChamado(rs.getInt("numero_chamado"));
				vs.setDataInicio(
						LocalDate.parse(rs.getString("data_inicio"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				vs.setDataFim(LocalDate.parse(rs.getString("data_fim"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				vs.setIdEmpresa(rs.getInt("id_empresa"));
				vs.setTecnico(rs.getString("tecnico"));
				vs.setLad(rs.getBoolean("is_lad"));
				vs.setSituacao(rs.getString("situacao"));
				lista.add(vs);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Connector.fecharConexao(conn, query, rs);
		}
		return lista;
	}

	public Boolean update(VisitaTecnica v) {
		Boolean bool = false;
		conn = Connector.abrirConexao();
		sql = "UPDATE visita_tecnica SET numero_chamado = ?, tecnico = ?, data_inicio = ?, data_fim = ?, id_empresa = ?, situacao = ?, is_lad = ? WHERE id_visita_tecnica = ?";
		int update = 0;
		
		try { 
			query = conn.prepareStatement(sql);
			query.setInt(1, v.getNumeroChamado());
			query.setString(2, v.getTecnico());
			query.setString(3, v.getDataInicio().toString());
			query.setString(4, v.getDataFim().toString());
			query.setInt(5, v.getIdEmpresa());
			query.setString(6, v.getSituacao());
			query.setBoolean(7, v.getLad());
			query.setInt(8, v.getIdVisitaTecnica());
			update = query.executeUpdate();
			if (update > 0) {
				bool = true;
				Alert alert = new Alert(AlertType.INFORMATION, "Visita tecnica editada com sucesso.");
				alert.setTitle("Yes!");
				alert.setHeaderText("Sucesso!");
				alert.show();
			} else System.out.println("algo errado no update");
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION, "Algo deu errado, tente novamente.");
			alert.setTitle("Oops!");
			alert.setHeaderText("Algo inesperado ocorreu.");
			alert.show();
			e.getMessage();
			throw new RuntimeException(e);
		} finally {
			Connector.fecharConexao(conn, query, rs);
		}
		
		return bool;
	}
}
