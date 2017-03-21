package com.brenomorais.netbanking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.brenomorais.netbanking.model.Lancamento;
import com.brenomorais.netbanking.repository.LancamentoFilter;
import com.brenomorais.netbanking.repository.Lancamentos;

@Service
public class CadastroLancamentosService {

	@Autowired
	private Lancamentos titulos;

	public void salvar(Lancamento lancamento) {
		try {
			titulos.save(lancamento);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido.");
		}
	}

	public void excluir(Long codigo){		
		titulos.delete(codigo);
	}

	
//	public String receber(Long codigo) {
//		//recupera o titulo com o codigo recebido na requisição
//		
//		Lancamento titulo = titulos.findOne(codigo); 	//Recebi o codigo
//		titulo.setStatus(StatusLancamento.RECEBIDO);	//Atualizei o status
//		titulos.save(titulo);						//salvei o titulo
//		return StatusTitulo.RECEBIDO.getDescricao();
//	}
	
	public List<Lancamento> filtrar(LancamentoFilter filtro){
		
		String descricao = filtro.getDescricao() == null ? "%" : filtro.getDescricao(); //verificação para não passar null na linha abaixo		
		return titulos.findByDescricaoContaining(descricao); //consulta pela descricao dos titulos
			
	}
}
