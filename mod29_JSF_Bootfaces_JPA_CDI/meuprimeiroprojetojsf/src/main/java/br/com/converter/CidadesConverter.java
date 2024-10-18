package br.com.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.entidades.Cidades;
import br.com.jpautil.JPAUtil;

@SuppressWarnings("serial")
@FacesConverter(forClass = Cidades.class)
public class CidadesConverter implements Converter, Serializable {

	@Override // Para retornar o objeto inteiro
	public Object getAsObject(FacesContext context, UIComponent component, String codigoCidade) {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		Cidades cidade = (Cidades) entityManager.find(Cidades.class, Long.parseLong(codigoCidade));
		
		return cidade;
	}

	@Override // Para retornar apenas o código em String
	public String getAsString(FacesContext context, UIComponent component, Object cidade) {
		
		return ((Cidades) cidade).getId().toString();
	}

}
