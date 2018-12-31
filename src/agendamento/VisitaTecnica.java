package agendamento;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VisitaTecnica {
	private int idVisitaTecnica;
	private int numeroChamado;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private String tecnico;
	private int idEmpresa;
	private boolean isLad;
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

	public boolean isLad() {
		return isLad;
	}

	public void setLad(boolean isLad) {
		this.isLad = isLad;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String toString() {
		return (new StringBuilder("\nNumero Chamado: ")).append(getNumeroChamado()).append("\tEmpresa: ")
				.append(getIdEmpresa()).append("\tSitua\347\343o: ").append(getSituacao()).append("\tTecnico: ")
				.append(getTecnico()).append("\tData Inicio: ").append(getDataInicio()).append("\tData Prevista: ")
				.append(getDataFim()).toString();
	}

}
