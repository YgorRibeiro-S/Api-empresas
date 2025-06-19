package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import dto.DadosCadastroFuncionarios;
import dto.DadosListagemFuncionarios;
import entity.Funcionarios;
import jakarta.transaction.Transactional;
import repository.FuncionariosRepository;
import org.springframework.stereotype.Service;

@Service
public class FuncionariosService {
	
	 @Autowired
	    private FuncionariosRepository repository;

	    @Transactional
	    public Funcionarios cadastrar(DadosCadastroFuncionarios dados) {
	        var funcionario = new Funcionarios(dados);
	        repository.save(funcionario);
	        return funcionario;
	    }
	    
	    public List<DadosListagemFuncionarios> listar() {
	        return repository.findAll().stream().map(DadosListagemFuncionarios::new).toList();
	    }
	    
	    @Transactional
	    public void inativar(Long id) {
	        var funcionario = repository.getReferenceById(id);
	        funcionario.inativar();
	    }
	    
	    @Transactional
	    public void reativar(Long id) {
	        var funcionario = repository.getReferenceById(id);
	        funcionario.reativar();
	    }
	    
	    @Transactional
	    public void excluir(Long id) {
	        repository.deleteById(id);
	    }




}
