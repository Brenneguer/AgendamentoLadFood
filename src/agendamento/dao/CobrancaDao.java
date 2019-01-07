package agendamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import agendamento.ChaveEstrangeiraException;
import agendamento.Cobranca;
import agendamento.VisitaTecnica;

// Referenced classes of package agendamento.dao:
//            IDao, VisitaTecnicaDao, Connector

public class CobrancaDao implements IDao<Cobranca> {
	private Connection conn;
	private String sql;
	private PreparedStatement query;
	private ResultSet rs;

	public boolean salvar(Cobranca obj) throws ChaveEstrangeiraException {
		Cobranca c = buscarPorNumeroChamado(obj.getNumeroChamado());
		VisitaTecnica v = (new VisitaTecnicaDao()).buscarPorNumeroChamado(obj.getNumeroChamado());
		boolean bool = false;
		if (v == null)
			throw new ChaveEstrangeiraException();
		if (c != null) {
			JOptionPane.showMessageDialog(null, "Já existe cobrança para essa visita.", "Cobrança Existente", 2);
			return bool;
		}
		conn = Connector.abrirConexao();
		sql = "INSERT INTO cobranca (numero_chamado, valor) VALUES (?,?)";
		try {
			query = conn.prepareStatement(sql);
			query.setInt(1, obj.getNumeroChamado());
			query.setDouble(2, obj.getValor());

			int insert = query.executeUpdate();
			if (insert > 0) {
				JOptionPane.showMessageDialog(null, "Cobrança Inserida.", "SUCESSO", 2);
				bool = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Connector.fecharConexao(conn, query, rs);
		}
		return bool;
	}

	public boolean deletar(int numero) {
		Cobranca c = buscarPorNumeroChamado(numero);
		boolean bool = false;
		if (c == null) {
			JOptionPane.showMessageDialog(null, "Não temos essa Cobrança cadastrada. Revise o numero da visita",
					"Não Encontrada", 1);
			return bool;
		}
		conn = Connector.abrirConexao();

		int resposta = JOptionPane.showConfirmDialog(null, "Realmente deseja deletar?\n\nNão existe opção desfazer.",
				"CONFIRMACÃO DE EXCLUSÃO", 0);
		if (resposta != 0)
			return bool;

		sql = "DELETE FROM cobranca WHERE numero_chamado = ?";
		int delete = 0;
		try {
			query = conn.prepareStatement(sql);
			query.setInt(1, numero);
			delete = query.executeUpdate();
			if (delete > 0) {
				JOptionPane.showMessageDialog(null, "Deletado com sucesso", "Sucesso", 2);
				bool = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Connector.fecharConexao(conn, query, rs);
		}
		return bool;
	}

	public List<Cobranca> listarTodos() {
		List<Cobranca> listaCobranca = new ArrayList<Cobranca>();
		conn = Connector.abrirConexao();
		sql = "SELECT * FROM cobranca";
		Cobranca c = null;

		try {
			query = conn.prepareStatement(sql);
			rs = query.executeQuery();
			while (rs.next()) {
				c = new Cobranca();
				c.setIdCobranca(rs.getInt("id_cobranca"));
				c.setNumeroChamado(rs.getInt("numero_chamado"));
				c.setValor(rs.getDouble("valor"));
				listaCobranca.add(c);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Connector.fecharConexao(conn, query, rs);
		}
		return listaCobranca;
	}

	public Cobranca buscarPorNumeroChamado(int numeroChamado) {
		conn = Connector.abrirConexao();
		sql = "SELECT * FROM cobranca WHERE numero_chamado = ?";
		Cobranca c = null;
		try {
			query = conn.prepareStatement(sql);
			query.setInt(1, numeroChamado);
			rs = query.executeQuery();
			if (rs.next()) {
				c = new Cobranca();
				c.setIdCobranca(rs.getInt("id_cobranca"));
				c.setNumeroChamado(rs.getInt("numero_chamado"));
				c.setValor(rs.getDouble("valor"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return c;
	}

}
