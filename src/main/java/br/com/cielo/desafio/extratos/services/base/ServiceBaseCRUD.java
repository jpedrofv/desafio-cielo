package br.com.cielo.desafio.extratos.services.base;

import java.util.List;

import br.com.cielo.desafio.extratos.exceptions.ParametrosObrigatoriosException;

public abstract class ServiceBaseCRUD<T> {

	public abstract T cadastrar(T t) throws ParametrosObrigatoriosException;
	public abstract T atualizar(T t) throws ParametrosObrigatoriosException;
	public abstract T buscarPorId(Integer id);
	public abstract List<T> buscarTodos();
	
	protected abstract boolean validarParametrosObrigatoriosInsercao(T t) throws ParametrosObrigatoriosException;
	protected abstract boolean validarParametrosObrigatoriosAtualizacao(T t) throws ParametrosObrigatoriosException;
	protected abstract T cadastroExistente(T t) throws ParametrosObrigatoriosException;
}
