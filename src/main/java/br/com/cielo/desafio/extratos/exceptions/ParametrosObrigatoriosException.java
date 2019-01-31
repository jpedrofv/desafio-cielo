package br.com.cielo.desafio.extratos.exceptions;

public class ParametrosObrigatoriosException extends Exception{

	
	private static final long serialVersionUID = -8783173077174820412L;

	public ParametrosObrigatoriosException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParametrosObrigatoriosException(String message) {
		super(message);
	}

	
}
