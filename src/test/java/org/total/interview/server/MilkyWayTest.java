package org.total.interview.server;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.total.interview.server.dao.GenericDAO;
import org.total.interview.server.model.Role;
import org.total.interview.server.model.RoleType;
import org.total.interview.server.model.User;
import org.total.interview.server.service.RoleService;
import org.total.interview.server.service.UserRoleService;
import org.total.interview.server.service.UserService;
import org.total.interview.server.util.PasswordManager;
import org.total.interview.server.util.PasswordManagerImpl;

import java.util.List;

public class MilkyWayTest {

    private static final Logger LOGGER = Logger.getLogger(MilkyWayTest.class);
    private static final SessionFactory SESSION_FACTORY = GenericDAO.getSessionFactory();
    private static final UserService USER_SERVICE = new UserService();
    private static final RoleService ROLE_SERVICE = new RoleService();
    private static final UserRoleService USER_ROLE_SERVICE = new UserRoleService();

    @Test
    public void connectionTest() throws Exception {
        LOGGER.debug(SESSION_FACTORY.toString());
        GenericDAO.shutdown();
    }

    @Test
    public void test() throws Exception {
//        PasswordManager passwordManager = new PasswordManagerImpl();
//        USER_SERVICE.persist(new User("Puma", passwordManager.encode("puma")));
//        ROLE_SERVICE.deleteById(8L);
//            for (User user : USER_SERVICE.findAll()) {
//                LOGGER.error(user);
//            }
//        List<User> users = USER_SERVICE.findByUserNameAndPassword("Lama", passwordManager.encode("lama"));
//        LOGGER.info(users);
//        User user = users.get(0);
//        user.setUserName("Lana");
//        USER_SERVICE.update(user);
//        List<User> users = USER_SERVICE.findByUserNameAndPassword("NikaLucky", passwordManager.encode("nika"));
//        LOGGER.info(users.get(0));
//        users.get(0).setUserName("Nika");
//        USER_SERVICE.persist(users.get(0));
//        LOGGER.info(user.getRoles());
//        if (user.getRoles().contains(ROLE_SERVICE.findByRoleType(RoleType.ADMIN))) {
//            LOGGER.info("Ok");
//        }
//        List<User> users = USER_SERVICE.findByUserNameAndPassword("Anton", passwordManager.encode("anton"));
//        for (User elem : users) {
//            LOGGER.info("*****************" + users.contains(USER_SERVICE.findByRoleType("Anton")));
//        }

//            LOGGER.info(user);
//        Role role = ROLE_SERVICE.findByRoleType(RoleType.GUEST);
//        LOGGER.info(role);
//        for (Role role : ROLE_SERVICE.findAll()) {
//            LOGGER.info(role);
//        }
//        USER_SERVICE.deleteById(9L);
//        List<User> users = USER_SERVICE.findAll();
//        for (User elem : users) {
//            LOGGER.info(elem.getRoles());
//        }
//        Assert.assertTrue(USER_ROLE_SERVICE.assignRoleByUserNameAndRoleType("Puma", RoleType.GUEST));
//        User user = USER_SERVICE.findByRoleType("NON");
//        user.getPassword();
//        for (int index = 0; index < 100; index++) {
//            USER_ROLE_SERVICE.assignRoleByUserNameAndRoleType("Anton", "user");
//            USER_ROLE_SERVICE.revokeRoleByUserNameAndRoleType("Anton", "user");
//        }
    }

}