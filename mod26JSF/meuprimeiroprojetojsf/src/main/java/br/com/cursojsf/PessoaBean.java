package br.com.cursojsf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlCommandButton;

@ViewScoped
@ManagedBean(name = "pessoaBean")
public class PessoaBean {

	private String nome;
	
	private String senha;
	
	private String texto;
	
	private HtmlCommandButton commandButton; // como nossa classe possui um Bean gerenciado, de qualquer tipo, nesse caso o ViewScoped, podemos fazer uma ponte (binding) e controlar um componente do front end, nesse exemplo o botão
	// Não é muito recomendado fazer isso pois, entre outras coisas, ocorre o acoplamento do componente da interface à lógica do bean.
	// Isso pode dificultar a manutenção e os testes, haverá necessidade de garantir que o bean sempre tenha acesso ao componente.
	
	private List<String> nomes = new ArrayList<>() ;

	public String addNome() {
		nomes.add(nome);
		
		if (nomes.size() > 3) {
			commandButton.setDisabled(true); // por exemplo se lançar mais de três nomes o botão de lançamento é desabilitado.
			return "paginanavegada?faces-redirect=true"; // condição para navegação dinâmica -- ?faces-redirect=true exibe o redirecionamento da página na URL
		}
		
		return ""; // null ou vazio permanece na mesma página -> outcome
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setCommandButton(HtmlCommandButton commandButton) {
		this.commandButton = commandButton;
	}
	
	public HtmlCommandButton getCommandButton() {
		return commandButton;
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
