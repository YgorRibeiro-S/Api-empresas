package com.ygorribeiro.API_Empresas.empresas;

public record DadosDetalhamentoEmpresas(Long id, String nome, String cnpj, int quantidade) {

	public DadosDetalhamentoEmpresas(Empresas empresas) {
		this(empresas.getId(), empresas.getCnpj(), empresas.getNome(), empresas.getQuantidade());
	}


}
