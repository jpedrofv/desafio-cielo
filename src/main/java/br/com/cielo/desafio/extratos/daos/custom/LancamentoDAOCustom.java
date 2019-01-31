package br.com.cielo.desafio.extratos.daos.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.cielo.desafio.extratos.models.Lancamento;

@Repository
public class LancamentoDAOCustom {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Lancamento> buscarPorIdRemessa(List<Integer> listaIdRemessa) {

		List<String> params = new ArrayList<>();
		params.add(String.join(",", listaIdRemessa.stream().map(String::valueOf).collect(Collectors.toList())));
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM TB_LANCAMENTO");
		query.append(" WHERE ID_REMESSA IN (?)");
		
		return jdbcTemplate.query(query.toString(), params.toArray(), new BeanPropertyRowMapper<>(Lancamento.class));
	}

}
