package br.com.estacionamento.mvc.model.persistent_object.enums;

public enum EnumCor {

	PRETO("PRETO"),
	BRANCO("BRANCO"),
	PRATA("PRATA"),
	VERMELHO("VERMELHO");
	
	private String cor;
	
	EnumCor(String cor){
		this.cor = cor;
	}
	
	public String getCor() {
		return this.cor;
	}
	
}
