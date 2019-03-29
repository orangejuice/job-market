package dao;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class BaseDaoImpl<T extends Serializable, PK extends Serializable> implements BaseDao<T, PK> {
    @PersistenceContext
    protected EntityManager em;

    private void flush() {
        em.flush();
    }

    @Override
    public T save(T entity) {
        em.persist(entity);
        flush();
        return entity;
    }

    @Override
    public T delete(T entity) {
        em.remove(entity);
        flush();
        return entity;
    }

    @Override
    public T delete(Class<T> clazz, PK primaryKey) {
        T entity = em.find(clazz, primaryKey);
        em.remove(entity);
        flush();
        return entity;
    }

    @Override
    public T update(T entity) {
        em.merge(entity);
        flush();
        return entity;
    }

    @Override
    public T find(Class<T> clazz, PK primaryKey) {
        return em.find(clazz, primaryKey);
    }

    @Override
    public List<T> findAll(Class<T> entityClass) {
        TypedQuery<T> query = em.createQuery("select t from " + entityClass.getSimpleName() + " as t", entityClass);
        return query.getResultList();
    }

    public T getOne(Class<T> entityClass, String jpql, Map<String, Object> param) {
        TypedQuery<T> query = em.createQuery(jpql, entityClass);
        for (String key : param.keySet()) {
            query.setParameter(key, param.get(key));
        }
        return getSingleResult(query);
    }

    public List<T> getAsList(Class<T> entityClass, String jpql, Map<String, Object> param) {
        TypedQuery<T> query = em.createQuery(jpql, entityClass);
        for (String key : param.keySet()) {
            query.setParameter(key, param.get(key));
        }
        return query.getResultList();
    }

    /**
     * 获取单个实体，如果没有符合条件的实体将会返回null
     */
    <EntityClass> EntityClass getSingleResult(TypedQuery<EntityClass> query) {
        EntityClass entity = null;
        try {
            entity = query.getSingleResult();
        } catch (NoResultException e) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }
}
