package br.com.cursojsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dao.DaoGeneric;
import br.com.entidades.Lancamento;
import br.com.repository.IDaoLancamento;

@ViewScoped
@Named(value = "relLancamento")
public class RelLancamento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Date dataInicial;
	
	private Date dataFinal;
	
	private String numeroDaNota;
	
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();
	
	@Inject
	private IDaoLancamento daoLancamento;
	
	@Inject
	private DaoGeneric<Lancamento> daoGeneric;
	
	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getNumeroDaNota() {
		return numeroDaNota;
	}

	public void setNumeroDaNota(String numeroDaNota) {
		this.numeroDaNota = numeroDaNota;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}
	
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
	
	public void buscarLancamento() {
		
		if (dataInicial == null && dataFinal == null && numeroDaNota == null) {
			lancamentos = daoGeneric.getListEntity(Lancamento.class);
		} else {
			lancamentos = daoLancamento.relatorioLancamento(numeroDaNota, dataInicial, dataFinal);
		}
		
	}

}
