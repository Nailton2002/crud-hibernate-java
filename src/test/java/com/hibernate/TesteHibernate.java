package com.hibernate;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.hibernate.dao.DaoGeneric;
import com.hibernate.model.Pessoa;
import com.hibernate.model.Telefone;
import org.junit.Test;

public class TesteHibernate {

	@Test
	public void testeHibernateUtil() {
		HibernateUtil.geEntityManager();
	}

	@Test
	public void deveSalvarUmaEntidadeGenerica() {
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		Pessoa obj = new Pessoa();
		obj.setIdade(38);
		obj.setNome("Nailton");
		obj.setSobrenome("Mendes");
		obj.setLogin("admin");
		obj.setSenha("123");
		obj.setSexo("Masculino");
		obj.setSenha("123");
		obj.setSalario(5.72500);
		daoGeneric.salvar(obj);
	}

	@Test
	public void deveListarTodosEntidadeGenerica() {
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		List<Pessoa> list = daoGeneric.listarTodos(Pessoa.class);
		for (Pessoa obj : list) {
			System.out.println(obj);
			System.out.println("====================================================================================");
		}
	}

	@Test
	public void deveListarPorIdUmaEntidadeGenerica() {
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		Pessoa obj = daoGeneric.listaPorId(2L, Pessoa.class);
		System.out.println(obj);
	}

	@Test
	public void deveBuscarPorIdEntidadeGenerica () {
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		Pessoa obj = new Pessoa();
		obj.setId(2L);
		obj = daoGeneric.buscarPorId (obj);
		System.out.println(obj);
	}

	@Test
	public void deveAtualizarUmaEntidadeGenerica() {
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		Pessoa obj = daoGeneric.listaPorId(2L, Pessoa.class);
		obj.setIdade(38);
		obj.setNome("Jose");
		obj.setSenha("admin");
		obj = daoGeneric.atualizar(obj);
		System.out.println(obj);
	}

	@Test
	public void deveDeletarUmaEntidadeGenerica() {
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		Pessoa obj = daoGeneric.listaPorId(1L, Pessoa.class);
		try {
			daoGeneric.deletarPoId(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deveBuscarPorNome() {
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		List<Pessoa> list = daoGeneric.getEntityManager().createQuery(" from Pessoa where nome = 'Jose'")
		.getResultList();
		for (Pessoa obj : list) {
			System.out.println(obj);
		}
	}


	@Test
	//Esta busca é igual a uma buscar página no spring boot
	public void deveBuscarMaxResultadoPorNome() {
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		List<Pessoa> list = daoGeneric.getEntityManager().createQuery(" from Pessoa order by nome")
		.setMaxResults(2).getResultList();
		for (Pessoa obj : list) {
			System.out.println(obj);
		}
	}


	@Test
	//Esta busca é igual a uma buscar página no spring boot
	public void deveBuscarMaxResultadoPorId() {
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		List<Pessoa> list = daoGeneric.getEntityManager().createQuery(" from Pessoa order by id")
		.setMaxResults(2).getResultList();
		for (Pessoa obj : list) {
			System.out.println(obj);
		}
	}

	@Test
	public void deveBuscarPorNomeOuSobreNomePassandoParametro() {
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		List<Pessoa> list = daoGeneric.getEntityManager()
		.createQuery("from Pessoa where nome = :nome or sobrenome = :sobrenome")
		.setParameter("nome", "Jose")
		.setParameter("sobrenome", "Nailton")
		.getResultList();
		for (Pessoa obj : list) {
			System.out.println(obj);
		}
	}

	@Test
	public void deveBuscarPorNomeESobreNomePassandoParametro() {
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		List<Pessoa> list = daoGeneric.getEntityManager()
		.createQuery("from Pessoa where nome = :nome and sobrenome = :sobrenome")
		.setParameter("nome", "Jose")
		.setParameter("sobrenome", "Mendes")
		.getResultList();
		for (Pessoa obj : list) {
			System.out.println(obj);
		}
	}

	@Test
	public void deveSomaTodasIdadeNoBancoDados() {
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		Long somaIdade = (Long) daoGeneric.getEntityManager()
		.createQuery("select sum(p.idade) from Pessoa p ")
		.getSingleResult();
		System.out.println("Soma de todas as idades é --> " + somaIdade);
	}

	@Test
	public void deveBuscarMediaTodasIdadeNoBancoDados() {
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		Double somaIdade = (Double) daoGeneric.getEntityManager()
		.createQuery("select avg(p.idade) from Pessoa p ")
		.getSingleResult();
		System.out.println("Média de todas as idades é --> " + somaIdade);

	}

	@Test
	public void deveBuscarTodosComQueryEspecifica() {
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		List<Pessoa> list = daoGeneric.getEntityManager().createNamedQuery("Pessoa.buscarTodos").getResultList();
		for (Pessoa obj : list) {
			System.out.println(obj);
		}
	}

	@Test
	public void deveBuscarPorNomeComQueryEspecifica() {
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		List<Pessoa> list = daoGeneric.getEntityManager().createNamedQuery("Pessoa.buscaPorNome")
		.setParameter("nome", "Jose").getResultList();
		for (Pessoa obj : list) {
			System.out.println(obj);
		}
	}

	@Test
	public void deveSalvarTelefoneRelacionadoPessoa(){
		DaoGeneric daoGeneric = new DaoGeneric();
		Pessoa pessoa =  (Pessoa) daoGeneric.listaPorId(3L, Pessoa.class);
		Telefone fone = new Telefone();
		fone.setTipo("Celular");
		fone.setNumero("81 998582533");
		fone.setPessoa(pessoa);
		daoGeneric.salvar(fone);
		System.out.println(pessoa);
		System.out.println("==================================================================================");
		System.out.println(fone);
	}

	@Test
	public void deveBuscarTelefonesRelacionadoPessoa(){
		DaoGeneric daoGeneric = new DaoGeneric();
		Pessoa pessoa =  (Pessoa) daoGeneric.listaPorId(3L, Pessoa.class);
		for (Telefone fone : pessoa.getTelefone()) {
			System.out.println(fone.getNumero());
			System.out.println(fone.getTipo());
			System.out.println(fone.getPessoa().getNome());
			System.out.println("----------------------------------");
		}

	}


}
