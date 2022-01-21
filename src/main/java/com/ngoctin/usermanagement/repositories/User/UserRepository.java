package com.ngoctin.usermanagement.repositories.User;

import com.ngoctin.usermanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>, MyUserRepository {
}
