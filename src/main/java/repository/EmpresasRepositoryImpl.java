package repository;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.Root;
import entity.Empresas;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;

public class EmpresasRepositoryImpl {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Empresas> consultar(String nome, String cnpj, Long id){
		CriteriaBuilder Builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Empresas> query = Builder.createQuery(Empresas.class);
		Root<Empresas> root = query.from(Empresas.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(nome != null && !nome.isEmpty()) {
			predicates.add(Builder.equal(root.get("nome"), nome));
		}
		
		if(cnpj != null && !cnpj.isEmpty()) {
			predicates.add(Builder.equal(root.get("cnpj"), cnpj));
		}
		
		if(id != null) {
			predicates.add(Builder.equal(root.get("id"), id));
		}
		
		if(!predicates.isEmpty()) {
			query.where(Builder.and(predicates.toArray(new Predicate[0])));
		}
		
		return entityManager.createQuery(query).getResultList();
		
		
	}
}
