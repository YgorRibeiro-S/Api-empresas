package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarEmpresas(
		
	    @NotNull
		Long id, 
		@NotBlank
		String nome, 
		 
		int quantidade) {

}
