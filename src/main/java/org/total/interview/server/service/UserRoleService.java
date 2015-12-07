package org.total.interview.server.service;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.total.interview.server.model.Role;
import org.total.interview.server.model.User;
import org.total.interview.server.util.HibernateUtil;

/**
 * Created by pavlo.fandych on 12/7/2015.
 */
public class UserRoleService {

    private static final UserService USER_SERVICE = new UserService();
    private static final RoleService ROLE_SERVICE = new RoleService();

    private static final Logger LOGGER = Logger.getLogger(UserRoleService.class);

    public boolean assignRole(String roleTitle, String userName) {
        try {
            Role roleToAssign = ROLE_SERVICE.findByRoleTitle(roleTitle);
            if (roleToAssign == null) {
                LOGGER.error("Role " + roleTitle + " not found.\n");
                return false;
            }

            User userToUpdate = USER_SERVICE.findByName(userName);
            if (userToUpdate == null) {
                LOGGER.error("User " + userName + " not found.\n");
                return false;
            }


            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            Hibernate.initialize(userToUpdate.getRoles());
            if (userToUpdate.getRoles().add(roleToAssign)) {
                session.update(userToUpdate);
                transaction.commit();
                session.close();
                return true;
            }
            session.close();

            return false;
        } catch (Exception e) {
            LOGGER.error(e, e);
        }
        return false;
    }
}
