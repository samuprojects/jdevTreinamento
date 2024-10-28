package br.com.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.entidades.Lancamento;

@Named
public class IDaoLancamentoImpl implements IDaoLancamento, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;
	
	@Override
	public List<Lancamento> consultarLimit10(Long codUser) {
		List<Lancamento> lista = null;
		
		lista = entityManager.createQuery(" from Lancamento where usuario.id = " + codUser + " order by id desc ").setMaxResults(10).getResultList();
		
		return lista;
	}
	
	@Override
	public List<Lancamento> consultar(Long codUser) {
		List<Lancamento> lista = null;
		
		lista = entityManager.createQuery(" from Lancamento where usuario.id = " + codUser).getResultList();
		
		return lista;
	}

	@Override
	public List<Lancamento> relatorioLancamento(String numeroNota, Date dataInicial, Date dataFinal) {
		
		List<Lancamento> lancamentos = new ArrayList<Lancamento>();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select l from Lancamento l");
		
		if (dataInicial == null && dataFinal == null && numeroNota != null && !numeroNota.isEmpty()) {
			sql.append(" where l.numeroNotaFiscal = '").append(numeroNota.trim()).append("'");
		}
		
		lancamentos = entityManager.createQuery(sql.toString()).getResultList();
		
		return lancamentos;
	}

}
