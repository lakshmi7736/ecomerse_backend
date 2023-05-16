package com.userRegistration.eCommerce.controller;

import com.userRegistration.eCommerce.entity.User;
import com.userRegistration.eCommerce.service.AdminService;
import com.userRegistration.eCommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping({"/registerUser"})
    public User registerUser(@RequestBody User user){
       return adminService.registerUser(user);
    }
    @GetMapping({"/getUsers"})
    public List<User> getUsers(){
        return adminService.getUsers();
    }
}
