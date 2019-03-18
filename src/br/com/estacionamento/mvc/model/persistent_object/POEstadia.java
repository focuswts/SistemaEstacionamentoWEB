package br.com.estacionamento.mvc.model.persistent_object;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

@Entity
@Table(name = "TB_ESTADIA")
public class POEstadia {

//	+--------------------+-------------------------+------+-----+-------------------+-----------------------------------------------+
//	| Field              | Type                    | Null | Key | Default           | Extra                                         |
//	+--------------------+-------------------------+------+-----+-------------------+-----------------------------------------------+
//	| TB_ESTADIA_ID      | int(11)                 | NO   | PRI | NULL              |                                               |
//	| TB_ESTADIA_INICIO  | timestamp               | NO   |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED on update CURRENT_TIMESTAMP |
//	| TB_ESTADIA_TERMINO | timestamp               | YES  |     | NULL              |                                               |
//	| TB_ESTADIA_PLACA   | varchar(8)              | NO   |     | NULL              |                                               |
//	| TB_ESTADIA_STATUS  | enum('ativo','inativo') | NO   |     | NULL              |                                               |
//	| TB_TABELA_PRECO_ID | int(11)                 | NO   | MUL | NULL              |                                               |
//	| TB_VAGA_ID         | int(11)                 | NO   | MUL | NULL              |                                               |
//	| TB_VEICULO_ID      | int(11)                 | YES  | MUL | NULL              |                                               |
//	+--------------------+-------------------------+------+-----+-------------------+-----------------------------------------------+
//
	@Id
	@GeneratedValue
	@Column(name = "TB_ESTADIA_ID", nullable = false, length = 11)
	private int idEstadia;

	@Column(name = "TB_ESTADIA_INICIO", nullable = false)
	private Timestamp inicioEstadia;

	@Column(name = "TB_ESTADIA_TERMINO", nullable = false)
	private Timestamp terminoEstadia;

	@Column(name = "TB_ESTADIA_PLACA", nullable = true, length = 8)
	private String placaEstadia;

	@Enumerated(EnumType.STRING)
	@Column(name = "TB_ESTADIA_STATUS", nullable = false)
	private EnumStatus statusEstadia;

	@ManyToOne
	@JoinColumn(name = "TB_TABELA_PRECO_ID", nullable = false)
	private POTabelaPreco idTabelaP;
	
	@OneToOne
	@JoinColumn(name = "TB_VAGA_ID", nullable = false)
	private POVaga idVaga;
	
	@OneToOne
	@JoinColumn(name = "TB_VEICULO_ID", nullable = false)
	private POVeiculo idVeiculo;

	public int getIdEstadia() {
		return idEstadia;
	}

	public void setIdEstadia(int idEstadia) {
		this.idEstadia = idEstadia;
	}

	public Timestamp getInicioEstadia() {
		return inicioEstadia;
	}

	public void setInicioEstadia(Timestamp inicioEstadia) {
		this.inicioEstadia = inicioEstadia;
	}

	public Timestamp getTerminoEstadia() {
		return terminoEstadia;
	}

	public void setTerminoEstadia(Timestamp terminoEstadia) {
		this.terminoEstadia = terminoEstadia;
	}

	public String getPlacaEstadia() {
		return placaEstadia;
	}

	public void setPlacaEstadia(String placaEstadia) {
		this.placaEstadia = placaEstadia;
	}

	public EnumStatus getStatusEstadia() {
		return statusEstadia;
	}

	public void setStatusEstadia(EnumStatus statusEstadia) {
		this.statusEstadia = statusEstadia;
	}

	public POTabelaPreco getIdTabelaP() {
		return idTabelaP;
	}

	public void setIdTabelaP(POTabelaPreco idTabelaP) {
		this.idTabelaP = idTabelaP;
	}

	public POVaga getIdVaga() {
		return idVaga;
	}

	public void setIdVaga(POVaga idVaga) {
		this.idVaga = idVaga;
	}

	public POVeiculo getIdVeiculo() {
		return idVeiculo;
	}

	public void setIdVeiculo(POVeiculo idVeiculo) {
		this.idVeiculo = idVeiculo;
	}
	
	

}
