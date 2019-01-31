package br.com.cielo.desafio.extratos.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.cielo.desafio.extratos.controllers.responses.RespostaGenerica;
import br.com.cielo.desafio.extratos.exceptions.ParametrosObrigatoriosException;

@ControllerAdvice
public class ExtratosControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = ParametrosObrigatoriosException.class)
	protected ResponseEntity<Object> parametrosInvalidos(RuntimeException ex, WebRequest request) {
		RespostaGenerica<?> response = new RespostaGenerica<>();
		response.setMsg(ex.getMessage());
		response.setData(null);
		return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

	}
}
