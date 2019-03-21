package br.com.estacionamento.mvc.model.persistent_object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

@Entity
@Table(name = "TB_CIDADE")
public class POCidade {

//	+------------------+-------------------------+------+-----+---------+----------------+
//	| Field            | Type                    | Null | Key | Default | Extra          |
//	+------------------+-------------------------+------+-----+---------+----------------+
//	| TB_CIDADE_ID     | int(11)                 | NO   | PRI | NULL    | auto_increment |
//	| TB_CIDADE_NOME   | varchar(50)             | NO   |     | NULL    |                |
//	| TB_CIDADE_STATUS | enum('ativo','inativo') | NO   |     | NULL    |                |
//	| TB_ESTADO_ID     | int(11)                 | NO   | MUL | NULL    |                |
//	+------------------+-------------------------+------+-----+---------+----------------+

	@Id
	@GeneratedValue
	@Column(name = "TB_CIDADE_ID", nullable = false, length = 11)
	private int idCidade;

	@Column(name = "TB_CIDADE_NOME", nullable = false, length = 50)
	private String nomeCidade;
	// private EnumStatus

	@Enumerated(EnumType.STRING)
	@Column(name = "TB_CIDADE_STATUS", nullable = false)
	private EnumStatus statusCidade;

	@OneToOne
	@JoinColumn(name = "TB_ESTADO_ID", nullable = false)
	private POEstado idEstado;

	public int getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(int idCidade) {
		this.idCidade = idCidade;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public POEstado getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(POEstado idEstado) {
		this.idEstado = idEstado;
	}

	public EnumStatus getStatusCidade() {
		return statusCidade;
	}

	public void setStatusCidade(EnumStatus statusCidade) {
		this.statusCidade = statusCidade;
	}

	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();

		json.put("idCidade", this.idCidade);
		json.put("nomeCidade", this.nomeCidade);
		json.put("idEstado", this.idEstado.toJSON());
		json.put("statusCidade", this.statusCidade.getStatus());

		return json;
	}

}
