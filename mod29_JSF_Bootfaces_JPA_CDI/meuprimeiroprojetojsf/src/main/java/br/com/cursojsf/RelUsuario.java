package br.com.cursojsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dao.DaoGeneric;
import br.com.entidades.Pessoa;
import br.com.repository.IDaoPessoa;

@ViewScoped
@Named(value = "relUsuario")
public class RelUsuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Date dataInicial;
	
	private Date dataFinal;
	
	private String nome;
	
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	
	@Inject
	private IDaoPessoa iDaoPessoa;
	
	@Inject
	private DaoGeneric<Pessoa> daoGeneric;
	
	public void relPessoa() {
		if (dataInicial == null && dataFinal == null && nome == null) {
			pessoas = daoGeneric.getListEntity(Pessoa.class);
		} else {
			pessoas = iDaoPessoa.relatorioPessoa(nome, dataInicial, dataFinal);
		}
	}
	
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
}
