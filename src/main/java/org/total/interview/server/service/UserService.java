package org.total.interview.server.service;

import org.total.interview.server.dao.UserDAO;
import org.total.interview.server.model.User;

import java.util.List;

/**
 * Created by total on 12/5/15.
 */
public class UserService {

    private static UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public void persist(User entity) {
        userDAO.openCurrentSessionwithTransaction();
        userDAO.persist(entity);
        userDAO.closeCurrentSessionwithTransaction();
    }

    public void update(User entity) {
        userDAO.openCurrentSessionwithTransaction();
        userDAO.update(entity);
        userDAO.closeCurrentSessionwithTransaction();
    }

    public User findById(Long id) {
        userDAO.openCurrentSession();
        User user = userDAO.findById(id);
        userDAO.closeCurrentSession();
        return user;
    }

    public User findByName(String name) {
//        userDAO.openCurrentSession();
        User user = userDAO.findByName(name);
//        userDAO.closeCurrentSession();
        return user;
    }

    public void delete(Long id) {
        userDAO.openCurrentSessionwithTransaction();
        User user = userDAO.findById(id);
        userDAO.delete(user);
        userDAO.closeCurrentSessionwithTransaction();
    }

    public List<User> findAll() {
        userDAO.openCurrentSession();
        List<User> users = userDAO.findAll();
        userDAO.closeCurrentSession();
        return users;
    }

    public void deleteAll() {
        userDAO.openCurrentSessionwithTransaction();
        userDAO.deleteAll();
        userDAO.closeCurrentSessionwithTransaction();
    }

    public UserDAO userDAO() {
        return userDAO;
    }

}
