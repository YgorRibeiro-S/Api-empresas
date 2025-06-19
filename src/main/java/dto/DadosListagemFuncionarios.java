package dto;

import entity.Funcionarios;

public record DadosListagemFuncionarios(Long id, String nome, String cpf, boolean status) {

	public DadosListagemFuncionarios(Funcionarios funcionarios) {
		this(funcionarios.getId(), funcionarios.getNome(), funcionarios.getCpf(), funcionarios.isStatus());
	}
}
