package br.com.cielo.desafio.extratos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cielo.desafio.extratos.daos.LancamentoDAO;
import br.com.cielo.desafio.extratos.daos.custom.LancamentoDAOCustom;
import br.com.cielo.desafio.extratos.exceptions.ParametrosObrigatoriosException;
import br.com.cielo.desafio.extratos.models.Lancamento;
import br.com.cielo.desafio.extratos.services.base.ServiceBaseCRUD;

@Service
public class LancamentoService extends ServiceBaseCRUD<Lancamento> {

	@Autowired
	private LancamentoDAO lancamentoDAO;
	
	@Autowired
	private LancamentoDAOCustom lancamentoDAOCustom;

	@Override
	public Lancamento cadastrar(Lancamento t) throws ParametrosObrigatoriosException {
		
		if (validarParametrosObrigatoriosInsercao(t)) {
			
			Lancamento existente = cadastroExistente(t);
			
			return existente != null ? existente : lancamentoDAO.save(t);
		}
		
		return null;
	}

	@Override
	public Lancamento atualizar(Lancamento t) throws ParametrosObrigatoriosException {
		if (validarParametrosObrigatoriosAtualizacao(t)) {

		}
		return null;
	}

	@Override
	public Lancamento buscarPorId(Integer id){
		if(id != null) {
			return lancamentoDAO.findOne(id);
		}
		return null;
	}

	@Override
	public List<Lancamento> buscarTodos() {
		return (List<Lancamento>) lancamentoDAO.findAll();
	}

	@Override
	protected boolean validarParametrosObrigatoriosInsercao(Lancamento t) throws ParametrosObrigatoriosException {
		
		if(t == null) throw new ParametrosObrigatoriosException("Objeto inválido.");
		if(t.getIdUnicoLegado() == null) throw new ParametrosObrigatoriosException("Lançamento sem registro no sistema legado.");
		
		return true;
	}

	@Override
	protected boolean validarParametrosObrigatoriosAtualizacao(Lancamento t) throws ParametrosObrigatoriosException {
		
		if(t == null) throw new ParametrosObrigatoriosException("Objeto inválido.");
		if(t.getIdLancamento() == null) throw new ParametrosObrigatoriosException("Lançamento sem registro.");
		if(t.getIdUnicoLegado() == null) throw new ParametrosObrigatoriosException("Lançamento sem idUnico.");
		
		return true;
	}

	@Override
	protected Lancamento cadastroExistente(Lancamento t) throws ParametrosObrigatoriosException {
		if(t != null)
			return lancamentoDAO.findByIdUnicoLegado(t.getIdUnicoLegado());
		else
			throw new ParametrosObrigatoriosException("Objeto inválido.");
	}
	
	public List<Lancamento> buscarLancamentosPorIdRemessa(List<Integer> listaIdRemessa) throws ParametrosObrigatoriosException{
		if(listaIdRemessa != null && !listaIdRemessa.isEmpty())
			return lancamentoDAOCustom.buscarPorIdRemessa(listaIdRemessa);
		else
			throw new ParametrosObrigatoriosException("Lista de IdRemessa vazia.");
	}
}
