package com.userRegistration.eCommerce.controller;

import com.userRegistration.eCommerce.entity.User;
import com.userRegistration.eCommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostConstruct
    public void initRolesAndUsers(){
        userService.initRolesAndUsers();
    }


        @PostMapping({"/registerNewUser"})
     public User registerNewUser(@RequestBody User user){
       return userService.registerNewUser(user);
    }

    @PostMapping({"/registerNewAdmin"})
    public User registerNewAdmin(@RequestBody User admin){
        return userService.registerNewAdmin(admin);
    }
    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This url is only for admins";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasAnyRole('Admin','User')")
    public String forUsers(){

        return "This url is only for users";
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsersByRole() {
        return userService.getAllUsersByRole();
    }

//    @DeleteMapping({"/deleteUserDetails/{userName}"})
//    public void deleteUserDetails(@PathVariable("userName")String userName){
//        userService.deleteUserDetails(userName);
//    }
}