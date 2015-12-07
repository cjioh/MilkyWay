package org.total.interview.server;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.total.interview.server.model.Role;
import org.total.interview.server.model.User;
import org.total.interview.server.service.RoleService;
import org.total.interview.server.service.UserRoleService;
import org.total.interview.server.service.UserService;
import org.total.interview.server.util.HibernateUtil;
import org.total.interview.server.util.PasswordManager;
import org.total.interview.server.util.PasswordManagerImpl;

import javax.jws.soap.SOAPBinding;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MilkyWayTest {

    private static final Logger LOGGER = Logger.getLogger(HibernateUtil.class);
    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();
    private static final UserService USER_SERVICE = new UserService();
    private static final RoleService ROLE_SERVICE = new RoleService();
    private static final UserRoleService USER_ROLE_SERVICE = new UserRoleService();

    @Test
    public void connectionTest() throws Exception {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.debug("HibernateUtilTest.connectionTest: sessionFactory=" + SESSION_FACTORY.toString());
        }
        HibernateUtil.shutdown();
    }

    @Test
    public void insertUserWithRole() throws Exception {
        Set<Role> roles = new HashSet<Role>();
        roles.add(new Role("user"));

        PasswordManager passwordManager = new PasswordManagerImpl();
        String password = passwordManager.encode("nika");

        User user = new User();
        user.setUserName("Nika");
        user.setPassword(password);
        user.setRoles(roles);

        USER_SERVICE.persist(user);
    }


    @Test
    public void getAllUsers() throws Exception {

    }

    @Test
    public void inserUserWithoutRole() throws Exception {
        User user = new User();
        user.setUserName("Vasya");

        PasswordManager passwordManager = new PasswordManagerImpl();
        user.setPassword(passwordManager.encode("vasya"));

        USER_SERVICE.persist(user);
    }

    @Test
    public void getUserById() throws Exception {
        User user = USER_SERVICE.findById(1L);
        LOGGER.info(user);
    }

    @Test
    public void getUserByName() throws Exception {
        User user = USER_SERVICE.findByName("Total");
        LOGGER.info(user);
    }

    @Test
    public void updateUser() throws Exception {

    }

    @Test
    public void deleteUser() throws Exception {

    }

    @Test
    public void assignRole() throws Exception {
        USER_ROLE_SERVICE.assignRole("user", "Vasya");
    }

}