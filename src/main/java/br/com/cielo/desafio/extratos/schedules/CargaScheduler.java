package br.com.cielo.desafio.extratos.schedules;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.cielo.desafio.extratos.exceptions.ParametrosObrigatoriosException;
import br.com.cielo.desafio.extratos.models.DomicilioBancario;
import br.com.cielo.desafio.extratos.models.Lancamento;
import br.com.cielo.desafio.extratos.models.Remessa;
import br.com.cielo.desafio.extratos.services.DomicilioBancarioService;
import br.com.cielo.desafio.extratos.services.LancamentoService;
import br.com.cielo.desafio.extratos.services.RemessaService;

@Component
@EnableScheduling
public class CargaScheduler {

	@Value("${desafio.importacao.carga.processar}")
	private String caminhoLocalImportacao;
	
	@Value("${desafio.importacao.carga.filename}")
	private String arquivoCarga;
	
	@Autowired
	private DomicilioBancarioService domicilioBancarioService;
	
	@Autowired
	private RemessaService remessaService;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	Logger log = Logger.getLogger(CargaScheduler.class);
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	@Scheduled(cron="0 */5 * * * *", zone="America/Sao_Paulo")
	public void inserirCargaSistemaLegado() {
		
	    JSONParser parser = new JSONParser();
	    log.info("Iniciando Carga...");
		try (FileReader arquivo = new FileReader(caminhoLocalImportacao + "/" + arquivoCarga)) {
			SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
			Object obj = parser.parse(arquivo);
			JSONObject jsonObject = new JSONObject(obj.toString());
			
            JSONArray listaControleLancamento = jsonObject.getJSONArray("listaControleLancamento");
            	
            	for(int i = 0; i < listaControleLancamento.length() ; i++) {
            		
            		JSONObject controleLancamento = listaControleLancamento.getJSONObject(i);
            		JSONObject lancamentoContaCorrenteCliente = controleLancamento.getJSONObject("lancamentoContaCorrenteCliente");
            		
            		Lancamento lancamento = new Lancamento();
            		lancamento.setDtEfetivaLancamento(fmt.parse(controleLancamento.getString("dataEfetivaLancamento")));
            		lancamento.setDtLancamentoContaCorrenteCliente(fmt.parse(controleLancamento.getString("dataLancamentoContaCorrenteCliente")));
            		lancamento.setNumEvento(controleLancamento.getInt("numeroEvento"));
            		lancamento.setDescGrupoPagamento(controleLancamento.getString("descricaoGrupoPagamento"));
            		lancamento.setIdUnicoLegado(controleLancamento.getInt("codigoIdentificadorUnico"));
            		lancamento.setNomeBanco(controleLancamento.getString("nomeBanco"));
            		lancamento.setQtdLancamentoRemessa(controleLancamento.getInt("quantidadeLancamentoRemessa"));
            		lancamento.setNumRaizCnpj(controleLancamento.getString("numeroRaizCNPJ"));
            		lancamento.setNumSufixCnpj(controleLancamento.getString("numeroSufixoCNPJ"));
            		lancamento.setValorLancamentoRemessa(controleLancamento.getBigDecimal("valorLancamentoRemessa"));
            		
            		if(lancamentoContaCorrenteCliente != null) {
            			
            			Remessa remessa = new Remessa();
            			remessa.setNomeSituacaoRemessa(lancamentoContaCorrenteCliente.getString("nomeSituacaoRemessa"));
            			remessa.setNomeTipoOperacao(lancamentoContaCorrenteCliente.getString("nomeTipoOperacao"));
            			remessa.setNumRemessaBanco(lancamentoContaCorrenteCliente.getInt("numeroRemessaBanco"));
            			JSONObject dadosDomicilioBancario = lancamentoContaCorrenteCliente.getJSONObject("dadosDomicilioBancario");
            			
            			if(dadosDomicilioBancario != null) {
            				DomicilioBancario domicilioBancario = new DomicilioBancario();
            				domicilioBancario.setNumContaCorrente(dadosDomicilioBancario.getString("numeroContaCorrente"));
            				domicilioBancario.setCodigoBanco(dadosDomicilioBancario.getInt("codigoBanco"));
            				domicilioBancario.setNumAgencia(dadosDomicilioBancario.getInt("numeroAgencia"));
            				domicilioBancario = domicilioBancarioService.cadastrar(domicilioBancario);
            				remessa.setIdDomicilioBancario(domicilioBancario.getIdDomicilioBancario());
            			}
            			
            		remessa = remessaService.cadastrar(remessa);
            		lancamento.setIdRemessa(remessa.getIdRemessa());
            		lancamentoService.cadastrar(lancamento);
            		
            		}
            	}
            	
            log.info("Importação concluída...");
            log.info("Removendo arquivos de importação.");
            Files.deleteIfExists(Paths.get(caminhoLocalImportacao));
            
        } catch (FileNotFoundException e) {
        	
			log.error(e.getStackTrace());
			
		} catch (IOException e) {
			
			log.error(e.getStackTrace());
			
		} catch (ParseException e) {
			
			log.error(e.getStackTrace());
			
		} catch (ParametrosObrigatoriosException e) {
			
			log.error(e.getStackTrace());
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
	}
}
