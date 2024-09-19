package br.com.cursojsf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped // agora cada sessão do usuário, ou navegador, mantém os dados em tela mesmo em caso de redirecionamento de página. Perde a informação apenas se deslogar ou fechar o navegador.
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
