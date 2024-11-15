package br.com.framework.interfac.crud;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public interface InterfaceCrud<T> extends Serializable {

	void save(T obj) throws Exception; // salvar dados
	
	void persist(T obj) throws Exception;
	
	void saveOrUpdate(T obj) throws Exception; // salvar/atualizar
	
	void update(T obj) throws Exception; // atualizar dados
	
	void delete(T obj) throws Exception; 
	
	T merge (T obj) throws Exception; // salvar/atualizar e retornar objeto em estado persistente
	
	List<T> findList(Class<T> objs) throws Exception; // carregar lista de dados de determinada classe
	
	Object findById(Class<T> entidade, Long id) throws Exception; // buscar objeto da classe
	
	T findPorId(Class<T> entidade, Long id) throws Exception; // outra alternativa retornar T
	
	List<T> findListByQueryDinamica(String s) throws Exception; // carregamento de Classe por uma query dinâmica
	
	void executeUpdateQueryDinamica(String s) throws Exception; // update com HQL
	
	void executeUpdateSQLDinamica(String s) throws Exception; // update com SQL
	
	void clearSession() throws Exception; // limpar sessão do Hibernate
	
	void evict(Object objs) throws Exception; // remover objeto da sessão Hibernate
	
	Session getSession() throws Exception; // retornar a sessão dentro da interface para utilizar nos controllers do spring
	
	List<?> getListSQLDinamica(String sql) throws Exception; // executar SQL dinâmica para retornar um conjunto de dados
	
	// 3 Classes JDBC Spring que implementam e abstraem com mais facilidade parecendo Hibernate mas utilizando JDBC
	JdbcTemplate getJdbcTemplate(); 
	
	SimpleJdbcTemplate getSimpleJdbcTemplate();
	
	SimpleJdbcInsert getsSimpleJdbcInsert();
	
	Long totalRegistro(String table) throws Exception; // para identificar qtd registros de uma tabela
	
	Query obterQuery(String query) throws Exception; // para realizar consultas dinâmicas nos registros do banco
	
	// para realizar carregamento por demanda
	List<T> findListByQueryDinamica(String query, int iniciaNoRegistro, int maximoResultado) throws Exception; 

}
