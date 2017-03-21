package com.brenomorais.netbanking.model;

public enum StatusLancamento {

	SAQUE("Saque"),
	DEPOSITO("Deposito");
	
	
	private String descricao;
	
	StatusLancamento(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
	
}
