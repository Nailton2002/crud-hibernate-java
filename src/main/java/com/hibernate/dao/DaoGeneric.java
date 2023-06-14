package com.hibernate.dao;

import com.hibernate.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class DaoGeneric <EntidadeGeneric>{

    private EntityManager entityManager = HibernateUtil.geEntityManager();

    public void salvar(EntidadeGeneric entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
    }

    public EntidadeGeneric pesquisar(EntidadeGeneric entity) {
        Object id = HibernateUtil.getPrimaryKey(entity);
        EntidadeGeneric obj = (EntidadeGeneric) entityManager.find(entity.getClass(), id);
        return obj;

    }
}
