package com.brenomorais.netbanking.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.brenomorais.netbanking.model.Lancamento;
import com.brenomorais.netbanking.model.StatusLancamento;
import com.brenomorais.netbanking.repository.LancamentoFilter;
import com.brenomorais.netbanking.service.CadastroLancamentosService;

@Controller
@RequestMapping("/lancamentos")
public class LancamentoController {

	private static final String CADASTRO_VIEW = "CadastroLancamento";

	
	//@Autowired
	//private Titulos titulos;
	
	@Autowired
	private CadastroLancamentosService cadastroLancamentosService;
	
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Lancamento());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Lancamento lancamento, Errors errors, RedirectAttributes attributes) {
	
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		try{
		
		cadastroLancamentosService.salvar(lancamento);
			
		attributes.addFlashAttribute("mensagem", "Lançamento salvo com sucesso!");		
		//retornando a pagina em branco apos dados serem salvo
		return "redirect:/lancamentos/novo";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataLancamento", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	}
	

	/*
	 * Retorna a pagina com todos os titulos
	 * 
	 * @RequestMapping("/titulos") no método pesquisar. Ou seja, se digitar no
	 * browser: localhost:8080/titulos irá chamar o método pesquisar() em
	 * TituloController.
	 */
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") LancamentoFilter filtro) {	
		
		//Lista de titulos que fica disponivel na view de pesquisa titulos		
		//List<Titulo> todosTitulos = titulos.findAll(); lista todos os títulos
		
		List<Lancamento> todosLancamentos = cadastroLancamentosService.filtrar(filtro);
		
		ModelAndView mv = new ModelAndView("PesquisaLancamento");
		mv.addObject("lancamentos",todosLancamentos);
		return mv;
	}	

	@ModelAttribute("todosStatusLancamento")
	public List<StatusLancamento> todosStatusLancamento() {
		return Arrays.asList(StatusLancamento.values());
	}
	
	//Não pode ter dois requestmapping igual
	//Recebendo codigo do titulo para edicao
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Lancamento lancamento){
		
		//Titulo titulo = titulos.findOne(codigo);		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(lancamento);
		return mv;
		
	}	
	
	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo,  RedirectAttributes attributes){
		
		cadastroLancamentosService.excluir(codigo);
		
		attributes.addFlashAttribute("mensagem", "Lançamento excluído com sucesso!");	
		return "redirect:/lancamentos";		
	}
	
	//Recebendo uma requisição via put e retornei, e atualiza o status do serviço, 
	//atualização e feita na classe de serviço
//	@RequestMapping(value= "/{codigo}/receber", method = RequestMethod.PUT)
//	public @ResponseBody String receber(@PathVariable Long codigo){		
//		System.out.println(" codigoLancamentoQuitação >>> "+codigo);
//		return cadastroLancamentosService.receber(codigo);
//	}

}
