package br.com.project.util.all;

public enum SituacaoPersistencia {

	ERRO("Erro"), SUCESSO("Sucesso"),
	OBJETO_REFERENCIADO("Esse objeto não pode ser apagado por possuir referências ao mesmo.");
	
	private String name;
	
	private SituacaoPersistencia(String s) {
		this.name = s;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
