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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dto.DadosAtualizarEmpresas;
import dto.DadosCadastroEmpresas;
import dto.DadosDetalhamentoEmpresas;
import dto.DadosListagemEmpresas;
import entity.Empresas;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import repository.EmpresasRepository;
import services.EmpresasService;

@RestController
@RequestMapping("/empresas")
public class EmpresasController {

	@Autowired
	private EmpresasService empresasService;

	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoEmpresas> cadastrar(@RequestBody @Valid DadosCadastroEmpresas dados) {
	Empresas empresasRetorno = empresasService.cadastrar(dados);
	return ResponseEntity.ok().body(new DadosDetalhamentoEmpresas(empresasRetorno));

	}

	@GetMapping
	public ResponseEntity<List<DadosListagemEmpresas>> listar() {
		  var lista = empresasService.listar();
		    return ResponseEntity.ok(lista);
	}

	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoEmpresas> atualizar(@RequestBody @Valid DadosAtualizarEmpresas dados) {
		 var empresaAtualizada = empresasService.atualizar(dados);
		    return ResponseEntity.ok(new DadosDetalhamentoEmpresas(empresaAtualizada));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
	    empresasService.excluir(id);
	    return ResponseEntity.noContent().build();
	}

	@DeleteMapping("inativar/{id}")
	@Transactional
	public ResponseEntity<Void> inativar(@PathVariable Long id) {
	    empresasService.inativar(id);
	    return ResponseEntity.noContent().build();
	}


	@PutMapping("/reativar/{id}")
	@Transactional
	public ResponseEntity<Void> reativar(@PathVariable Long id) {
	    empresasService.reativar(id);
	    return ResponseEntity.noContent().build();
	}


	@GetMapping("/buscar")
	public ResponseEntity<?> buscarEmpresas(@RequestParam(required = false) Long id,
	                                        @RequestParam(required = false) String nome,
	                                        @RequestParam(required = false) String cnpj) {
	    return empresasService.buscarEmpresas(id, nome, cnpj);
	}




}