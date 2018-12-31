package agendamento;

public class Cobranca {
	private int idCobranca;
	private int numeroChamado;
	private double valor;

	public int getIdCobranca() {
		return idCobranca;
	}

	public void setIdCobranca(int idCobranca) {
		this.idCobranca = idCobranca;
	}

	public int getNumeroChamado() {
		return numeroChamado;
	}

	public void setNumeroChamado(int numeroChamado) {
		this.numeroChamado = numeroChamado;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String toString() {
		return (new StringBuilder("\nID: ")).append(getIdCobranca()).append("\tChamado: ").append(getNumeroChamado())
				.append("\tValor: R$ ").append(getValor()).toString();
	}
}
