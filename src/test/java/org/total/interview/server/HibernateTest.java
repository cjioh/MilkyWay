package org.total.interview.server;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.total.interview.server.entity.Role;
import org.total.interview.server.management.UserManagerImpl;
import org.total.interview.server.util.HibernateUtil;

import java.util.HashSet;
import java.util.Set;

public class HibernateTest {

    private static final Logger LOGGER = Logger.getLogger(HibernateUtil.class);
    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();

    @Test
    public void connectionTest() throws Exception {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.debug("HibernateUtilTest.connectionTest: sessionFactory=" + SESSION_FACTORY.toString());
        }
        HibernateUtil.shutdown();
    }

    @Test
    public void insertUser() throws Exception {
        Set<Role> roles = new HashSet<Role>();
        roles.add(new Role("user"));
        UserManagerImpl.getInstance().addUserByUserName("User", roles);
    }

    @Test
    public void getAllUsers() throws Exception {
        UserManagerImpl.getInstance().getAllUsers();
    }

    @Test
    public void updateUser() throws Exception {
        UserManagerImpl.getInstance().updateUserById(2, "Test");
    }

    @Test
    public void deleteUser() throws Exception {
        UserManagerImpl.getInstance().deleteUserById(3);
    }

}