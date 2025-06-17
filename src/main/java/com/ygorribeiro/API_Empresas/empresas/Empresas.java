package com.ygorribeiro.API_Empresas.empresas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;

@Entity(name = "Empresas")
@Table(name = "empresas")
@EqualsAndHashCode(of = "id")
public class Empresas {
	
	
	public Empresas(DadosCadastroEmpresas dados) {
		this.status = true;
		this.nome=dados.nome();
		this.cnpj=dados.cnpj();
		this.quantidade=dados.quantidade();
		
	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cnpj;
    private int quantidade;
    private boolean status;
    
    public Empresas() {
    }

   
    public Empresas(long id, String nome, String cnpj, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.quantidade = quantidade;
    }

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

	@Override
    public int hashCode() {
        return Long.hashCode(id);
    }


	public void atualizarinformacoes(@Valid DadosAtualizarEmpresas dados) {
		if(dados.nome() != null){
			this.nome = dados.nome();
		}
		
	}


	public void inativar() {
		this.status = false;
		
	}

	public void reativar() {
		this.status = true;	
		
	}
}