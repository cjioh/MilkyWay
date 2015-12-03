package org.total.interview.server.management;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.total.interview.server.entity.Role;
import org.total.interview.server.entity.User;
import org.total.interview.server.util.HibernateUtil;

import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by pavlo.fandych on 12/3/2015.
 */
public class UserManagerImpl implements UserManager {

    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();
    private static final Logger LOGGER = Logger.getLogger(UserManagerImpl.class);

    private static volatile UserManagerImpl SingletonRef;
    private static final Lock SingletonLock = new ReentrantLock();

    private UserManagerImpl() {}

    public static UserManagerImpl getInstance() {
        if (SingletonRef == null) {
            try {
                SingletonLock.lock();
                SingletonRef = new UserManagerImpl();
            } catch (Exception e) {
                LOGGER.error(e, e);
            } finally {
                SingletonLock.unlock();
            }
        }
        return SingletonRef;
    }

    private static void shutdown() {
        HibernateUtil.shutdown();
    }

    public Integer addUserByUserName(String userName, Set<Role> roles) {
        Session session = SESSION_FACTORY.openSession();
        Transaction tx = null;
        Integer userID = null;

        try {
            tx = session.beginTransaction();
            User user = new User(userName);
            user.setRoles(roles);
            userID = (Integer) session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            LOGGER.error(e, e);
        } catch (Exception e) {
            LOGGER.error(e, e);
        } finally {
            session.close();
            shutdown();
        }
        return userID;
    }

    public List<User> getAllUsers() {
        Session session = SESSION_FACTORY.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List users = session.createQuery("FROM User").list();
            tx.commit();
            return users;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            LOGGER.error(e, e);
        } catch (Exception e) {
            LOGGER.error(e, e);
        } finally {
            session.close();
            shutdown();
        }
        return null;
    }

    public void updateUserById(Integer userID, String userName) {
        Session session = SESSION_FACTORY.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            User user =
                    (User) session.get(User.class, userID);
            user.setUserName(userName);
            session.update(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            LOGGER.error(e, e);
        } catch (Exception e) {
            LOGGER.error(e, e);
        } finally {
            session.close();
            shutdown();
        }
    }

    public void deleteUserById(Integer userID) {
        Session session = SESSION_FACTORY.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            User user =
                    (User) session.get(User.class, userID);
            session.delete(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            LOGGER.error(e, e);
        } catch (Exception e) {
            LOGGER.error(e, e);
        } finally {
            session.close();
            shutdown();
        }
    }

}
