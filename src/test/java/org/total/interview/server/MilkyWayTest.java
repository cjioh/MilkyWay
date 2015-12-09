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
    public void test() throws Exception {
//        PasswordManager passwordManager = new PasswordManagerImpl();
//        USER_SERVICE.persist(new User("Vova", passwordManager.encode("vova")));

//        ROLE_SERVICE.persist(new Role("moderator"));

//            for (User user : USER_SERVICE.findAll()) {
//                LOGGER.error(user);
//            }
//        User user = USER_SERVICE.findByName("Vova");
//        for (Role role : user.getRoles()) {
//            LOGGER.error("***************" + role.getRoleTitle());
//        }

//            LOGGER.info(user);
//        Role role = ROLE_SERVICE.findById(1L);
//        LOGGER.info(role);

//        for (Role role : ROLE_SERVICE.findAll()) {
//            LOGGER.info(role);
//        }
//        USER_ROLE_SERVICE.assignRoleByUserNameAndRoleTitle("Vova", "guest");
        User user = USER_SERVICE.findByName("NON");
        user.getPassword();
    }

}