package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dto.DadosAtualizarEmpresas;
import dto.DadosCadastroEmpresas;
import dto.DadosListagemEmpresas;
import entity.Empresas;
import jakarta.transaction.Transactional;
import repository.EmpresasRepository;

@Service
public class EmpresasService {

	@Autowired
	private EmpresasRepository repository;
	
	@Transactional
	public Empresas cadastrar(DadosCadastroEmpresas dados) {
		return repository.save(new Empresas(dados));
	}
	
	public List<DadosListagemEmpresas> listar(){
		  return repository.findAllByStatusTrue()
                  .stream()
                  .map(DadosListagemEmpresas::new)
                  .toList();
	}
	
	@Transactional
	public Empresas atualizar(DadosAtualizarEmpresas dados) {
	    var empresa = repository.getReferenceById(dados.id());
	    empresa.atualizarinformacoes(dados);
	    return empresa;
	}
	
	@Transactional
	public void excluir(Long id) {
	    repository.deleteById(id);
	}
	
	@Transactional
	public void inativar(Long id) {
	    var empresa = repository.getReferenceById(id);
	    empresa.inativar();
	}
	
	@Transactional
	public void reativar(Long id) {
	    var empresa = repository.getReferenceById(id);
	    empresa.reativar();
	}
	
	public ResponseEntity<?> buscarEmpresas(Long id, String nome, String cnpj) {
	    if (id == null && nome == null && cnpj == null) {
	        return ResponseEntity.badRequest().body("É necessário informar pelo menos um parâmetro: id, nome ou cnpj.");
	    }

	    var resultado = repository.consultar(nome, cnpj, id);

	    if (resultado.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }

	    var listagem = resultado.stream().map(DadosListagemEmpresas::new).toList();
	    return ResponseEntity.ok(listagem);
	}




}
