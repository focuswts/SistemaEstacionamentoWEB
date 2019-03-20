package br.com.estacionamento.mvc.model.persistent_object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

@Entity
@Table(name = "TB_TIPO_VEICULO")
public class POTipoVeiculo {

//	+------------------------+-------------------------+------+-----+---------+-------+
//	| Field                  | Type                    | Null | Key | Default | Extra |
//	+------------------------+-------------------------+------+-----+---------+-------+
//	| TB_TIPO_VEICULO_ID     | int(11)                 | NO   | PRI | NULL    |       |
//	| TB_TIPO_VEICULO_DESC   | varchar(50)             | NO   |     | NULL    |       |
//	| TB_TIPO_VEICULO_STATUS | enum('ativo','inativo') | NO   |     | NULL    |       |
//	+------------------------+-------------------------+------+-----+---------+-------+

	@Id
	@GeneratedValue
	@Column(name = "TB_TIPO_VEICULO_ID", nullable = false, length = 11)
	private int idTipoV;

	@Column(name = "TB_TIPO_VEICULO_DESC", nullable = false, length = 50)
	private String descTipoV;

	@Enumerated(EnumType.STRING)
	@Column(name = "TB_TIPO_VEICULO_STATUS", nullable = false)
	private EnumStatus statusTipoV;

	public int getIdTipoV() {
		return idTipoV;
	}

	public void setIdTipoV(int idTipoV) {
		this.idTipoV = idTipoV;
	}

	public String getDescTipoV() {
		return descTipoV;
	}

	public void setDescTipoV(String descTipoV) {
		this.descTipoV = descTipoV;
	}

	public EnumStatus getStatusTipoV() {
		return statusTipoV;
	}

	public void setStatusTipoV(EnumStatus statusTipoV) {
		this.statusTipoV = statusTipoV;
	}

	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("idTipoV", this.idTipoV);
		json.put("descTipoV", this.descTipoV);
		json.put("statusTipoV", this.statusTipoV.getStatus());

		return json;
	}

}
