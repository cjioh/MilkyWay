package org.total.interview.server.service;

import org.total.interview.server.dao.RoleDAO;
import org.total.interview.server.model.Role;
import org.total.interview.server.model.RoleType;

import java.util.List;

/**
 * Created by total on 12/5/15.
 */
public class RoleService {

    private RoleDAO roleDAO;

    public RoleService() {
        roleDAO = new RoleDAO();
    }

    public Role findById(Long id) {
        return roleDAO.findById(id);
    }

    public Role findByRoleType(RoleType roleType) {
        return roleDAO.findByRoleType(roleType);
    }

    public List<Role> findAll() {
        return roleDAO.findAll();
    }

    public void persist(Role entity) {
        roleDAO.persist(entity);
    }

    public void update(Role entity) {
        roleDAO.update(entity);
    }

    public void delete(Long id) {
        Role role = roleDAO.findById(id);
        roleDAO.delete(role);
    }

    public void deleteAll() {
        roleDAO.deleteAll();
    }

}
