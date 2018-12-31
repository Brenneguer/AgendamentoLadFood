package agendamento.dao;

import java.util.List;

import agendamento.ChaveEstrangeiraException;

public interface IDao<T> {

	public void salvar(T istance) throws ChaveEstrangeiraException;

	public void deletar(int i);

	public List<T> listarTodos();
}
