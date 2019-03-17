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
@Table(name = "TB_MODELO")
public class POModelo {

//	+--------------------+-------------------------+------+-----+---------+-------+
//	| Field              | Type                    | Null | Key | Default | Extra |
//	+--------------------+-------------------------+------+-----+---------+-------+
//	| TB_MODELO_ID       | int(11)                 | NO   | PRI | NULL    |       |
//	| TB_MODELO_DESC     | varchar(50)             | NO   |     | NULL    |       |
//	| TB_MODELO_STATUS   | enum('ativo','inativo') | NO   |     | NULL    |       |
//	| TB_TIPO_VEICULO_ID | int(11)                 | NO   | MUL | NULL    |       |
//	| TB_MARCA_ID        | int(11)                 | NO   | MUL | NULL    |       |
//	+--------------------+-------------------------+------+-----+---------+-------+

	@Id
	@GeneratedValue
	@Column(name = "TB_MODELO_ID", nullable = false, length = 11)
	private int idModelo;

	@Column(name = "TB_MODELO_DESC",nullable = false,length = 50)
	private String descModelo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TB_MODELO_STATUS",nullable = false)
	private EnumStatus statusModelo;

	@ManyToOne
	@JoinColumn(name = "TB_TIPO_VEICULO_ID",nullable = false)
	private POTipoVeiculo idTipoV;
	
	@ManyToOne
	@JoinColumn(name = "TB_MARCA_ID",nullable = false)
	private POMarca idMarca;

	public int getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(int idModelo) {
		this.idModelo = idModelo;
	}

	public String getDescModelo() {
		return descModelo;
	}

	public void setDescModelo(String descModelo) {
		this.descModelo = descModelo;
	}

	public EnumStatus getStatusModelo() {
		return statusModelo;
	}

	public void setStatusModelo(EnumStatus statusModelo) {
		this.statusModelo = statusModelo;
	}

	public POTipoVeiculo getIdTipoV() {
		return idTipoV;
	}

	public void setIdTipoV(POTipoVeiculo idTipoV) {
		this.idTipoV = idTipoV;
	}

	public POMarca getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(POMarca idMarca) {
		this.idMarca = idMarca;
	}

	
	
}
