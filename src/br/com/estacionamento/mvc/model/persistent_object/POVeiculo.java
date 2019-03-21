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

import org.json.JSONException;
import org.json.JSONObject;

import br.com.estacionamento.mvc.model.persistent_object.enums.EnumCor;
import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

@Entity
@Table(name = "TB_VEICULO")
public class POVeiculo {

//	+-------------------+-------------------------------------------+------+-----+---------+-------+
//	| Field             | Type                                      | Null | Key | Default | Extra |
//	+-------------------+-------------------------------------------+------+-----+---------+-------+
//	| TB_VEICULO_ID     | int(11)                                   | NO   | PRI | NULL    |       |
//	| TB_VEICULO_PLACA  | varchar(50)                               | NO   |     | NULL    |       |
//	| TB_VEICULO_COR    | enum('preto','branco','prata','vermelho') | NO   |     | NULL    |       |
//	| TB_VEICULO_STATUS | enum('ativo','inativo')                   | NO   |     | NULL    |       |
//	| TB_MODELO_ID      | int(11)                                   | NO   | MUL | NULL    |       |
//	+-------------------+-------------------------------------------+------+-----+---------+-------+

	@Id
	@GeneratedValue
	@Column(name = "TB_VEICULO_ID", nullable = false, length = 11)
	private int idVeiculo;

	@Column(name = "TB_VEICULO_PLACA", nullable = false, length = 50)
	private String placaVeiculo;

	@Enumerated(EnumType.STRING)
	@Column(name = "TB_VEICULO_COR", nullable = false)
	private EnumCor corVeiculo;
	
	@ManyToOne
	@JoinColumn(name = "TB_MODELO_ID",nullable = false)
	private POModelo idModelo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TB_VEICULO_STATUS", nullable = false)
	private EnumStatus statusVeiculo;
	
	

	public int getIdVeiculo() {
		return idVeiculo;
	}

	public void setIdVeiculo(int idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	public String getPlacaVeiculo() {
		return placaVeiculo;
	}

	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}

	public EnumCor getCorVeiculo() {
		return corVeiculo;
	}

	public void setCorVeiculo(EnumCor corVeiculo) {
		this.corVeiculo = corVeiculo;
	}

	public EnumStatus getStatusVeiculo() {
		return statusVeiculo;
	}

	public void setStatusVeiculo(EnumStatus statusVeiculo) {
		this.statusVeiculo = statusVeiculo;
	}

	public POModelo getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(POModelo idModelo) {
		this.idModelo = idModelo;
	}

	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("idVeiculo", this.idVeiculo);
		json.put("placaVeiculo", this.placaVeiculo);
		json.put("corVeiculo",this.corVeiculo.getCor());
		json.put("idModelo", this.idModelo.toJSON());
		json.put("statusVeiculo", this.statusVeiculo.getStatus());

		return json;
	}
	
}
