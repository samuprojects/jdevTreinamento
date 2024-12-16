package br.com.project.util.all;

import org.springframework.stereotype.Component;

@Component
public abstract class BeanViewAbstract implements ActionViewPadrao {

	private static final long serialVersionUID = 1L;

	@Override
	public void limparLista() throws Exception {
		
	}

	@Override
	public String save() throws Exception {
		return null;
	}

	@Override
	public void saveNotReturn() throws Exception {
		
	}

	@Override
	public void saveEdit() throws Exception {
		
	}

	@Override
	public String novo() throws Exception {
		return null;
	}

	@Override
	public void definirVariaveisNulas() throws Exception {
		
	}

	@Override
	public void consultarEntidade() throws Exception {
		
	}

	@Override
	public void statusOperation(SituacaoPersistencia a) throws Exception {
		Mensagens.responseOperation(a);
	}
	
	protected void sucesso() throws Exception {
		statusOperation(SituacaoPersistencia.SUCESSO);
	}
	
	protected void error() throws Exception {
		statusOperation(SituacaoPersistencia.ERRO);
	}

	@Override
	public String redirecionarNewEntidade() throws Exception {
		return null;
	}

	@Override
	public String redirecionarFindEntidade() throws Exception {
		return null;
	}

	@Override
	public void addMsg(String msg) {
		Mensagens.msg(msg);
	}

}
