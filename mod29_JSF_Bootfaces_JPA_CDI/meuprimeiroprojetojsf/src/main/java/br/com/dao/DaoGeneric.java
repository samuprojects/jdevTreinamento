package br.com.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.jpautil.JPAUtil;

@Named
public class DaoGeneric<E> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;
	
	@Inject
	private JPAUtil jpaUtil;

	
	public void salvar(E entidade) {
		
		entityManager.persist(entidade);
		
	}
	
	public E merge(E entidade) {
		
		E retorno = entityManager.merge(entidade);
		
		return retorno;
	}
	
	public void delete(E entidade) {
		
		entityManager.remove(entidade);
		
	}
	
	public void deletePorId(E entidade) {
		
		Object id = jpaUtil.getPrimaryKey(entidade);
		entityManager.createQuery("delete from " + entidade.getClass().getCanonicalName() + " where id = " + id).executeUpdate();
		
	}
	
	public List<E> getListEntity(Class<E> entidade) {
		
		List<E> retorno = entityManager.createQuery("from " + entidade.getName()).getResultList();
		
		return retorno;
	}
	
	public List<E> getListEntityLimit10(Class<E> entidade) {
		
		List<E> retorno = entityManager.createQuery("from " + entidade.getName() + " order by id desc ").setMaxResults(10).getResultList();
		
		return retorno;
	}
	
	public E consultar(Class<E> entidade, String codigo) {
		
		E objeto = (E) entityManager.find(entidade, Long.parseLong(codigo));
		
		return objeto;
	}
}
