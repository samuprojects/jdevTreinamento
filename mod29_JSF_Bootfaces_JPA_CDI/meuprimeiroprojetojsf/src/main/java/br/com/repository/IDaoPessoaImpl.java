package br.com.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import br.com.entidades.Estados;
import br.com.entidades.Pessoa;

@Named
public class IDaoPessoaImpl implements IDaoPessoa, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	EntityManager entityManager;

	@Override
	public Pessoa consultarUsuario(String login, String senha) {
		
		Pessoa pessoa = null;
		
		try {
			
			pessoa = (Pessoa) entityManager.createQuery("select p from Pessoa p where p.login = '" + login + "' and p.senha = '" + senha + "'").getSingleResult();
			
		} catch (NoResultException e) { // Tratamento para usuário com login/senha não localizado
		}		
		
		return pessoa;
	}
	
	@Transactional
	public List<SelectItem> listaEstados() {
	    
	    List<SelectItem> selectItems = new ArrayList<SelectItem>();
	    
	    List<Estados> estados = entityManager.createQuery("from Estados", Estados.class).getResultList();
	    
	    for (Estados estado : estados) {
	        selectItems.add(new SelectItem(estado, estado.getNome()));
	    }
	    
	    return selectItems;
	}

}
