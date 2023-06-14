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
		obj.setLogin("teste");
		obj.setNome("Nailton");
		obj.setSenha("123");
		obj.setSobrenome("Mendes");

		daoGeneric.salvar(obj);
	}

	@Test
	public void deveListarPorIdUmaEntidadeGenerica() {
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		Pessoa obj = new Pessoa();
		obj.setId(1L);
		obj = daoGeneric.buscarPorId(obj);
		System.out.println(obj);
	}

	@Test
	public void deveBuscarPorIdEntidadeGenerica() {
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		Pessoa obj = daoGeneric.listaPorId(1L, Pessoa.class);
		System.out.println(obj);

	}
}
