package br.com.cielo.desafio.extratos.controllers.responses;

public class RespostaGenerica<T> {

	private String msg;
	private T data;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
}
