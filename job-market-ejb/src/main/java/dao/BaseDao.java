package dao;

import java.io.Serializable;
import java.util.List;


public interface BaseDao<T extends Serializable, PK extends Serializable> {
    T save(T entity);

    T delete(T entity);

    T delete(Class<T> clazz, PK primaryKey);

    T update(T entity);

    T find(Class<T> clazz, PK primaryKey);

    List<T> findAll(Class<T> clazz);
}
