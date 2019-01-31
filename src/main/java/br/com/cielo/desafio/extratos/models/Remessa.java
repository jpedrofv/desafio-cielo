package br.com.cielo.desafio.extratos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name="TB_REMESSA")
public class Remessa {

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="SQ_REMESSA", sequenceName="SQ_REMESSA", initialValue=1)
	@Column(name="ID_REMESSA")
	private Integer idRemessa;
	
	@Column(name="ID_DOMICILIO_BANCARIO")
	private Integer idDomicilioBancario;
	
	@Column(name="NUM_REMESSA_BANCO")
	private Integer numRemessaBanco;
	
	@Column(name="NOME_SITUACAO_REMESSA")
	private String nomeSituacaoRemessa;
	
	@Column(name="NOME_TIPO_OPERACAO")
	private String nomeTipoOperacao;
	
	public Integer getIdRemessa() {
		return idRemessa;
	}
	public void setIdRemessa(Integer idRemessa) {
		this.idRemessa = idRemessa;
	}
	public Integer getIdDomicilioBancario() {
		return idDomicilioBancario;
	}
	public void setIdDomicilioBancario(Integer idDomicilioBancario) {
		this.idDomicilioBancario = idDomicilioBancario;
	}
	public Integer getNumRemessaBanco() {
		return numRemessaBanco;
	}
	public void setNumRemessaBanco(Integer numRemessaBanco) {
		this.numRemessaBanco = numRemessaBanco;
	}
	public String getNomeSituacaoRemessa() {
		return nomeSituacaoRemessa;
	}
	public void setNomeSituacaoRemessa(String nomeSituacaoRemessa) {
		this.nomeSituacaoRemessa = nomeSituacaoRemessa;
	}
	public String getNomeTipoOperacao() {
		return nomeTipoOperacao;
	}
	public void setNomeTipoOperacao(String nomeTipoOperacao) {
		this.nomeTipoOperacao = nomeTipoOperacao;
	}
	
	
}
