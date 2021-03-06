package br.com.cielo.desafio.extratos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cielo.desafio.extratos.exceptions.ParametrosObrigatoriosException;
import br.com.cielo.desafio.extratos.models.DomicilioBancario;
import br.com.cielo.desafio.extratos.models.Lancamento;
import br.com.cielo.desafio.extratos.models.Remessa;
import br.com.cielo.desafio.extratos.models.requests.ExtratoRequest;
import br.com.cielo.desafio.extratos.models.responses.ExtratoResponse;

@Service
public class ExtratoService {
	
	@Autowired
	private DomicilioBancarioService domicilioBancarioService;
	
	@Autowired
	private RemessaService remessaService;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	public List<ExtratoResponse> gerarExtrato(ExtratoRequest request) throws ParametrosObrigatoriosException{
		
		List<ExtratoResponse> response = new ArrayList<>();
		
		if(request != null) {
			
			DomicilioBancario domicilioBancario = domicilioBancarioService.buscarPorNumContaCorrente(request.getContaCorrente());
			
			if(domicilioBancario != null) {
				
				List<Remessa> listaRemessas = remessaService.buscarPorIdDomicilioBancario(domicilioBancario.getIdDomicilioBancario());
				
				List<Integer> listaIdRemessa = listaRemessas.stream()
				.filter(remessa -> remessa != null)
				.map(Remessa::getIdRemessa)
				.collect(Collectors.toList());
				
				List<Lancamento> listaLancamento = lancamentoService.buscarLancamentosPorIdRemessa(listaIdRemessa);
				
				
				listaLancamento.stream()
				.filter(lancamento -> lancamento != null)
				.forEach(l -> {
					
					ExtratoResponse extrato = new ExtratoResponse();
					
					Remessa remessa = listaRemessas.stream()
					.filter(r -> r != null && r.getIdRemessa() == l.getIdRemessa())
					.findAny()
					.orElse(null);
					
					if(remessa != null) {
						extrato.setNomeSituacaoRemessa(remessa.getNomeSituacaoRemessa());
						extrato.setDescricaoRemessa(remessa.getNomeTipoOperacao());
						extrato.setDataLancamento(l.getDtLancamentoContaCorrenteCliente());
						extrato.setDataEfetivaLancamento(l.getDtEfetivaLancamento());
						extrato.setNumEvento(l.getNumEvento());
						extrato.setValorFinal(l.getValorLancamentoRemessa());
						extrato.setDadosBancarios(l.getNomeBanco(), domicilioBancario.getNumAgencia(), domicilioBancario.getNumContaCorrente());
						
						response.add(extrato);
					}
					
				});
			}
		}else {
			
			throw new ParametrosObrigatoriosException("Filtro para pesquisa inválido.");
			
		}
		return response;
	}

}
