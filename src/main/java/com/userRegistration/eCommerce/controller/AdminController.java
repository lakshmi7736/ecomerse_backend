package com.userRegistration.eCommerce.controller;

import com.userRegistration.eCommerce.dao.UserDao;
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
    public User registerNewAdmin(@RequestBody User admin)
    {
        String email = admin.getGmail();
        String phoneNumber = admin.getPhoneNumber();

        if (!adminService.isValidEmail(email)) {
            // Email is not valid
            throw new IllegalArgumentException("Invalid email address.");
        }

        if (adminService.isExistingEmail(email)) {
            // Email already exists
            throw new IllegalArgumentException("Email address already exists.");
        }

        if (!adminService.isValidPhoneNumber(phoneNumber)) {
            // Phone number is not valid
            throw new IllegalArgumentException("Invalid phone number.");
        }
        return adminService.registerNewAdmin(admin);
    }

    @PostMapping({"/registerUserByAdmin"})
    public User registerUserByAdmin(@RequestBody User user){
        String email = user.getGmail();
        String phoneNumber = user.getPhoneNumber();

        if (!adminService.isValidEmail(email)) {
            // Email is not valid
            throw new IllegalArgumentException("Invalid email address.");
        }

        if (adminService.isExistingEmail(email)) {
            // Email already exists
            throw new IllegalArgumentException("Email address already exists.");
        }

        if (!adminService.isValidPhoneNumber(phoneNumber)) {
            // Phone number is not valid
            throw new IllegalArgumentException("Invalid phone number.");
        }
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
        String email = user.getGmail();
        String phoneNumber = user.getPhoneNumber();

        if (!adminService.isValidEmail(email)) {
            // Email is not valid
            throw new IllegalArgumentException("Invalid email address.");
        }
        if (adminService.isExistingEmailUpdate(email, user.getUserName())) {
            // Existing user with the same email
            throw new IllegalArgumentException("Email already exists for another user.");
        }
        if (!adminService.isValidPhoneNumber(phoneNumber)) {
            // Phone number is not valid
            throw new IllegalArgumentException("Invalid phone number.");
        }
      return  adminService.updateUser(user);
    }

}
