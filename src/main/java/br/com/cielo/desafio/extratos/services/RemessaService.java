package br.com.cielo.desafio.extratos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cielo.desafio.extratos.daos.RemessaDAO;
import br.com.cielo.desafio.extratos.exceptions.ParametrosObrigatoriosException;
import br.com.cielo.desafio.extratos.models.Remessa;
import br.com.cielo.desafio.extratos.services.base.ServiceBaseCRUD;

@Service
public class RemessaService extends ServiceBaseCRUD<Remessa>{

	@Autowired
	private RemessaDAO remessaDAO;

	@Override
	public Remessa cadastrar(Remessa t) throws ParametrosObrigatoriosException {
		
		if(validarParametrosObrigatoriosInsercao(t)) {
			
			Remessa existente = cadastroExistente(t);
			
			return existente != null ? existente : remessaDAO.save(t);
		}
		
		return null;
	}
	
	@Override
	public Remessa atualizar(Remessa t) throws ParametrosObrigatoriosException {
		if(validarParametrosObrigatoriosAtualizacao(t)) {
			
		}
		return null;
	}

	@Override
	public Remessa buscarPorId(Integer id){
		if(id != null && id > 0) {
			return remessaDAO.findOne(id);
		}
		return null;
	}

	@Override
	public List<Remessa> buscarTodos() {
		return (List<Remessa>) remessaDAO.findAll();
	}

	@Override
	protected boolean validarParametrosObrigatoriosInsercao(Remessa t) throws ParametrosObrigatoriosException {
		
		if(t == null) throw new ParametrosObrigatoriosException("Objeto inválido.");
		if(t.getIdDomicilioBancario() == null) throw new ParametrosObrigatoriosException("Não é possível cadastrar uma remessa sem o código do Domicilio Bancário.");
		if(t.getNumRemessaBanco() == null) throw new ParametrosObrigatoriosException("Número Remessa Banco inválido.");
		
		return true;
	}

	@Override
	protected boolean validarParametrosObrigatoriosAtualizacao(Remessa t) throws ParametrosObrigatoriosException {
		return true;
	}

	@Override
	protected Remessa cadastroExistente(Remessa t) throws ParametrosObrigatoriosException {
		
		if(t != null) 
			return remessaDAO.findByNumRemessaBancoAndIdDomicilioBancario(t.getNumRemessaBanco(), t.getIdDomicilioBancario());
		else
			throw new ParametrosObrigatoriosException("Objeto inválido.");
	}
	
	public List<Remessa> buscarPorIdDomicilioBancario(Integer idDomicilioBancario) throws ParametrosObrigatoriosException{
		
		if(idDomicilioBancario != null && idDomicilioBancario > 0)
			return remessaDAO.findByIdDomicilioBancario(idDomicilioBancario);
		else
			throw new ParametrosObrigatoriosException("Id do Domicilio Bancário inválido.");
	}

}
