package br.com.cielo.desafio.extratos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.cielo.desafio.extratos.daos.DomicilioBancarioDAO;
import br.com.cielo.desafio.extratos.exceptions.ParametrosObrigatoriosException;
import br.com.cielo.desafio.extratos.models.DomicilioBancario;

@Service
public class DomicilioBancarioService {
	
	@Autowired
	private DomicilioBancarioDAO domicilioBancarioDAO;
	
	public DomicilioBancario inserirDomicilioBancario(DomicilioBancario domicilioBancario) throws ParametrosObrigatoriosException{
		
		if(validaParametrosObrigatoriosInsercao(domicilioBancario)) {
			return domicilioBancarioDAO.save(domicilioBancario);
		}
		
		return null;
	}

	private boolean validaParametrosObrigatoriosInsercao(DomicilioBancario domicilioBancario) throws ParametrosObrigatoriosException{
		
		if(domicilioBancario == null) throw new ParametrosObrigatoriosException("Objeto inválido.");
		if(domicilioBancario.getNumAgencia() == null) throw new ParametrosObrigatoriosException("Número da agência inválido.");
		if(domicilioBancario.getCodigoBanco() == null)throw new ParametrosObrigatoriosException("Código do Banco inválido.");
		if(!StringUtils.hasText(domicilioBancario.getNumContaCorrente()))throw new ParametrosObrigatoriosException("Número da conta corrente não informado.");
		
		return true;
	}
}
