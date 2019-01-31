package br.com.cielo.desafio.extratos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name="TB_DOMICILIO_BANCARIO")
public class DomicilioBancario {

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE) 
	@SequenceGenerator(sequenceName="SQ_DOMICILIO_BANCARIO", initialValue=1, name = "SQ_DOMICILIO_BANCARIO" )
	@Column(name="ID_DOMICILIO_BANCARIO")
	private Integer idDomicilioBancario;
	
	@Column(name="CODIGO_BANCO")
	private Integer codigoBanco;
	
	@Column(name="NUM_AGENCIA")
	private Integer numAgencia;
	
	@Column(name="NUM_CONTA_CORRENTE")
	private String numContaCorrente;

	public Integer getIdDomicilioBancario() {
		return idDomicilioBancario;
	}

	public void setIdDomicilioBancario(Integer idDomicilioBancario) {
		this.idDomicilioBancario = idDomicilioBancario;
	}

	public Integer getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(Integer codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public Integer getNumAgencia() {
		return numAgencia;
	}

	public void setNumAgencia(Integer numAgencia) {
		this.numAgencia = numAgencia;
	}

	public String getNumContaCorrente() {
		return numContaCorrente;
	}

	public void setNumContaCorrente(String numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
	}
	
	
}
