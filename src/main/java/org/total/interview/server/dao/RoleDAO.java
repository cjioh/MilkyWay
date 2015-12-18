package org.total.interview.server.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.total.interview.server.model.Role;
import org.total.interview.server.model.RoleType;

import java.util.List;

public class RoleDAO implements DAOInterface<Role, Long> {

    private Session session;
    private Transaction transaction;

    private SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());

        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

        return sessionFactory;

    }

    private Session openSessionWithTransaction() {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        return session;
    }

    private void closeSessionWithCommitTransaction() {
        transaction.commit();
        session.close();
    }

    public Role findById(Long id) {
        session = getSessionFactory().openSession();
        Role role = (Role) session.get(Role.class, id);
        session.close();
        return role;
    }

    public Role findByRoleType(RoleType roleType) {
        session = getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Role.class);
        criteria.add(Restrictions.like("roleType", roleType));
        Role role = (Role) criteria.uniqueResult();
        session.close();
        return role;
    }

    public List<Role> findAll() {
        session = getSessionFactory().openSession();
        List<Role> roles = (List<Role>) session.createQuery("FROM Role").list();
        session.close();
        return roles;
    }

    public void persist(Role entity) {
        session = openSessionWithTransaction();
        session.save(entity);
        closeSessionWithCommitTransaction();
    }

    public void update(Role entity) {
        session = openSessionWithTransaction();
        session.update(entity);
        closeSessionWithCommitTransaction();
    }

    public void delete(Role entity) {
        session = openSessionWithTransaction();
        session.delete(entity);
        closeSessionWithCommitTransaction();
    }

    public void deleteAll() {
        session = openSessionWithTransaction();
        List<Role> entityList = findAll();
        for (Role entity : entityList) {
            delete(entity);
        }
        closeSessionWithCommitTransaction();
    }

}
