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

		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		Pessoa obj = new Pessoa();

		obj.setIdade(38);
		obj.setLogin("teste");
		obj.setNome("Nailton");
		obj.setSenha("123");
		obj.setSobrenome("Mendes");

		daoGeneric.salvar(obj);
	}
}
