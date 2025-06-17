package com.ygorribeiro.API_Empresas.funcionarios;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroFuncionarios(
 
  @NotBlank
  String nome,
  String cpf,
  String residencia
  
   		) {

}
