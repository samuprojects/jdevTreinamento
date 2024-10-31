package posjavamavenhibernate;

import java.util.List;

import org.junit.Test;

import dao.DaoGeneric;
import model.TelefoneUser;
import model.UsuarioPessoa;

public class TesteHibernate {

	@Test
	public void testeHibernateUtil() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa.setIdade(30);
		pessoa.setLogin("teste");
		pessoa.setNome("John Dev");
		pessoa.setSenha("123");
		pessoa.setSobrenome("Silva");
		pessoa.setEmail("johndev@example.com");
		
		daoGeneric.salvar(pessoa);
	}
	
	@Test
	public void testeBuscar() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(2L);
		
		pessoa = daoGeneric.pesquisar(pessoa);
		
		System.out.println(pessoa);
	}
	
	@Test
	public void testeBuscar2() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(2L, UsuarioPessoa.class);
		
		System.out.println(pessoa);
	}
	
	@Test
	public void testeUpdate() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(1L, UsuarioPessoa.class);
		
		pessoa.setIdade(99);
		pessoa.setNome("Nome atualizado Hibernate");
		pessoa.setSenha("sasas");
		pessoa.setEmail("teste@example.com");
		pessoa.setSobrenome("Java FX");
		
		pessoa = daoGeneric.updateMerge(pessoa);
		
		System.out.println(pessoa);
	}
	
	@Test
	public void testeDelete() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(1L, UsuarioPessoa.class);
		
		daoGeneric.deletarPorId(pessoa);
	}
	
	@Test
	public void testeConsultar() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.listar(UsuarioPessoa.class);
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("-----------------------------------------------------");
		}
	}
	
	@Test
	public void testeQueryList() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		@SuppressWarnings("unchecked")
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa where nome = 'Sam Dev'").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}
	
	@Test
	public void testeQueryListMaxResult() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		@SuppressWarnings("unchecked")
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa order by nome").setMaxResults(3).getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}
	
	@Test
	public void testeQueryListParameter() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		@SuppressWarnings("unchecked")
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery("from UsuarioPessoa where nome = :nome or sobrenome = :sobrenome")
		.setParameter("nome", "Sam Dev")
		.setParameter("sobrenome", "Silva").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}
	
	@Test
	public void testeQuerySomaIdade() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		Long somaIdade = (Long) daoGeneric.getEntityManager().createQuery("select sum(u.idade) from UsuarioPessoa u ").getSingleResult();
		
		System.out.println("Soma de todas as idade é --> " + somaIdade);
	}
	
	@Test
	public void testeNamedQuery1() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		@SuppressWarnings("unchecked")
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.todos").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}
	
	@Test
	public void testeNamedQuery2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		@SuppressWarnings("unchecked")
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.buscarPorNome")
		.setParameter("nome", "John Dev").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}
	
	@SuppressWarnings("all")
	@Test
	public void testeGravarTelefone() {
		DaoGeneric daoGeneric = new DaoGeneric();
		
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(2L, UsuarioPessoa.class);
		
		TelefoneUser telefoneUser = new TelefoneUser();
		
		telefoneUser.setTipo("Casa");
		telefoneUser.setNumero("987654321");
		telefoneUser.setUsuarioPessoa(pessoa);
		
		daoGeneric.salvar(telefoneUser);
	}
	
	@SuppressWarnings("all")
	@Test
	public void testeConsultarTelefones() {
	    DaoGeneric daoGeneric = new DaoGeneric();
	    
	    UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(2L, UsuarioPessoa.class);
	    
	    if (pessoa != null && pessoa.getTelefoneUsers() != null) {
	        for (TelefoneUser fone : pessoa.getTelefoneUsers()) {
	            if (fone != null) {
	                System.out.println(fone.getNumero());
	                System.out.println(fone.getTipo());
	                System.out.println(fone.getUsuarioPessoa() != null ? fone.getUsuarioPessoa().getNome() : "Usuário Pessoa é nulo");
	                System.out.println("--------------------------------------");
	            } else {
	                System.out.println("TelefoneUser é nulo");
	            }
	        }
	    } else {
	        System.out.println("Pessoa é nula ou não possui telefones");
	    }
	}
}
