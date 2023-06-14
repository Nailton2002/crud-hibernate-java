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

    public EntidadeGeneric atualizar(EntidadeGeneric entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        EntidadeGeneric obj = entityManager.merge(entity);
        transaction.commit();
        return obj;
    }

    public EntidadeGeneric buscarPorId(EntidadeGeneric entity) {
        Object id = HibernateUtil.getPrimaryKey(entity);
        EntidadeGeneric obj = (EntidadeGeneric) entityManager.find(entity.getClass(), id);
        return obj;
    }

    public EntidadeGeneric listaPorId(Long id, Class<EntidadeGeneric> entidade) {
        entityManager.clear();
        EntidadeGeneric obj = (EntidadeGeneric) entityManager.createQuery(
"from " + entidade.getSimpleName() + " where id = " + id).getSingleResult();
        return obj;
    }
}
