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
@Table(name = "TB_ENDERECO")
public class POEndereco {

//	+------------------------+-------------------------+------+-----+---------+-------+
//	| Field                  | Type                    | Null | Key | Default | Extra |
//	+------------------------+-------------------------+------+-----+---------+-------+
//	| TB_ENDERECO_ID         | int(11)                 | NO   | PRI | NULL    |       |
//	| TB_ENDERECO_CEP        | varchar(8)              | NO   |     | NULL    |       |
//	| TB_ENDERECO_LOGRADOURO | varchar(100)            | NO   |     | NULL    |       |
//	| TB_ENDERECO_NUM        | varchar(10)             | NO   |     | NULL    |       |
//	| TB_ENDERECO_COMP       | varchar(100)            | YES  |     | NULL    |       |
//	| TB_ENDERECO_STATUS     | enum('ativo','inativo') | NO   |     | NULL    |       |
//	| TB_CIDADE_ID           | int(11)                 | NO   | MUL | NULL    |       |
//	| TB_MENSALISTA_ID       | int(11)                 | NO   | MUL | NULL    |       |
//	+------------------------+-------------------------+------+-----+---------+-------+

	@Id
	@GeneratedValue
	@Column(name = "TB_ENDERECO_ID", nullable = false, length = 11)
	private int idEndereco;

	@Column(name = "TB_ENDERECO_CEP", nullable = false, length = 8)
	private String cep;

	@Column(name = "TB_ENDERECO_LOGRADOURO", nullable = false, length = 100)
	private String logradouro;

	@Column(name = "TB_ENDERECO_NUM", nullable = false, length = 10)
	private String numEndereco;

	@Column(name = "TB_ENDERECO_COMP", nullable = true, length = 100)
	private String complemento;

	@Enumerated(EnumType.STRING)
	@Column(name = "TB_ENDERECO_STATUS", nullable = false)
	private EnumStatus statusEndereco;

	@ManyToOne
	@JoinColumn(name = "TB_CIDADE_ID", nullable = false)
	private POCidade idCidade;

	@ManyToOne
	@JoinColumn(name = "TB_MENSALISTA_ID", nullable = false)
	private POMensalista idMensalista;

	public int getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumEndereco() {
		return numEndereco;
	}

	public void setNumEndereco(String numEndereco) {
		this.numEndereco = numEndereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public EnumStatus getStatusEndereco() {
		return statusEndereco;
	}

	public void setStatusEndereco(EnumStatus statusEndereco) {
		this.statusEndereco = statusEndereco;
	}

	public POCidade getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(POCidade idCidade) {
		this.idCidade = idCidade;
	}

	public POMensalista getIdMensalista() {
		return idMensalista;
	}

	public void setIdMensalista(POMensalista idMensalista) {
		this.idMensalista = idMensalista;
	}

}
