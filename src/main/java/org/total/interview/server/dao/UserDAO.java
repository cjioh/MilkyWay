package org.total.interview.server.dao;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.total.interview.server.model.User;

import java.util.List;

public class UserDAO implements DAOInterface<User, Long> {

    private Session currentSession;

    private Transaction currentTransaction;

    public UserDAO() {

    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;

    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void persist(User entity) {
        getCurrentSession().save(entity);
    }

    public void update(User entity) {
        getCurrentSession().update(entity);
    }

    public User findById(Long id) {
        Session session = openCurrentSessionwithTransaction();
        User user = (User) getCurrentSession().get(User.class, id);
        Hibernate.initialize(user.getRoles());
        closeCurrentSessionwithTransaction();
        return user;

    }

    public User findByName(String name) {
        Session session = openCurrentSessionwithTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.like("userName", name));
        User user = (User) criteria.uniqueResult();
        if (user != null) {
            Hibernate.initialize(user.getRoles());
        }
        closeCurrentSessionwithTransaction();
        return user;
    }

    public void delete(User entity) {
        getCurrentSession().delete(entity);
    }

    public List<User> findAll() {
        List<User> users = (List<User>) getCurrentSession().createQuery("FROM User").list();
        return users;
    }

    public void deleteAll() {
        List<User> entityList = findAll();
        for (User entity : entityList) {
            delete(entity);
        }
    }

}
