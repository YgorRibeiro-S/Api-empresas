package com.ygorribeiro.API_Empresas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ygorribeiro.API_Empresas.funcionarios.DadosCadastroFuncionarios;
import com.ygorribeiro.API_Empresas.funcionarios.DadosDetalhamentoFuncionarios;
import com.ygorribeiro.API_Empresas.funcionarios.DadosListagemFuncionarios;
import com.ygorribeiro.API_Empresas.funcionarios.Funcionarios;
import com.ygorribeiro.API_Empresas.funcionarios.FuncionariosRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionarios")
public class FuncionariosController {
	
	@Autowired
	private FuncionariosRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoFuncionarios> cadastrar (@RequestBody @Valid DadosCadastroFuncionarios dados, UriComponentsBuilder uriBuilder) {
		var funcionarios = new Funcionarios(dados);
		repository.save(funcionarios); 
	
		var uri = uriBuilder.path("/empresas/{id}").buildAndExpand(funcionarios.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoFuncionarios(funcionarios));

		
	}
	
	@GetMapping
	public List<DadosListagemFuncionarios> listar(){
		return repository.findAll().stream().map(DadosListagemFuncionarios::new).toList();	
	
	}
	
	@DeleteMapping("inativar/{id}")
	@Transactional
	public ResponseEntity<Void> inativar(@PathVariable Long id) {
		var funcionarios = repository.getReferenceById(id);
		funcionarios.inativar();
		
		return ResponseEntity.noContent().build();
}
	
	@PutMapping("/reativar/{id}")
	@Transactional
	public ResponseEntity<Void> reativar (@PathVariable Long id) {
	    var funcionarios = repository.getReferenceById(id);
	    funcionarios.reativar();
	    
	    return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

}
