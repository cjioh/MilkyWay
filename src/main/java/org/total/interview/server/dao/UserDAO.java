package org.total.interview.server.dao;

import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.total.interview.server.model.User;

import java.util.List;

public class UserDAO extends GenericDAO<User> implements DAOInterface<User> {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class);

    public User findById(Long id) {
        User user = null;
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            user = (User) session.get(User.class, id);
            if (user != null) {
                Hibernate.initialize(user.getRoles());
            }
        } catch (HibernateException e) {
            LOGGER.error(e, e);
        } finally {
            session.close();
        }
        return user;
    }

    public User findByName(String name) {
        User user = null;
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.like("userName", name));
            user = (User) criteria.uniqueResult();
            if (user != null) {
                Hibernate.initialize(user.getRoles());
            }
        } catch (HibernateException e) {
            LOGGER.error(e, e);
        } finally {
            session.close();
        }
        return user;
    }

    public User findByUserNameAndPassword(String userName, String password) {
        User user = null;
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            String hql = "FROM User WHERE password = :password AND userName = :userName";
            Query query = session.createQuery(hql);
            query.setParameter("userName", userName);
            query.setParameter("password", password);
            user = (User) query.uniqueResult();
            if (user != null) {
                Hibernate.initialize(user.getRoles());
            }
        } catch (HibernateException e) {
            LOGGER.error(e, e);
        } finally {
            session.close();
        }
        return user;
    }

    public List<User> findAll() {
        List<User> users = null;
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            users = (List<User>) session.createQuery("FROM User").list();
            if (users != null && !users.isEmpty()) {
                for (User user : users)
                Hibernate.initialize(user.getRoles());
            }
        } catch (HibernateException e) {
            LOGGER.error(e, e);
        } finally {
            session.close();
        }
        return users;
    }

}
