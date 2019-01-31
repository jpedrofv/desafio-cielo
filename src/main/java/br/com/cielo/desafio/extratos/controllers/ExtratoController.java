package br.com.cielo.desafio.extratos.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cielo.desafio.extratos.controllers.requests.ExtratoRequest;
import br.com.cielo.desafio.extratos.controllers.responses.ExtratoResponse;
import br.com.cielo.desafio.extratos.controllers.responses.RespostaGenerica;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController("/extratos")
public class ExtratoController {
	
	@ApiOperation(value = "Consulta missões", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Concluído com sucesso."),
	                        @ApiResponse(code = 400, message = "Parâmetros não foram informados corretamente.") })
	@GetMapping
	public ResponseEntity<RespostaGenerica<ExtratoResponse>> buscarExtratoPorNumContaCorrente(@RequestParam ExtratoRequest request){
		if(request != null) {
			
		}
	}
}
