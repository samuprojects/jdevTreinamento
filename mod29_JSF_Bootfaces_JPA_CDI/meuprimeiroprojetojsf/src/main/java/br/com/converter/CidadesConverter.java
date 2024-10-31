package br.com.converter;

import java.io.Serializable;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.entidades.Cidades;

@FacesConverter(forClass = Cidades.class, value = "cidadeConverter")
public class CidadesConverter implements Converter, Serializable {

	private static final long serialVersionUID = 7942337638899772351L;

	@Override // Para retornar o objeto inteiro
	public Object getAsObject(FacesContext context, UIComponent component, String codigoCidade) {
		
		if (codigoCidade != null && !codigoCidade.isEmpty()) {
			
			EntityManager entityManager = CDI.current().select(EntityManager.class).get();
			
			Cidades cidade = (Cidades) entityManager.find(Cidades.class, Long.parseLong(codigoCidade));
			
			System.out.println("cidade converter " + cidade);
			
			return cidade;
		} else {
			
			System.out.println("cidade converter vazio 1 ");
			return "";

		}		
		
	}

	@Override // Para retornar apenas o código em String
	public String getAsString(FacesContext context, UIComponent component, Object cidade) {
		
		if (cidade == null  || (cidade.toString() != null && cidade.toString().isEmpty())) {
			System.out.println("cidade converter vazio 2 ");
			return "";
		}
		
		if (cidade instanceof Cidades) {
			System.out.println("cidade converter 3 " + ((Cidades) cidade).getId().toString());
			return ((Cidades) cidade).getId().toString();
		} else {
			System.out.println("cidade converter to string " + cidade.toString());
			return cidade.toString();
		}
		
	}

}
