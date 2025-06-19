package dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroFuncionarios(
 
  @NotBlank
  String nome,
  String cpf,
  String residencia
  
   		) {

}
