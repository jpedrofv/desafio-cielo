package br.com.cielo.desafio.extratos.daos;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import br.com.cielo.desafio.extratos.models.Lancamento;

public interface LancamentoDAO extends CrudRepository<Lancamento, Serializable>{

}
