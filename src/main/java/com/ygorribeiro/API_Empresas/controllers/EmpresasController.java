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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ygorribeiro.API_Empresas.empresas.DadosAtualizarEmpresas;
import com.ygorribeiro.API_Empresas.empresas.DadosCadastroEmpresas;
import com.ygorribeiro.API_Empresas.empresas.DadosDetalhamentoEmpresas;
import com.ygorribeiro.API_Empresas.empresas.DadosListagemEmpresas;
import com.ygorribeiro.API_Empresas.empresas.Empresas;
import com.ygorribeiro.API_Empresas.empresas.EmpresasRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/empresas")
public class EmpresasController {
	
	@Autowired
	private EmpresasRepository repository;
	
	@PostMapping
    @Transactional
	public ResponseEntity<DadosDetalhamentoEmpresas> cadastrar (@RequestBody @Valid DadosCadastroEmpresas dados, UriComponentsBuilder uriBuilder) {
		var empresas = new Empresas(dados);
		repository.save(empresas); 
	
		var uri = uriBuilder.path("/empresas/{id}").buildAndExpand(empresas.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoEmpresas(empresas));

		
	}
	
	@GetMapping
	public ResponseEntity<List<DadosListagemEmpresas>> listar (){
	 var lista = repository.findAllByStatusTrue().stream().map(DadosListagemEmpresas::new).toList();
	 
	 return ResponseEntity.ok(lista);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoEmpresas> atualizar (@RequestBody @Valid DadosAtualizarEmpresas dados) {
		var empresas = repository.getReferenceById(dados.id());
		empresas.atualizarinformacoes(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoEmpresas(empresas));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("inativar/{id}")
	@Transactional
	public ResponseEntity<Void> inativar(@PathVariable Long id) {
		var empresas = repository.getReferenceById(id);
		empresas.inativar();
		
		return ResponseEntity.noContent().build();
}
	
	@PutMapping("/reativar/{id}")
	@Transactional
	public ResponseEntity<Void> reativar (@PathVariable Long id) {
	    var empresas = repository.getReferenceById(id);
	    empresas.reativar();
	    
	    return ResponseEntity.noContent().build();
	}
	    
	
	@GetMapping("/buscar")
	public ResponseEntity<?> buscarEmpresas(
	        @RequestParam(required = false) Long id,
	        @RequestParam(required = false) String nome,
	        @RequestParam(required = false) String cnpj) {

	    List<Empresas> resultado;

	    if (id != null) {
	        if (nome != null && cnpj != null) {
	            resultado = repository.findByNomeAndCnpjAndId(nome, cnpj, id);
	        } else if (nome != null) {
	            resultado = repository.findByNomeAndId(nome, id);
	        } else if (cnpj != null) {
	            resultado = repository.findByCnpjAndId(cnpj, id);
	        } else {
	            var empresa = repository.findById(id);
	            return empresa.map(e -> ResponseEntity.ok(new DadosListagemEmpresas(e)))
	                          .orElse(ResponseEntity.notFound().build());
	        }
	    } else if (nome != null && cnpj != null) {
	        resultado = repository.findByNomeAndCnpj(nome, cnpj);
	    } else if (nome != null) {
	        resultado = repository.findByNome(nome);
	    } else if (cnpj != null) {
	        resultado = repository.findByCnpj(cnpj);
	    } else {
	        return ResponseEntity.badRequest().body("e necessario informar pelo menos um parametro: id, nome ou cnpj.");
	    }

	    var listagem = resultado.stream()
	            .map(DadosListagemEmpresas::new)
	            .toList();

	    return ResponseEntity.ok(listagem);
	}

}