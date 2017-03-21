package com.brenomorais.netbanking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brenomorais.netbanking.model.Lancamento;

public interface Lancamentos extends JpaRepository<Lancamento, Long>{

	//Implmenta consulta de titulos pela descrição como estivesse usando o like em sql
	public List<Lancamento> findByDescricaoContaining(String descricao);	
	
}
