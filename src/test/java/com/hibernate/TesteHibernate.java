package com.hibernate;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Test;

public class TesteHibernate {

	@Test
	public void testeHibernateUtil() {
		HibernateUtil.geEntityManager();
	}
}
