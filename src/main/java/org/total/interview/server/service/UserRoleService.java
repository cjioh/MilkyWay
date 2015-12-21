package org.total.interview.server.service;

import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.total.interview.server.dao.GenericDAO;
import org.total.interview.server.model.Role;
import org.total.interview.server.model.RoleType;
import org.total.interview.server.model.User;

/**
 * Created by pavlo.fandych on 12/7/2015.
 */
public class UserRoleService {

    private static final SessionFactory SESSION_FACTORY = GenericDAO.getSessionFactory();
    private static final Logger LOGGER = Logger.getLogger(UserRoleService.class);

    public boolean assignRoleByUserNameAndRoleType(String userName, RoleType roleType) {
        Session session = SESSION_FACTORY.openSession();

        if (session == null) {
            LOGGER.error("Session creation failed.\n");
            return false;
        }

        LOGGER.debug("Session creation done.\n");

        Transaction transaction = session.beginTransaction();

        if (transaction == null) {
            LOGGER.error("Transaction opening failed.\n");
            return false;
        }
        LOGGER.debug("Transaction opening done.\n");

        try {
            Criteria userCriteria = session.createCriteria(User.class);

            if (userCriteria == null) {
                LOGGER.error("User criteria creation failed.\n");
                return false;
            }

            LOGGER.debug("User criteria creation done.\n");

            userCriteria.add(Restrictions.like("userName", userName));

            User userToUpdate = (User) userCriteria.uniqueResult();

            if (userToUpdate == null) {
                LOGGER.error("Fetching user failed.\n");
                return false;
            }

            LOGGER.debug("Fetching user done.\n");

            Criteria roleCriteria = session.createCriteria(Role.class);

            if (roleCriteria == null) {
                LOGGER.error("Role criteria creation failed.\n");
                return false;
            }

            LOGGER.debug("Role criteria creation done.\n");

            roleCriteria.add(Restrictions.like("roleType", roleType));

            Role roleToAssign = (Role) roleCriteria.uniqueResult();

            if (roleToAssign == null) {
                LOGGER.error("Fetching role failed.\n");
                return false;
            }

            LOGGER.debug("Fetching role done.\n");

            Hibernate.initialize(userToUpdate.getRoles());

            if (userToUpdate.getRoles().add(roleToAssign)) {
                session.update(userToUpdate);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            LOGGER.error(e, e);
        } finally {
            transaction.commit();
            session.close();
        }
        return false;
    }

    public boolean revokeRoleByUserNameAndRoleType(String userName, RoleType roleType) {
        Session session = SESSION_FACTORY.openSession();

        if (session == null) {
            LOGGER.error("Session creation failed.\n");
            return false;
        }

        LOGGER.debug("Session creation done.\n");

        Transaction transaction = session.beginTransaction();

        if (transaction == null) {
            LOGGER.error("Transaction opening failed.\n");
            return false;
        }

        LOGGER.debug("Transaction opening done.\n");

        try {
            Criteria userCriteria = session.createCriteria(User.class);

            if (userCriteria == null) {
                LOGGER.error("User criteria creation failed.\n");
                return false;
            }

            LOGGER.debug("User criteria creation done.\n");

            userCriteria.add(Restrictions.like("userName", userName));

            User userToUpdate = (User) userCriteria.uniqueResult();

            if (userToUpdate == null) {
                LOGGER.error("Fetching user failed.\n");
                return false;
            }

            LOGGER.debug("Fetching user done.\n");

            Criteria roleCriteria = session.createCriteria(Role.class);

            if (roleCriteria == null) {
                LOGGER.error("Role criteria creation failed.\n");
                return false;
            }

            LOGGER.debug("Role criteria creation done.\n");

            roleCriteria.add(Restrictions.like("roleType", roleType));

            Role roleToRevoke = (Role) roleCriteria.uniqueResult();

            if (roleToRevoke == null) {
                LOGGER.error("Fetching role failed.\n");
                return false;
            }

            LOGGER.debug("Fetching role done.\n");

            Hibernate.initialize(userToUpdate.getRoles());

            if (userToUpdate.getRoles().remove(roleToRevoke)) {
                session.update(userToUpdate);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            LOGGER.error(e, e);
        } finally {
            transaction.commit();
            session.close();
        }
        return false;
    }
}
