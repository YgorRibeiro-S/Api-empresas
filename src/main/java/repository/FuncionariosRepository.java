package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Funcionarios;

public interface FuncionariosRepository extends JpaRepository<Funcionarios, Long> {

}
