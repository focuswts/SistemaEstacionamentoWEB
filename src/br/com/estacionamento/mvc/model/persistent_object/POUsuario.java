package br.com.estacionamento.mvc.model.persistent_object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_USUARIO")
public class POUsuario {

//	+-------------------+-------------------------+------+-----+---------+-------+
//	| Field             | Type                    | Null | Key | Default | Extra |
//	+-------------------+-------------------------+------+-----+---------+-------+
//	| TB_USUARIO_ID     | int(11)                 | NO   | PRI | NULL    |       |
//	| TB_USUARIO_NOME   | varchar(10)             | NO   |     | NULL    |       |
//	| TB_USUARIO_SENHA  | varchar(10)             | NO   |     | NULL    |       |
//	| TB_USUARIO_STATUS | enum('ativo','inativo') | NO   |     | NULL    |       |
//	+-------------------+-------------------------+------+-----+---------+-------+

@Id
@GeneratedValue
@Column(name = "TB_USUARIO_ID", nullable = false,length = 11)
private int idUsuario;

@Column(name = "TB_USUARIO_NOME",nullable = false , length = 10)
private String nomeUsuario;

@Column(name = "TB_USUARIO_SENHA", nullable = false , length = 10)
private String senhaUsuario;

	
	
	
}
