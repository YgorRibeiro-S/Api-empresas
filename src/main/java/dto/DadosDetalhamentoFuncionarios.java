package dto;

import entity.Funcionarios;

public record DadosDetalhamentoFuncionarios( long id,
 String nome,
 String cpf,
 String residencia) {
	
	public DadosDetalhamentoFuncionarios(Funcionarios funcionarios) {
		this(funcionarios.getId(), funcionarios.getCpf(), funcionarios.getNome(), funcionarios.getResidencia());
	}

}
