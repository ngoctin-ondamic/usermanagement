package com.ngoctin.usermanagement.repositories.User;

import com.ngoctin.usermanagement.entities.User;
import org.springframework.data.jpa.repository.Query;

public interface MyUserRepository {

    boolean updateUser(Long id, User user);
    boolean deleteUser(Long id);
    boolean updateNameByID(Long id, String name);
}
