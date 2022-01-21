package com.ngoctin.usermanagement.services.User;

import com.ngoctin.usermanagement.entities.User;

import java.util.List;

public interface UserService {

    User createUser(User user);
    boolean updateUser(Long id, User user);
    boolean deletedUser(Long id);
    List<User> getAllUsers();
    User getByID(Long id);
    Boolean updateNameByID(Long id, String name);

}
