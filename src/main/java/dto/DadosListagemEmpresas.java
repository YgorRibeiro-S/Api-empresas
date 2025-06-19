package dto;

import entity.Empresas;

public record DadosListagemEmpresas(Long id, String nome, int quantidade, String cnpj) {

	
	public DadosListagemEmpresas(Empresas empresas) {
		this(empresas.getId(), empresas.getNome(), empresas.getQuantidade(), empresas.getCnpj());
	}
}
