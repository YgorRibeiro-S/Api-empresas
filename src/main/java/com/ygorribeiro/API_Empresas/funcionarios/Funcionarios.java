package com.ygorribeiro.API_Empresas.funcionarios;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;

@Entity(name = "Funcionarios")
@Table(name = "funcionarios")
@EqualsAndHashCode(of = "id")
public class Funcionarios {
	
	
	public Funcionarios(DadosCadastroFuncionarios dados) {
		this.nome = dados.nome();
	    this.cpf = dados.cpf();
	    this.residencia = dados.residencia();
	    this.status = true;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String cpf;
	private String residencia;
	private boolean status;
	
	public Funcionarios() {}
	
	public Funcionarios(Long id, String nome, String cpf, String residencia) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.residencia = residencia;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getResidencia() {
		return residencia;
	}

	public void setResidencia(String residencia) {
		this.residencia = residencia;
	}
	
	public boolean isStatus() {
	    return status;
	}

	public void setStatus(boolean status) {
	    this.status = status;
	}
	
	@Override
    public int hashCode() {
        return Long.hashCode(id);
    }
	

	public void inativar() {
		this.status = false;
		
	}
	
	public void reativar() {
		this.status = true;
	}
	
	
}
	
	


