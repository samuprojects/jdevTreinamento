package br.com.cursojsf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ApplicationScoped // agora independente de fechar o navegador, ou usuário encerrar, a informação é compartilhada com todos na aplicação e mantido em tela.
@ManagedBean(name = "pessoaBean")
public class PessoaBean {

	private String nome;
	
	private List<String> nomes = new ArrayList<>() ;

	public String addNome() {
		nomes.add(nome);
		return "";
	}
	
	public void setNomes(List<String> nomes) {
		this.nomes = nomes;
	}
	
	public List<String> getNomes() {
		return nomes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
