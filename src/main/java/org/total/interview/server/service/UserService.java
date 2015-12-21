package org.total.interview.server.service;

import org.total.interview.server.dao.UserDAO;
import org.total.interview.server.model.User;

import java.util.List;

/**
 * Created by total on 12/5/15.
 */
public class UserService {

    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public User findById(Long id) {
        return userDAO.findById(id);
    }

    public User findByName(String name) {
        return userDAO.findByName(name);
    }

    public List<User> findByUserNameAndPassword(String userName, String password) {
        return userDAO.findByUserNameAndPassword(userName, password);
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public void persist(User entity) {
        userDAO.persist(entity);
    }

    public void deleteById(Long id) {
        User user = userDAO.findById(id);
        userDAO.delete(user);
    }

    public void deleteAll() {
        userDAO.deleteAll();
    }

}
