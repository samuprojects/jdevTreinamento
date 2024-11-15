package br.com.framework.implementacao.crud;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.framework.interfac.crud.InterfaceCrud;

@Component
@Transactional
public class ImplementacaoCrud<T> implements InterfaceCrud<T> {

	private static final long serialVersionUID = 1L;
	
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	@Autowired
	private JdbcTemplateImpl jdbcTemplate;
	
	@Autowired
	private SimpleJdbcTemplateImpl simpleJdbcTemplate;
	
	@Autowired
	private SimpleJdbcInsertImpl simpleJdbcInsert;
	
	@Autowired
	private SimpleJdbcClassImpl simpleJdbcClass;
	
	public SimpleJdbcClassImpl getSimpleJdbcClass() {
		return simpleJdbcClass;
	}

	@Override
	public void save(T obj) throws Exception {

	}
	
	@Override
	public void persist(T obj) throws Exception {

	}

	@Override
	public void saveOrUpdate(T obj) throws Exception {

	}

	@Override
	public void update(T obj) throws Exception {

	}

	@Override
	public void delete(T obj) throws Exception {

	}

	@Override
	public T merge(T obj) throws Exception {
		return null;
	}

	@Override
	public List<T> findList(Class<T> objs) throws Exception {
		return null;
	}

	@Override
	public Object findById(Class<T> entidade, Long id) throws Exception {
		return null;
	}

	@Override
	public T findPorId(Class<T> entidade, Long id) throws Exception {
		return null;
	}

	@Override
	public List<T> findListByQueryDinamica(String s) throws Exception {
		return null;
	}

	@Override
	public void executeUpdateQueryDinamica(String s) throws Exception {

	}

	@Override
	public void executeUpdateSQLDinamica(String s) throws Exception {

	}

	@Override
	public void clearSession() throws Exception {

	}

	@Override
	public void evict(Object objs) throws Exception {

	}

	@Override
	public Session getSession() throws Exception {
		return null;
	}

	@Override
	public List<?> getListSQLDinamica(String sql) throws Exception {
		return null;
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public SimpleJdbcTemplate getSimpleJdbcTemplate() {
		return simpleJdbcTemplate;
	}

	@Override
	public SimpleJdbcInsert getsSimpleJdbcInsert() {
		return simpleJdbcInsert;
	}

	@Override
	public Long totalRegistro(String table) throws Exception {
		return null;
	}

	@Override
	public Query obterQuery(String query) throws Exception {
		return null;
	}

	@Override
	public List<T> findListByQueryDinamica(String query, int iniciaNoRegistro, int maximoResultado) throws Exception {
		return null;
	}
	
	private void validarSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		validarTransaction();
	}
	
	// validação para transação caso não exista, antes de qualquer operação no banco garantir que exista a transação ativa e a session factory criada 
	private void validarTransaction() {
		if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
	}
	
	// para utilizar operações de JDBC com Ajax
	private void commitProcessoAjax() {
		sessionFactory.getCurrentSession().beginTransaction().commit();
	}
	
	private void rollBackProcessoAjax() {
		sessionFactory.getCurrentSession().beginTransaction().rollback();
	}

}
