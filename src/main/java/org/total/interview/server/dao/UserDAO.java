package org.total.interview.server.dao;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.total.interview.server.model.User;

import java.util.List;

public class UserDAO implements DAOInterface<User, Long> {

    private Session session;
    private Transaction transaction;

    private SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());

        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

        return sessionFactory;
    }

    private Session openSessionWithTransaction() {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        return session;
    }

    private void closeSessionWithCommitTransaction() {
        transaction.commit();
        session.close();
    }

    public User findById(Long id) {
        session = getSessionFactory().openSession();
        User user = (User) session.get(User.class, id);
        Hibernate.initialize(user.getRoles());
        session.close();
        return user;
    }

    public User findByName(String name) {
        session = getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.like("userName", name));
        User user = (User) criteria.uniqueResult();
        if (user != null) {
            Hibernate.initialize(user.getRoles());
        }
        session.close();
        return user;
    }

    public List<User> findByUserNameAndPassword(String userName, String password) {
        session = getSessionFactory().openSession();
        String hql = "FROM User WHERE password = :password AND userName = :userName";
        Query query = session.createQuery(hql);
        query.setParameter("password", password);
        query.setParameter("userName", userName);
        List<User> users = (List<User>)query.list();
        if (!users.isEmpty() && users.get(0) != null) {
            Hibernate.initialize(users.get(0).getRoles());
        }
        session.close();
        return users;
    }

    public List<User> findAll() {
        session = getSessionFactory().openSession();
        List<User> users = (List<User>) session.createQuery("FROM User").list();
        session.close();
        return users;
    }

    public void persist(User entity) {
        session = openSessionWithTransaction();
        session.save(entity);
        closeSessionWithCommitTransaction();
    }

    public void update(User entity) {
        session = openSessionWithTransaction();
        session.update(entity);
        closeSessionWithCommitTransaction();
    }

    public void delete(User entity) {
        session = openSessionWithTransaction();
        session.delete(entity);
        closeSessionWithCommitTransaction();
    }

    public void deleteAll() {
        session = openSessionWithTransaction();
        List<User> entityList = findAll();
        for (User entity : entityList) {
            delete(entity);
        }
        closeSessionWithCommitTransaction();
    }

}
