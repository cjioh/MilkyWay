package org.total.interview.server;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.total.interview.server.model.Role;
import org.total.interview.server.model.User;
import org.total.interview.server.service.RoleService;
import org.total.interview.server.service.UserRoleService;
import org.total.interview.server.service.UserService;
import org.total.interview.server.util.HibernateUtil;
import org.total.interview.server.util.PasswordManager;
import org.total.interview.server.util.PasswordManagerImpl;

import java.util.HashSet;
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

        PasswordManager passwordManager = new PasswordManagerImpl();

        User user = new User();
        user.setUserName("Qwerty");
        user.setPassword(passwordManager.encode("qwerty"));

        USER_SERVICE.persist(user);

        USER_ROLE_SERVICE.assignRoleByUserNameAndRoleTitle("user", "Qwerty");
    }

    @Test
    public void getAllUsers() throws Exception {

    }

    @Test
    public void findNonExistingUser() throws Exception {
        User user = USER_SERVICE.findByName("NonExisting");
        LOGGER.info(USER_SERVICE.findByName("NonExisting"));
        Assert.assertNull(user);
        user.getUserId();
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
        for (Role role : user.getRoles()) {
            LOGGER.info(role.getRoleTitle());
        }
    }

    @Test
    public void updateUser() throws Exception {

    }

    @Test
    public void deleteUser() throws Exception {

    }

    @Test
    public void getRoleByRoleTitle() throws Exception {
        Role role = ROLE_SERVICE.findByRoleTitle("guest");
        LOGGER.info(role);
        for (User user : role.getUsers()) {
            LOGGER.info(user.getUserName());
        }
    }

    @Test
    public void assignRole() throws Exception {
        USER_ROLE_SERVICE.assignRoleByUserNameAndRoleTitle("guest", "Total");
    }

    @Test
    public void revokeRole() throws Exception {
        USER_ROLE_SERVICE.revokeRoleByUserNameAndRoleTitle("admin", "Nika");
    }

    @Test
    public void getAllUsersWithRoleUser() throws Exception {
        Role role = ROLE_SERVICE.findByRoleTitle("guest");
        Set<User> users = role.getUsers();

    }

    @Test
    public void insertRole() throws Exception {
        Role role = new Role("guest");
        ROLE_SERVICE.persist(role);
    }

    @Test
    public void inserUserWithoutRole() throws Exception {
        User user = new User();
        user.setUserName("Total");

        PasswordManager passwordManager = new PasswordManagerImpl();
        user.setPassword(passwordManager.encode("l777Pui1"));

        USER_SERVICE.persist(user);
    }

}