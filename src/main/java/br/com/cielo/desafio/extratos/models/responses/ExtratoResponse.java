package br.com.cielo.desafio.extratos.models.responses;

import java.math.BigDecimal;
import java.util.Date;

public class ExtratoResponse {

	private Date dataLancamento;
	private String descricaoRemessa;
	private Integer numEvento;
	private String nomeSituacaoRemessa;
	private Date dataEfetivaLancamento;
	private String dadosBancarios;
	private BigDecimal valorFinal;
	
	public Date getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public String getDescricaoRemessa() {
		return descricaoRemessa;
	}
	public void setDescricaoRemessa(String descricaoRemessa) {
		this.descricaoRemessa = descricaoRemessa;
	}
	public Integer getNumEvento() {
		return numEvento;
	}
	public void setNumEvento(Integer numEvento) {
		this.numEvento = numEvento;
	}
	public String getNomeSituacaoRemessa() {
		return nomeSituacaoRemessa;
	}
	public void setNomeSituacaoRemessa(String nomeSituacaoRemessa) {
		this.nomeSituacaoRemessa = nomeSituacaoRemessa;
	}
	public Date getDataEfetivaLancamento() {
		return dataEfetivaLancamento;
	}
	public void setDataEfetivaLancamento(Date dataEfetivaLancamento) {
		this.dataEfetivaLancamento = dataEfetivaLancamento;
	}
	public String getDadosBancarios() {
		return dadosBancarios;
	}
	public void setDadosBancarios(String nomeBanco, Integer agencia, String numContaCorrente) {
		this.dadosBancarios = "BANCO " + nomeBanco + " Ag " + agencia + " CC " + numContaCorrente;
	}
	public BigDecimal getValorFinal() {
		return valorFinal;
	}
	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}
	
	
}
