package br.com.repository;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import br.com.entidades.Estados;
import br.com.entidades.Lancamento;
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

	@Override
	public List<Pessoa> relatorioPessoa(String nome, Date dataInicial, Date dataFinal) {
		List<Pessoa> lancamentos = new ArrayList<Pessoa>();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select l from Pessoa l");
		
		if (dataInicial == null && dataFinal == null && nome != null && !nome.isEmpty()) {
			sql.append(" where upper (l.nome) like '%").append(nome.trim().toUpperCase()).append("%'");
		} else if (nome == null || (nome != null && nome.isEmpty()) && dataInicial != null && dataFinal == null) {
			
			String dataInicialString = new SimpleDateFormat("yyyy-MM-dd").format(dataInicial);
			sql.append(" where l.dataNascimento >= '").append(dataInicialString).append("'");
			
		} else if (nome == null || (nome != null && nome.isEmpty()) && dataInicial == null && dataFinal != null) {
			
			String dataFinalString = new SimpleDateFormat("yyyy-MM-dd").format(dataFinal);
			sql.append(" where l.dataNascimento <= '").append(dataFinalString).append("'");
			
		} else if (nome == null || (nome != null && nome.isEmpty()) && dataInicial != null & dataFinal != null) {
			
			String dataInicialString = new SimpleDateFormat("yyyy-MM-dd").format(dataInicial);
			String dataFinalString = new SimpleDateFormat("yyyy-MM-dd").format(dataFinal);
			
			sql.append(" where l.dataNascimento >= '").append(dataInicialString).append("'");
			sql.append(" and l.dataNascimento <= '").append(dataFinalString).append("'");
			
		} else if (nome != null && !nome.isEmpty() && dataInicial != null & dataFinal != null) {
			
			String dataInicialString = new SimpleDateFormat("yyyy-MM-dd").format(dataInicial);
			String dataFinalString = new SimpleDateFormat("yyyy-MM-dd").format(dataFinal);
			
			sql.append(" where l.dataNascimento >= '").append(dataInicialString).append("'");
			sql.append(" and l.dataNascimento <= '").append(dataFinalString).append("'");
			sql.append(" and upper (l.nome) like '%").append(nome.trim().toUpperCase()).append("%'");
		}
		
		lancamentos = entityManager.createQuery(sql.toString()).getResultList();
		
		return lancamentos;
	}

}
