package br.com.cielo.desafio.extratos.daos;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.cielo.desafio.extratos.models.Remessa;

public interface RemessaDAO extends CrudRepository<Remessa, Serializable>{
	
	public Remessa findByNumRemessaBancoAndIdDomicilioBancario(Integer numRemessaBanco, Integer idDomicilioBancario);
	public List<Remessa> findByIdDomicilioBancario(Integer idDomicilioBancario);
}
