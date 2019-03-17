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
	
	@ManyToOne
	@JoinColumn(name = "TB_TABELA_VAGA_ID", nullable = false)
	private POVaga idVaga;
	
	@ManyToOne
	@JoinColumn(name = "TB_VEICULO_ID", nullable = false)
	private POVeiculo idVeiculo;

}
