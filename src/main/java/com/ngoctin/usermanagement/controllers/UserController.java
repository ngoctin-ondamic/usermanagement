package com.ngoctin.usermanagement.controllers;

import com.ngoctin.usermanagement.entities.User;
import com.ngoctin.usermanagement.models.UserResponse;
import com.ngoctin.usermanagement.models.UserUpdateNameRequest;
import com.ngoctin.usermanagement.services.User.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping(path = "/user/create")
    public ResponseEntity<?> createUser(@RequestBody User user){
        User responseUser = userService.createUser(user);
        if(responseUser != null){
            return ResponseEntity.status(HttpStatus.OK).body(responseUser);
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Created Failed !");
    }

    @PutMapping("/user/updateNameByID")
    public ResponseEntity<?> updateNameByID(@RequestBody UserUpdateNameRequest userUpdateNameRequest){
        if(userService.updateNameByID(userUpdateNameRequest.getId(),userUpdateNameRequest.getName())){
            return ResponseEntity.status(HttpStatus.OK).body("Updated Successfully !");
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Updated Failed !");
    }


    @PutMapping(path = "/user/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user){
        boolean check = userService.updateUser(id, user);
        log.info("Id : {}",id);
        log.info("User : {}",user.toString());
        log.warn("Check : {}",check);
        if(check){
            return ResponseEntity.status(HttpStatus.OK).body("Update Successfully !");
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Update Failed !");
    }

    @PutMapping(path ="/user/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        if(userService.deletedUser(id)){
            return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully !");
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Deleted Successfully !");
    }

    @GetMapping(path = "/user/getByID/{id}")
    public ResponseEntity<?> getByID(@PathVariable("id") Long id){
        User user = userService.getByID(id);
        if(user != null){
            UserResponse userResponse = new UserResponse(user.getName(),user.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body(userResponse);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found !");
    }

    @GetMapping(path = "/users/getAll")
    public ResponseEntity<?> getAllUsers(){
        List<User> users = userService.getAllUsers();
        if(users != null && users.size() > 0){
            return ResponseEntity.status(HttpStatus.OK).body(users);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Users is empty !");
    }

}
