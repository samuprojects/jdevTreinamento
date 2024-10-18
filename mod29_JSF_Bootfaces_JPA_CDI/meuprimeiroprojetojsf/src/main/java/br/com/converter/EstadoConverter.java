package br.com.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.entidades.Estados;
import br.com.jpautil.JPAUtil;

@SuppressWarnings("serial")
@FacesConverter(forClass = Estados.class)
public class EstadoConverter implements Converter, Serializable {

	@Override // Para retornar o objeto inteiro
	public Object getAsObject(FacesContext context, UIComponent component, String codigoEstado) {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		Estados estados = (Estados) entityManager.find(Estados.class, Long.parseLong(codigoEstado));
		
		return estados;
	}

	@Override // Para retornar apenas o código em String
	public String getAsString(FacesContext context, UIComponent component, Object estado) {
		
		return ((Estados) estado).getId().toString();
	}

}
