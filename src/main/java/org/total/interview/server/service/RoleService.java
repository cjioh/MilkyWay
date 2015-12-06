package org.total.interview.server.service;

import org.total.interview.server.dao.RoleDAO;
import org.total.interview.server.model.Role;

import java.util.List;

/**
 * Created by total on 12/5/15.
 */
public class RoleService {

    private static RoleDAO roleDAO;

    public RoleService() {
        roleDAO = new RoleDAO();
    }

    public void persist(Role entity) {
        roleDAO.openCurrentSessionwithTransaction();
        roleDAO.persist(entity);
        roleDAO.closeCurrentSessionwithTransaction();
    }

    public void update(Role entity) {
        roleDAO.openCurrentSessionwithTransaction();
        roleDAO.update(entity);
        roleDAO.closeCurrentSessionwithTransaction();
    }

    public Role findById(Long id) {
        roleDAO.openCurrentSession();
        Role role = roleDAO.findById(id);
        roleDAO.closeCurrentSession();
        return role;
    }

    public Role findByRoleTitle(String roleTitle) {
        roleDAO.openCurrentSession();
        Role role = roleDAO.findByName(roleTitle);
        roleDAO.closeCurrentSession();
        return role;
    }

    public void delete(Long id) {
        roleDAO.openCurrentSessionwithTransaction();
        Role role = roleDAO.findById(id);
        roleDAO.delete(role);
        roleDAO.closeCurrentSessionwithTransaction();
    }

    public List<Role> findAll() {
        roleDAO.openCurrentSession();
        List<Role> roles = roleDAO.findAll();
        roleDAO.closeCurrentSession();
        return roles;
    }

    public void deleteAll() {
        roleDAO.openCurrentSessionwithTransaction();
        roleDAO.deleteAll();
        roleDAO.closeCurrentSessionwithTransaction();
    }

    public RoleDAO userDAO() {
        return roleDAO;
    }
}
