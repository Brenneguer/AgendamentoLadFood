package agendamento;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VisitaTecnica {
	private int idVisitaTecnica;
	private int numeroChamado;
	private double valor;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private String tecnico;
	private String tipo;
	private String empresa;
	private String obs;

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String toString() {
		return ""+tecnico+";"+tipo+";"+numeroChamado+";"+empresa+";"+dataInicio.toString()+";"+dataFim.toString()+";"+situacao+";"+lad;
	}

}
