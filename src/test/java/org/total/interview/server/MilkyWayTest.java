package org.total.interview.server;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.total.interview.server.dao.GenericDAO;
import org.total.interview.server.model.Role;
import org.total.interview.server.model.RoleType;
import org.total.interview.server.service.RoleService;
import org.total.interview.server.service.UserRoleService;
import org.total.interview.server.service.UserService;

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
        Assert.assertNull(USER_SERVICE.findByName(null));

//        ROLE_SERVICE.persist(new Role(RoleType.ADMIN));
//        USER_ROLE_SERVICE.assignRoleByUserNameAndRoleType("Tiger", RoleType.ADMIN);
    }

}