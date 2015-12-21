package org.total.interview.server.dao;

import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class GenericDAO<T> implements DAOInterface<T> {

    private static SessionFactory SESSION_FACTORY = null;
    private static final Logger LOGGER = Logger.getLogger(GenericDAO.class);
    private Class<T> persistentClass;

    static {
        Configuration cfg = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
        SESSION_FACTORY = cfg.buildSessionFactory(builder.build());
    }

    public GenericDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

    public T findById(Long id) {
        T entity = null;
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            entity = (T) session.get(persistentClass, id);
        } catch (HibernateException e) {
            LOGGER.error(e, e);
        } finally {
            session.close();
        }
        return entity;
    }

    public List<T> findAll() {
        return findByCriteria();
    }

    public void persist(T entity) {
        Session session = null;
        Transaction transaction;
        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.error(e, e);
        } finally {
            session.close();
        }
    }

    public void delete(T entity) {
        Session session = null;
        Transaction transaction;
        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.error(e, e);
        } finally {
            session.close();
        }
    }

    public void deleteAll() {
        for (T entity : findAll()) {
            delete(entity);
        }
    }

    protected List<T> findByCriteria(Criterion... criterion) {
        Session session = null;
        Criteria criteria = null;
        List<T> entities = null;
        try {
            session = getSessionFactory().openSession();
            criteria = session.createCriteria(getPersistentClass());
            for (Criterion c : criterion) {
                criteria.add(c);
            }
            entities = criteria.list();
        } catch (HibernateException e) {
            LOGGER.error(e, e);
        } finally {
            session.close();
        }
        return entities;
    }

}
