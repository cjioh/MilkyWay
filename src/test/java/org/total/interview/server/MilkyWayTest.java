package org.total.interview.server;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.total.interview.server.model.Role;
import org.total.interview.server.model.RoleType;
import org.total.interview.server.model.User;
import org.total.interview.server.service.RoleService;
import org.total.interview.server.service.UserRoleService;
import org.total.interview.server.service.UserService;
import org.total.interview.server.util.HibernateUtil;
import org.total.interview.server.util.PasswordManager;
import org.total.interview.server.util.PasswordManagerImpl;

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
//        USER_SERVICE.persist(new User("AAA", passwordManager.encode("aaa")));
//        ROLE_SERVICE.persist(new Role(RoleType.GUEST));
//            for (User user : USER_SERVICE.findAll()) {
//                LOGGER.error(user);
//            }
//        List<User> users = USER_SERVICE.findByUserNameAndPassword("Lama", passwordManager.encode("lama"));
//        LOGGER.info(users);
//        User user = users.get(0);
//        user.setUserName("Lana");
//        USER_SERVICE.update(user);
//        User user = USER_SERVICE.findByRoleType("Tiger");
//        LOGGER.info(user);
//        if (user.getRoles().contains(ROLE_SERVICE.findByRoleTitle("admin"))) {
//            LOGGER.info("Ok");
//        }
//        List<User> users = USER_SERVICE.findByUserNameAndPassword("Anton", passwordManager.encode("anton"));
//        for (User elem : users) {
//            LOGGER.info("*****************" + users.contains(USER_SERVICE.findByRoleType("Anton")));
//        }
//            List<Role> roles = ROLE_SERVICE.findAll();
//        for (Role elem : roles) {
//            LOGGER.info(elem);
//        }
//            LOGGER.info(user);
//        Role role = ROLE_SERVICE.findByRoleType(RoleType.MODERATOR);
//        LOGGER.info(role);
//        for (Role role : ROLE_SERVICE.findAll()) {
//            LOGGER.info(role);
//        }
//        USER_SERVICE.deleteById(9L);
//        List<User> users = USER_SERVICE.findAll();
//        for (User elem : users) {
//            LOGGER.info(elem);
//        }
//        Assert.assertTrue(USER_ROLE_SERVICE.assignRoleByUserNameAndRoleType("Vova", RoleType.GUEST));
//        User user = USER_SERVICE.findByRoleType("NON");
//        user.getPassword();
//        for (int index = 0; index < 100; index++) {
//            USER_ROLE_SERVICE.assignRoleByUserNameAndRoleType("Anton", "user");
//            USER_ROLE_SERVICE.revokeRoleByUserNameAndRoleType("Anton", "user");
//        }
    }

}