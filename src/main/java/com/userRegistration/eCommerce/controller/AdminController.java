package com.userRegistration.eCommerce.controller;

import com.userRegistration.eCommerce.entity.User;
import com.userRegistration.eCommerce.service.AdminService;
import com.userRegistration.eCommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController

public class AdminController {
    @Autowired
    private UserService userService;
    @PostConstruct
    public void initRolesAndUsers(){
        userService.initRolesAndUsers();
    }
    @Autowired
    private AdminService adminService;

    @PostMapping({"/registerNewAdmin"})
    public User registerNewAdmin(@RequestBody User admin){
        return userService.registerNewAdmin(admin);
    }

    @PostMapping({"/registerUser"})
    public User registerUser(@RequestBody User user){
       return adminService.registerUser(user);
    }
    @GetMapping({"/getUsers"})
    public List<User> getUsers(){
        return adminService.getUsers();
    }
    @DeleteMapping("/deleteUser/{userName}")
    public void deleteUser(@PathVariable("userName") String userName) {
        adminService.deleteUser(userName);
    }
    @PutMapping({"/updateUser"})
    public User updateUser(@RequestBody User user){
      return   adminService.updateUser(user);

    }

}
