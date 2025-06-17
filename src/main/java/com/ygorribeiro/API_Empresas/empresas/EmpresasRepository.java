package com.ygorribeiro.API_Empresas.empresas;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresasRepository extends JpaRepository<Empresas, Long> {

	List<Empresas> findByNomeAndCnpjAndId(String nome, String cnpj, Long id);

    List<Empresas> findByNomeAndId(String nome, Long id);

    List<Empresas> findByCnpjAndId(String cnpj, Long id);

    List<Empresas> findByNomeAndCnpj(String nome, String cnpj);

    List<Empresas> findByNome(String nome);

    List<Empresas> findByCnpj(String cnpj);

    Optional<Empresas> findById(Long id);

    List<Empresas> findAllByStatusTrue();



		
	    
	    
	


}
