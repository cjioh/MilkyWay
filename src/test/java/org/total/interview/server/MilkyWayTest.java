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
    public void test() throws Exception {
//        PasswordManager passwordManager = new PasswordManagerImpl();
//        USER_SERVICE.persist(new User("Tiger", passwordManager.encode("tiger")));

//        ROLE_SERVICE.persist(new Role("admin"));

//            for (User user : USER_SERVICE.findAll()) {
//                LOGGER.error(user);
//            }
//        User user = USER_SERVICE.findByName("Tiger");
//        if (user.getRoles().contains(ROLE_SERVICE.findByRoleTitle("admin"))) {
//            LOGGER.info("Ok");
//        }

//            LOGGER.info(user);
//        Role role = ROLE_SERVICE.findById(1L);
//        LOGGER.info(role);

//        for (Role role : ROLE_SERVICE.findAll()) {
//            LOGGER.info(role);
//        }
//        List<User> users = USER_SERVICE.findAll();
//        USER_ROLE_SERVICE.assignRoleByUserNameAndRoleTitle("Tiger", "user");
//        User user = USER_SERVICE.findByName("NON");
//        user.getPassword();

//        for (int index = 0; index < 100; index++) {
//            USER_ROLE_SERVICE.assignRoleByUserNameAndRoleTitle("Anton", "user");
//            USER_ROLE_SERVICE.revokeRoleByUserNameAndRoleTitle("Anton", "user");
//        }
    }

}