package com.brenomorais.netbanking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brenomorais.netbanking.model.Usuario;

@Controller
@RequestMapping("/inicio")
public class LoginController {

		
	@RequestMapping("/login")
	public String login() {
		return "login";
	}	
	
	@RequestMapping(method = RequestMethod.POST)
	public String logar(Usuario usuario) {
		System.out.println(">>> " + usuario.getNome()+" -Senha >> "+usuario.getSenha());
		
		return "Login";
	}
}

