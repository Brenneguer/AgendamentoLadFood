package agendamento.dao;

import java.util.List;

import agendamento.ChaveEstrangeiraException;

public interface IDao<T> {

	public boolean salvar(T istance) throws ChaveEstrangeiraException;

	public boolean deletar(int i);

	public List<T> listarTodos();
}
