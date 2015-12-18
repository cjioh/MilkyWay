package org.total.interview.server.dao;

import java.io.Serializable;
import java.util.List;

public interface DAOInterface<T, Id extends Serializable> {

    public T findById(Id id);

    public List<T> findAll();

    public void persist(T entity);

    public void update(T entity);

    public void delete(T entity);

    public void deleteAll();

}