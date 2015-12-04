package org.total.interview.server.management;

import org.total.interview.server.entity.User;

import java.util.List;

public interface UserManager {

    public long addUser(User user);

    public List<User> getAllUsers();

    public User updateUser(User user);

    public boolean deleteUserById(long userID);

    public User getUserById(long userID);

    public User getUserByName(String userName);

}