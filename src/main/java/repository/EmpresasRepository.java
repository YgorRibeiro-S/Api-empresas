package repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Empresas;

public interface EmpresasRepository extends JpaRepository<Empresas, Long>, EmpresasRepositoryCustom {

	List<Empresas> findAllByStatusTrue();



		
	    
	    
	


}
