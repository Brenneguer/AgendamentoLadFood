package agendamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import agendamento.VisitaTecnica;

public final class ConsultaDao {
	Connection conn = null;
	PreparedStatement query = null;
	ResultSet rs = null;
	String sql = "";
	
	public List<VisitaTecnica> consultaPorData(LocalDate dataInicioSelect, LocalDate dataFimSelect) {
		conn = Connector.abrirConexao();
		sql = "SELECT * FROM visita_tecnica WHERE data_inicio BETWEEN (?) AND (?)";
		VisitaTecnica v = null;
		List<VisitaTecnica> listaVisita = new ArrayList<>();
		
		try {
			query = conn.prepareStatement(sql);
			query.setString(1, dataInicioSelect.toString());
			query.setString(2, dataFimSelect.toString());
			rs = query.executeQuery();
			
			while (rs.next()) {
				v = new VisitaTecnica();
				v.setIdVisitaTecnica(rs.getInt("id_visita_tecnica"));
				v.setNumeroChamado(rs.getInt("numero_chamado"));
				v.setDataInicio(LocalDate.parse(rs.getString("data_inicio"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				v.setDataFim(LocalDate.parse(rs.getString("data_fim"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				v.setIdEmpresa(rs.getInt("id_empresa"));
				v.setTecnico(rs.getString("tecnico"));
				v.setLad(rs.getBoolean("is_lad"));
				v.setSituacao(rs.getString("situacao"));
				
				listaVisita.add(v);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Connector.fecharConexao(conn, query, rs);
		}
		return listaVisita;
	}
	
	
}
