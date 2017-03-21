package com.brenomorais.netbanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brenomorais.netbanking.model.Usuario;

public interface Usuarios extends JpaRepository<Usuario, Long>{

	//Implmenta consulta de titulos pela descrição como estivesse usando o like em sql

   
}
