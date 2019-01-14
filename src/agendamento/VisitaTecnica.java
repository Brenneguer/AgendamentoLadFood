package agendamento;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VisitaTecnica {
	private int idVisitaTecnica;
	private int numeroChamado;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private String tecnico;
	private String tipo;
	private Integer idEmpresa;

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	private Boolean lad;
	private String situacao;

	public void setNumeroChamado(int numeroChamado) {
		this.numeroChamado = numeroChamado;
	}

	public int getNumeroChamado() {
		return numeroChamado;
	}

	public void setIdVisitaTecnica(int idVisitaTecnica) {
		this.idVisitaTecnica = idVisitaTecnica;
	}

	public int getIdVisitaTecnica() {
		return idVisitaTecnica;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = LocalDate.parse(dataInicio, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = LocalDate.parse(dataFim, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setTecnico(String tecnico) {
		this.tecnico = tecnico;
	}

	public String getTecnico() {
		return tecnico;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Boolean getLad() {
		return lad;
	}

	public void setLad(Boolean isLad) {
		this.lad = isLad;

	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String toString() {
		return ""+tecnico+";"+tipo+";"+numeroChamado+";"+idEmpresa+";"+dataInicio.toString()+";"+dataFim.toString()+";"+situacao+";"+lad;
	}

}
