package agendamento;

import java.sql.SQLException;

public class ChaveEstrangeiraException extends SQLException {

	public String getMessage() {
		return "CHAVE ESTRANGEIRA N\303O LOCALIZADA";
	}

	private static final long serialVersionUID = 1L;
}