package org.total.interview.server.management;

import org.total.interview.server.entity.Role;
import org.total.interview.server.entity.User;

import java.util.List;
import java.util.Set;

/**
 * Created by pavlo.fandych on 12/3/2015.
 */
public interface UserManager {
    public Integer addUserByUserName(String userName, Set<Role> roles);

    public List<User> getAllUsers();

    public void updateUserById(Integer userID, String userName);

    public void deleteUserById(Integer userID);
}
