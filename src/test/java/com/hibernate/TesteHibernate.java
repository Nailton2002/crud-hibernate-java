package com.hibernate;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.hibernate.dao.DaoGeneric;
import com.hibernate.model.Pessoa;
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
	public void deveBuscarMaxResultadoPorNome() {
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		List<Pessoa> list = daoGeneric.getEntityManager().createQuery(" from Pessoa order by nome")
		.setMaxResults(2).getResultList();
		for (Pessoa obj : list) {
			System.out.println(obj);
		}

	}

}
