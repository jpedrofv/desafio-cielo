package br.com.cielo.desafio.extratos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.cielo.desafio.extratos.daos.DomicilioBancarioDAO;
import br.com.cielo.desafio.extratos.exceptions.ParametrosObrigatoriosException;
import br.com.cielo.desafio.extratos.models.DomicilioBancario;
import br.com.cielo.desafio.extratos.services.base.ServiceBaseCRUD;

@Service
public class DomicilioBancarioService extends ServiceBaseCRUD<DomicilioBancario> {

	@Autowired
	private DomicilioBancarioDAO domicilioBancarioDAO;

	@Override
	public DomicilioBancario cadastrar(DomicilioBancario t) throws ParametrosObrigatoriosException {
		if (validarParametrosObrigatoriosInsercao(t)) {

			DomicilioBancario domicilioBancarioExistente = cadastroExistente(t);

			return domicilioBancarioExistente != null ? domicilioBancarioExistente : domicilioBancarioDAO.save(t);
		}

		return null;
	}

	@Override
	public DomicilioBancario atualizar(DomicilioBancario t) throws ParametrosObrigatoriosException {
		if (validarParametrosObrigatoriosAtualizacao(t)) {

		}
		return null;
	}

	@Override
	public DomicilioBancario buscarPorId(Integer id){
		if(id != null) {
			return domicilioBancarioDAO.findOne(id);
		}
		return null;
	}

	@Override
	public List<DomicilioBancario> buscarTodos() {
		return (List<DomicilioBancario>) domicilioBancarioDAO.findAll();
	}

	@Override
	protected boolean validarParametrosObrigatoriosInsercao(DomicilioBancario t) throws ParametrosObrigatoriosException {

		if (t == null) throw new ParametrosObrigatoriosException("Objeto inválido.");
		if (t.getNumAgencia() == null) throw new ParametrosObrigatoriosException("Número da agência inválido.");
		if (t.getCodigoBanco() == null) throw new ParametrosObrigatoriosException("Código do Banco inválido.");
		if (!StringUtils.hasText(t.getNumContaCorrente())) throw new ParametrosObrigatoriosException("Número da conta corrente não informado.");
	
		return true;
	}

	@Override
	protected boolean validarParametrosObrigatoriosAtualizacao(DomicilioBancario t)
			throws ParametrosObrigatoriosException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected DomicilioBancario cadastroExistente(DomicilioBancario t) throws ParametrosObrigatoriosException {
		if(t != null)
			return domicilioBancarioDAO
				.findByNumContaCorrenteAndNumAgenciaAndCodigoBanco(t.getNumContaCorrente(), t.getNumAgencia(),
						t.getCodigoBanco());
		else 
			throw new ParametrosObrigatoriosException("Objeto inválido.");
	}
	
	public DomicilioBancario buscarPorNumContaCorrente(String contaCorrente) throws ParametrosObrigatoriosException{
		if(StringUtils.hasText(contaCorrente))
			return domicilioBancarioDAO.findByNumContaCorrente(contaCorrente);
		else
			throw new ParametrosObrigatoriosException("Número da Conta Corrente não informado.");
	}
}
