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
@Table(name = "TB_MARCA")
public class POMarca {

//	+-----------------+-------------------------+------+-----+---------+-------+
//	| Field           | Type                    | Null | Key | Default | Extra |
//	+-----------------+-------------------------+------+-----+---------+-------+
//	| TB_MARCA_ID     | int(11)                 | NO   | PRI | NULL    |       |
//	| TB_MARCA_DESC   | varchar(50)             | NO   |     | NULL    |       |
//	| TB_MARCA_STATUS | enum('ativo','inativo') | NO   |     | NULL    |       |
//	+-----------------+-------------------------+------+-----+---------+-------+

	@Id
	@GeneratedValue
	@Column(name = "TB_MARCA_ID", nullable = false, length = 11)
	private int idMarca;

	@Column(name = "TB_MARCA_DESC", nullable = false, length = 50)
	private String descMarca;


	@Enumerated(EnumType.STRING)
	@Column(name = "TB_MARCA_STATUS", nullable = false)
	private EnumStatus statusMarca;

	public int getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	public String getDescMarca() {
		return descMarca;
	}

	public void setDescMarca(String descMarca) {
		this.descMarca = descMarca;
	}

	public EnumStatus getStatusMarca() {
		return statusMarca;
	}

	public void setStatusMarca(EnumStatus statusMarca) {
		this.statusMarca = statusMarca;
	}
	
}
