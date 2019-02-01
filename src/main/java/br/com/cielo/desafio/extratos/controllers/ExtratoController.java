package br.com.cielo.desafio.extratos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cielo.desafio.extratos.controllers.responses.RespostaGenerica;
import br.com.cielo.desafio.extratos.exceptions.ParametrosObrigatoriosException;
import br.com.cielo.desafio.extratos.models.requests.ExtratoRequest;
import br.com.cielo.desafio.extratos.models.responses.ExtratoResponse;
import br.com.cielo.desafio.extratos.services.ExtratoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="Extratos", description="Extrato de Lançamentos em Conta.")
public class ExtratoController{
	
	@Autowired
	private ExtratoService extratoService;
	
	@ApiOperation(value = "Buscar extrato utilizando o número da conta corrente.", consumes = "application/json", produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Concluído com sucesso."),
	                        @ApiResponse(code = 400, message = "Parâmetros não foram informados corretamente."),
    						@ApiResponse(code = 500, message = "Houve um erro interno no servidor.")})
	@GetMapping("/extratos")
	public ResponseEntity<RespostaGenerica<List<ExtratoResponse>>> buscarExtratoPorNumContaCorrente(@RequestParam String contaCorrente) throws ParametrosObrigatoriosException{
		
		RespostaGenerica<List<ExtratoResponse>> response = new RespostaGenerica<>();
		
		ExtratoRequest request = new ExtratoRequest();
		request.setContaCorrente(contaCorrente);
		if(request != null && StringUtils.hasText(request.getContaCorrente())) {
			
			List<ExtratoResponse> listaExtrato = extratoService.gerarExtrato(request);
			
			if(listaExtrato != null && !listaExtrato.isEmpty()) {
				
				response.setMsg("Lançamentos encontrados.");
				response.setData(listaExtrato);
				return new ResponseEntity<>(response, HttpStatus.OK);
				
			}else{
				
				response.setMsg("Nenhum lançamento encontrado.");
				response.setData(null);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
				
			}
		}
		
		response.setMsg("Paramêtros inválidos.");
		response.setData(null);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		
	}
}
