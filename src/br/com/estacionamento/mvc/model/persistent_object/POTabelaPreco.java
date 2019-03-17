package br.com.estacionamento.mvc.model.persistent_object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

@Entity
@Table(name = "TB_TABELA_PRECO")
public class POTabelaPreco {

//	+---------------------------+-------------------------+------+-----+---------+-------+
//	| Field                     | Type                    | Null | Key | Default | Extra |
//	+---------------------------+-------------------------+------+-----+---------+-------+
//	| TB_TABELA_PRECO_ID        | int(11)                 | NO   | PRI | NULL    |       |
//	| TB_TABELA_PRECO_VALOR     | double                  | NO   |     | NULL    |       |
//	| TB_TABELA_PRECO_STATUS    | enum('ativo','inativo') | NO   |     | NULL    |       |
//	| TB_TABELA_PRECO_TEMPO_MIN | int(11)                 | NO   |     | NULL    |       |
//	| TB_TABELA_PRECO_TEMPO_MAX | int(11)                 | NO   |     | NULL    |       |
//	+---------------------------+-------------------------+------+-----+---------+-------+

	@Id
	@GeneratedValue
	@Column(name = "TB_TABELA_PRECO_ID", nullable = false, length = 11)
	private int idTabelaP;

	@Column(name = "TB_TABELA_PRECO_VALOR", nullable = false)
	private double tabelaPValor;

	@Enumerated(EnumType.STRING)
	@Column(name = "TB_TABELA_PRECO_STATUS", nullable = false)
	private EnumStatus statusTabelaP;

	@Column(name = "TB_TABELA_PRECO_TEMPO_MIN", nullable = false)
	private int tabelaPTempoMin;

	@Column(name = "TB_TABELA_PRECO_TEMPO_MAX", nullable = false)
	private int tabelaPTempoMax;

	public int getIdTabelaP() {
		return idTabelaP;
	}

	public void setIdTabelaP(int idTabelaP) {
		this.idTabelaP = idTabelaP;
	}

	public double getTabelaPValor() {
		return tabelaPValor;
	}

	public void setTabelaPValor(double tabelaPValor) {
		this.tabelaPValor = tabelaPValor;
	}

	public EnumStatus getStatusTabelaP() {
		return statusTabelaP;
	}

	public void setStatusTabelaP(EnumStatus statusTabelaP) {
		this.statusTabelaP = statusTabelaP;
	}

	public int getTabelaPTempoMin() {
		return tabelaPTempoMin;
	}

	public void setTabelaPTempoMin(int tabelaPTempoMin) {
		this.tabelaPTempoMin = tabelaPTempoMin;
	}

	public int getTabelaPTempoMax() {
		return tabelaPTempoMax;
	}

	public void setTabelaPTempoMax(int tabelaPTempoMax) {
		this.tabelaPTempoMax = tabelaPTempoMax;
	}
	
	

}
