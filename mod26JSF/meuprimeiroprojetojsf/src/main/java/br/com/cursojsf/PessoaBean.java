package br.com.cursojsf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped // agora enquanto a tela estiver aberta será possível lançar dados na lista mantendo as informações em tela, em caso de redirecionamento os dados são perdidos
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
