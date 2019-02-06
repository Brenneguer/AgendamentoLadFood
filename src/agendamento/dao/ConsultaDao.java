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

public class ConsultaDao {
	Connection conn = null;
	PreparedStatement query = null;
	ResultSet rs = null;
	String sql = "";
	List<VisitaTecnica> listaVisita = null;

	public List<VisitaTecnica> consultaPorData(LocalDate dataInicioSelect, LocalDate dataFimSelect) {
		conn = Connector.abrirConexao();
		sql = "SELECT * FROM visita_tecnica WHERE data_inicio BETWEEN (?) AND (?)";
		VisitaTecnica v = null;
		listaVisita = new ArrayList<>();

		try {
			query = conn.prepareStatement(sql);
			query.setString(1, dataInicioSelect.toString());
			query.setString(2, dataFimSelect.toString());
			rs = query.executeQuery();

			while (rs.next()) {
				v = new VisitaTecnica();
				v.setIdVisitaTecnica(rs.getInt("id_visita_tecnica"));
				v.setNumeroChamado(rs.getInt("numero_chamado"));
				v.setTipo(rs.getString("tipo"));
				v.setDataInicio(
						LocalDate.parse(rs.getString("data_inicio"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				v.setDataFim(LocalDate.parse(rs.getString("data_fim"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				v.setEmpresa(rs.getString("empresa"));
				v.setTecnico(rs.getString("tecnico"));
				v.setLad(rs.getBoolean("is_lad"));
				v.setSituacao(rs.getString("situacao"));
				v.setObs(rs.getString("obs"));
				v.setValor(rs.getDouble("valor"));

				listaVisita.add(v);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Connector.fecharConexao(conn, query, rs);
		}
		return listaVisita;
	}
	
	public List<VisitaTecnica> consultaPorChamado(int numeroChamado) {
		listaVisita = new ArrayList<>();
		conn = Connector.abrirConexao();
		sql = "SELECT * FROM visita_tecnica where numero_chamado = "+"?";
		VisitaTecnica v = null;
		
		try {
			query = conn.prepareStatement(sql);
			query.setInt(1, numeroChamado);
			rs = query.executeQuery();
			
			while (rs.next()) {
				v = new VisitaTecnica();
				v.setIdVisitaTecnica(rs.getInt("id_visita_tecnica"));
				v.setNumeroChamado(rs.getInt("numero_chamado"));
				v.setTipo(rs.getString("tipo"));
				v.setDataInicio(LocalDate.parse(rs.getString("data_inicio"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				v.setDataFim(LocalDate.parse(rs.getString("data_fim"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				v.setEmpresa(rs.getString("empresa"));	
				v.setTecnico(rs.getString("tecnico"));
				v.setLad(rs.getBoolean("is_lad"));
				v.setSituacao(rs.getString("situacao"));
				v.setObs(rs.getString("obs"));
				v.setValor(rs.getDouble("valor"));
				
				listaVisita.add(v);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return listaVisita;
	}

	public List<VisitaTecnica> consultaCobradas(List<VisitaTecnica> lista) {
		List<VisitaTecnica> listaCobradas = new ArrayList<>();
		if (!lista.isEmpty()) {
			for (VisitaTecnica v : lista) {
				if (v.getLad() == true) {
					listaCobradas.add(v);
				}
			}
		}
		return listaCobradas;
	}

	public List<VisitaTecnica> consultaTecnico(List<VisitaTecnica> lista, String tecnico) {
		List<VisitaTecnica> listaVisitaTecnico = new ArrayList<VisitaTecnica>();
		if (!lista.isEmpty()) {
			for (VisitaTecnica v : lista) {
				if (v.getTecnico().contains(tecnico)) {
					listaVisitaTecnico.add(v);
				}
			}
		}
		return listaVisitaTecnico;
	}
	
	public List<VisitaTecnica> consultaPorChamado(List<VisitaTecnica> l, int numeroChamado) {
		List<VisitaTecnica> listaContentChamado = new ArrayList<>();
		
		for (VisitaTecnica v : l) {
			if(v.getNumeroChamado() == numeroChamado) {
				listaContentChamado.add(v);
			}
		}
		return listaContentChamado;
	}
	
	public List<VisitaTecnica> consultaTecnico(String tecnico) {
		List<VisitaTecnica> listaVisitaTecnico = new ArrayList<>();
		conn = Connector.abrirConexao();
		sql = "SELECT * FROM visita_tecnica WHERE tecnico LIKE ?";
		VisitaTecnica v = null;
		
		try {
			query = conn.prepareStatement(sql);
			query.setString(1, "%"+tecnico+"%");
			rs = query.executeQuery();
			
			while (rs.next()) {
				v = new VisitaTecnica();
				v.setIdVisitaTecnica(rs.getInt("id_visita_tecnica"));
				v.setNumeroChamado(rs.getInt("numero_chamado"));
				v.setTipo(rs.getString("tipo"));
				v.setDataInicio(LocalDate.parse(rs.getString("data_inicio"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				v.setDataFim(LocalDate.parse(rs.getString("data_fim"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				v.setEmpresa(rs.getString("empresa"));
				v.setTecnico(rs.getString("tecnico"));
				v.setLad(rs.getBoolean("is_lad"));
				v.setSituacao(rs.getString("situacao"));
				v.setObs(rs.getString("obs"));
				v.setValor(rs.getDouble("valor"));
				
				listaVisitaTecnico.add(v);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return listaVisitaTecnico;
	}

	public List<VisitaTecnica> consultaTipo(List<VisitaTecnica> lista, String tipo) {
		
		if(lista.isEmpty()) {
			System.out.println("lista não pode estar vazia");
			return null;
		}
		listaVisita = new ArrayList<>();
		for(VisitaTecnica v : lista) {
			if (v.getTipo().equalsIgnoreCase(tipo)) {
				listaVisita.add(v);
			}
		}
		return listaVisita;
	}
}
