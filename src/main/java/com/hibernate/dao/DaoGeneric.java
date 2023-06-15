package com.hibernate.dao;

import com.hibernate.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class DaoGeneric <EntidadeGeneric>{

    private EntityManager entityManager = HibernateUtil.geEntityManager();

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void salvar(EntidadeGeneric entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
    }

    public List<EntidadeGeneric> listarTodos(Class<EntidadeGeneric> entidade) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List<EntidadeGeneric> lista = entityManager.createQuery("from " + entidade.getName()).getResultList();
        transaction.commit();
        return lista;
    }

    public EntidadeGeneric listaPorId (Long id, Class<EntidadeGeneric> entidade) {
        entityManager.clear();
        EntidadeGeneric obj = (EntidadeGeneric) entityManager.createQuery(
                "from " + entidade.getSimpleName() + " where id = " + id).getSingleResult();
        return obj;
    }

    public EntidadeGeneric buscarPorId(EntidadeGeneric entity) {
        Object id = HibernateUtil.getPrimaryKey(entity);
        EntidadeGeneric obj = (EntidadeGeneric) entityManager.find(entity.getClass(), id);
        return obj;
    }

    public EntidadeGeneric atualizar(EntidadeGeneric entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        EntidadeGeneric obj = entityManager.merge(entity);
        transaction.commit();
        return obj;
    }

    public void deletarPoId(EntidadeGeneric entity) throws Exception{
        Object id = HibernateUtil.getPrimaryKey(entity);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.createNativeQuery("delete from " + entity.getClass().getSimpleName().toLowerCase()
        + " where id =" + id).executeUpdate();
        transaction.commit();
    }

}
