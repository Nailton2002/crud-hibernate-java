package com.hibernate.dao;

import com.hibernate.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class DaoGeneric <E>{

    private EntityManager entityManager = HibernateUtil.geEntityManager();

    public void salvar(E entidade) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entidade);
        transaction.commit();
    }
}
