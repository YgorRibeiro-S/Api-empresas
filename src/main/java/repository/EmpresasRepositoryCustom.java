package repository;

import java.util.List;
import entity.Empresas;

public interface EmpresasRepositoryCustom {
    List<Empresas> consultar(String nome, String cnpj, Long id);
}