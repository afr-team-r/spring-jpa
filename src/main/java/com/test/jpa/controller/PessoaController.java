package com.test.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.test.jpa.dao.PessoaRepository;
import com.test.jpa.model.Pessoa;

@Controller
public class PessoaController {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@RequestMapping("/adicionaPessoa")
	@ResponseBody
	public String adicionaPessoa(@RequestParam(value="nome", required=false) String nome,
							   @RequestParam(value="idade", required=false) Integer idade) {
		
		if(nome != null && idade != null) {
			Pessoa nova = new Pessoa();
			nova.setNome(nome);
			nova.setIdade(idade);
			
			pessoaRepository.save(nova);
			
			return "OK!";
		}
	
		return nome + idade;
	}
	
	@RequestMapping("/listaPessoas")
	@ResponseBody
	public String listaPessoas() {
			
		StringBuffer sb = new StringBuffer();
	
		 for(Pessoa p : pessoaRepository.findAll()) 
			 sb.append(p.getNome() + "<br/>");
		 
		 return sb.toString();		 
	}

}
