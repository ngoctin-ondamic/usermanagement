package com.ngoctin.usermanagement.services.User;

import com.ngoctin.usermanagement.entities.User;
import com.ngoctin.usermanagement.repositories.User.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        try {
            return userRepository.save(user);
        }catch (Exception exception){
            return null;
        }
    }

    @Override
    public boolean updateUser(Long id, User user) {
        try {
            boolean check = userRepository.updateUser(id,user);
            log.warn("Check : {}",check);
            return check;
        }catch (Exception exception){
            return false;
        }
    }

    @Override
    public boolean deletedUser(Long id) {
        try {
            return userRepository.deleteUser(id);
        }catch (Exception exception){
            return false;
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        }catch (Exception exception){
            return null;
        }
    }

    @Override
    public User getByID(Long id) {
        try {
            return userRepository.findById(id).get();
        }catch (Exception exception){
            return null;
        }
    }

    @Override
    public Boolean updateNameByID(Long id, String name) {
        try {
            if(userRepository.updateNameByID(id, name)){
                return true;
            }
            return false;
        }catch (Exception exception){
            log.warn("catch");
            return false;
        }
    }
}
