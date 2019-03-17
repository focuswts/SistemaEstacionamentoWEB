package br.com.estacionamento.mvc.model.persistent_object.enums;

public enum EnumTelefone {

	RESIDENCIAL("residencial"),
	COMERCIAL("comercial"),
	CELULAR("celular");
	
	private String tipoTel;
	
	EnumTelefone(String tipoTel){
		this.tipoTel = tipoTel;
	}
	
	public String getTipoTel(){
		return this.tipoTel;
	}
	
}
