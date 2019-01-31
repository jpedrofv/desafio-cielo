package br.com.cielo.desafio.extratos.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name="TB_LANCAMENTO")
public class Lancamento {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="SQ_LANCAMENTO", sequenceName="SQ_LANCAMENTO", initialValue=1)
	@Column(name="ID_LANCAMENTO")
	private Integer idLancamento;
	
	@Column(name="ID_REMESSA")
	private Integer idRemessa;
	
	@Column(name="DT_EFETIVA_LANCAMENTO")
	private Date dtEfetivaLancamento;
	
	@Column(name="DT_LANCAMENTO_CONTA_CORRENTE_CLIENTE")
	private Date dtLancamentoContaCorrenteCliente;
	
	@Column(name="NUM_EVENTO")
	private Integer numEvento;
	
	@Column(name="DESC_GRUPO_PAGAMENTO")
	private String descGrupoPagamento;
	
	@Column(name="ID_UNICO_LEGADO")
	private Integer idUnicoLegado;
	
	@Column(name="NOME_BANCO")
	private String nomeBanco;
	
	@Column(name="QTD_LANCAMENTO_REMESSA")
	private Integer qtdLancamentoRemessa;
	
	@Column(name="NUM_RAIZ_CNPJ")
	private String numRaizCnpj;
	
	@Column(name="NUM_SUFIX_CNPJ")
	private String numSufixCnpj;
	
	@Column(name="VALOR_LANCAMENTO_REMESSA")
	private BigDecimal valorLancamentoRemessa;
	
	public Integer getIdLancamento() {
		return idLancamento;
	}
	public void setIdLancamento(Integer idLancamento) {
		this.idLancamento = idLancamento;
	}
	public Integer getIdRemessa() {
		return idRemessa;
	}
	public void setIdRemessa(Integer idRemessa) {
		this.idRemessa = idRemessa;
	}
	public Date getDtEfetivaLancamento() {
		return dtEfetivaLancamento;
	}
	public void setDtEfetivaLancamento(Date dtEfetivaLancamento) {
		this.dtEfetivaLancamento = dtEfetivaLancamento;
	}
	public Date getDtLancamentoContaCorrenteCliente() {
		return dtLancamentoContaCorrenteCliente;
	}
	public void setDtLancamentoContaCorrenteCliente(Date dtLancamentoContaCorrenteCliente) {
		this.dtLancamentoContaCorrenteCliente = dtLancamentoContaCorrenteCliente;
	}
	public Integer getNumEvento() {
		return numEvento;
	}
	public void setNumEvento(Integer numEvento) {
		this.numEvento = numEvento;
	}
	public String getDescGrupoPagamento() {
		return descGrupoPagamento;
	}
	public void setDescGrupoPagamento(String descGrupoPagamento) {
		this.descGrupoPagamento = descGrupoPagamento;
	}
	public Integer getIdUnicoLegado() {
		return idUnicoLegado;
	}
	public void setIdUnicoLegado(Integer idUnicoLegado) {
		this.idUnicoLegado = idUnicoLegado;
	}
	public String getNomeBanco() {
		return nomeBanco;
	}
	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}
	public Integer getQtdLancamentoRemessa() {
		return qtdLancamentoRemessa;
	}
	public void setQtdLancamentoRemessa(Integer qtdLancamentoRemessa) {
		this.qtdLancamentoRemessa = qtdLancamentoRemessa;
	}
	public String getNumRaizCnpj() {
		return numRaizCnpj;
	}
	public void setNumRaizCnpj(String numRaizCnpj) {
		this.numRaizCnpj = numRaizCnpj;
	}
	public String getNumSufixCnpj() {
		return numSufixCnpj;
	}
	public void setNumSufixCnpj(String numSufixCnpj) {
		this.numSufixCnpj = numSufixCnpj;
	}
	public BigDecimal getValorLancamentoRemessa() {
		return valorLancamentoRemessa;
	}
	public void setValorLancamentoRemessa(BigDecimal valorLancamentoRemessa) {
		this.valorLancamentoRemessa = valorLancamentoRemessa;
	}

	
}
