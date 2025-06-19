package controllers;

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

import dto.DadosCadastroFuncionarios;
import dto.DadosDetalhamentoFuncionarios;
import dto.DadosListagemFuncionarios;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import services.FuncionariosService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionariosController {
	
	@Autowired
	private FuncionariosService funcionariosService;

	
	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoFuncionarios> cadastrar (@RequestBody @Valid DadosCadastroFuncionarios dados, UriComponentsBuilder uriBuilder) {
	    var funcionario = funcionariosService.cadastrar(dados);
	    var uri = uriBuilder.path("/empresas/{id}").buildAndExpand(funcionario.getId()).toUri();
	    
	    return ResponseEntity.created(uri).body(new DadosDetalhamentoFuncionarios(funcionario));
	}

	
	@GetMapping
	public List<DadosListagemFuncionarios> listar(){
	    return funcionariosService.listar();
	}

	@DeleteMapping("inativar/{id}")
	@Transactional
	public ResponseEntity<Void> inativar(@PathVariable Long id) {
	    funcionariosService.inativar(id);
	    return ResponseEntity.noContent().build();
	}

	
	@PutMapping("/reativar/{id}")
	@Transactional
	public ResponseEntity<Void> reativar (@PathVariable Long id) {
	    funcionariosService.reativar(id);
	    return ResponseEntity.noContent().build();
	}

	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
	    funcionariosService.excluir(id);
	    return ResponseEntity.noContent().build();
	}


}
