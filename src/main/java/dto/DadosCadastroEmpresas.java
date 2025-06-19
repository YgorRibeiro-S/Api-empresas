package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroEmpresas(
		
		
		
		@NotBlank
		String nome, 
		String cnpj, 
		@NotNull
		int quantidade) {

}
