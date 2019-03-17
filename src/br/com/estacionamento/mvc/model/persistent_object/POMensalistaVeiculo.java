package br.com.estacionamento.mvc.model.persistent_object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

@Entity
@Table(name = "TB_MENSALISTA_VEICULO")
public class POMensalistaVeiculo {

//	+------------------------------------+-------------------------+------+-----+---------+-------+
//	| Field                              | Type                    | Null | Key | Default | Extra |
//	+------------------------------------+-------------------------+------+-----+---------+-------+
//	| TB_MENSALISTA_VEICULO_ID           | int(11)                 | NO   | PRI | NULL    |       |
//	| TB_MENSALISTA_VEICULO_PROPRIETARIO | tinyint(1)              | NO   |     | NULL    |       |
//	| TB_MENSALISTA_ID                   | int(11)                 | NO   | MUL | NULL    |       |
//	| TB_VEICULO_ID                      | int(11)                 | NO   | MUL | NULL    |       |
//	| TB_MENSALISTA_VEICULO_STATUS       | enum('ativo','inativo') | NO   |     | NULL    |       |
//	+------------------------------------+-------------------------+------+-----+---------+-------+

	@Id
	@GeneratedValue
	@Column(name = "TB_MENSALISTA_VEICULO_ID", nullable = false, length = 11)
	private int idmensalistaV;
	
	@Column(name = "TB_MENSALISTA_VEICULO_PROPRIETARIO",nullable = false,length = 1)
	private int proprietarioV;
	
	@ManyToOne
	@JoinColumn(name = "TB_MENSALISTA_ID",nullable = false)
	private POMensalista idMensalista;
	
	@ManyToOne
	@JoinColumn(name = "TB_VEICULO_ID",nullable = false)
	private POVeiculo idVeiculo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TB_MENSALISTA_VEICULO_STATUS")
	private EnumStatus statusMensalistaV;

	public int getIdmensalistaV() {
		return idmensalistaV;
	}

	public void setIdmensalistaV(int idmensalistaV) {
		this.idmensalistaV = idmensalistaV;
	}

	public int getProprietarioV() {
		return proprietarioV;
	}

	public void setProprietarioV(int proprietarioV) {
		this.proprietarioV = proprietarioV;
	}

	public POMensalista getIdMensalista() {
		return idMensalista;
	}

	public void setIdMensalista(POMensalista idMensalista) {
		this.idMensalista = idMensalista;
	}

	public POVeiculo getIdVeiculo() {
		return idVeiculo;
	}

	public void setIdVeiculo(POVeiculo idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	public EnumStatus getStatusMensalistaV() {
		return statusMensalistaV;
	}

	public void setStatusMensalistaV(EnumStatus statusMensalistaV) {
		this.statusMensalistaV = statusMensalistaV;
	}
	
	
	

}
