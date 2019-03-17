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
import br.com.estacionamento.mvc.model.persistent_object.enums.EnumTelefone;

@Entity
@Table(name = "TB_TELEFONE")
public class POTelefone {

//	+----------------------+-------------------------------------------+------+-----+---------+----------------+
//	| Field                | Type                                      | Null | Key | Default | Extra          |
//	+----------------------+-------------------------------------------+------+-----+---------+----------------+
//	| TB_TELEFONE_ID       | int(11)                                   | NO   | PRI | NULL    | auto_increment |
//	| TB_TELEFONE_NUM      | varchar(11)                               | NO   |     | NULL    |                |
//	| TB_TELEFONE_TIPO_TEL | enum('residencial','comercial','celular') | NO   |     | NULL    |                |
//	| TB_TELEFONE_STATUS   | enum('ativo','inativo')                   | NO   |     | NULL    |                |
//	| TB_MENSALISTA_ID     | int(11)                                   | NO   | MUL | NULL    |                |
//	+----------------------+-------------------------------------------+------+-----+---------+----------------+

	@Id
	@GeneratedValue
	@Column(name = "TB_TELEFONE_ID", nullable = false, length = 11)
	private int idTelefone;

	@Column(name = "TB_TELEFONE_NUM", nullable = false, length = 11)
	private String numTelefone;

	@Enumerated(EnumType.STRING)
	@Column(name = "TB_TELEFONE_TIPO_TEL", nullable = false)
	private EnumTelefone tipoTel;

	@Enumerated(EnumType.STRING)
	@Column(name = "TB_MENSALISTA_STATUS")
	private EnumStatus statusTelefone;

	@ManyToOne
	@JoinColumn(name = "TB_MENSALISTA_ID", nullable = false)
	private POMensalista idMensalista;

	public int getIdTelefone() {
		return idTelefone;
	}

	public void setIdTelefone(int idTelefone) {
		this.idTelefone = idTelefone;
	}

	public String getNumTelefone() {
		return numTelefone;
	}

	public void setNumTelefone(String numTelefone) {
		this.numTelefone = numTelefone;
	}

	public EnumTelefone getTipoTel() {
		return tipoTel;
	}

	public void setTipoTel(EnumTelefone tipoTel) {
		this.tipoTel = tipoTel;
	}

	public EnumStatus getStatusTelefone() {
		return statusTelefone;
	}

	public void setStatusTelefone(EnumStatus statusTelefone) {
		this.statusTelefone = statusTelefone;
	}

	public POMensalista getIdMensalista() {
		return idMensalista;
	}

	public void setIdMensalista(POMensalista idMensalista) {
		this.idMensalista = idMensalista;
	}

}
