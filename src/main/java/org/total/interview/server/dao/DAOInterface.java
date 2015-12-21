package org.total.interview.server.dao;

import java.util.List;

public interface DAOInterface<T> {

    public T findById(Long id);

    public List<T> findAll();

    public void persist(T entity);

    public void delete(T entity);

    public void deleteAll();

}