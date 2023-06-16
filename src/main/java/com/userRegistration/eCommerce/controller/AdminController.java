package com.userRegistration.eCommerce.controller;

import com.userRegistration.eCommerce.Exception.ResourceNotFoundException;
import com.userRegistration.eCommerce.dao.UserDao;
import com.userRegistration.eCommerce.entity.User;
import com.userRegistration.eCommerce.service.AdminService;
import com.userRegistration.eCommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
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
    public ResponseEntity<User>  registerNewAdmin(@RequestBody User admin)
    {
        String email = admin.getGmail();
        String phoneNumber = admin.getPhoneNumber();

        if (!adminService.isValidEmail(email)) {
            // Email is not valid
            throw new ResourceNotFoundException("Invalid email address.");
        }

        if (adminService.isExistingEmail(email)) {
            // Email already exists
            throw new ResourceNotFoundException("Email address already exists.");
        }

        if (!adminService.isValidPhoneNumber(phoneNumber)) {
            // Phone number is not valid
            throw new ResourceNotFoundException("Invalid phone number.");
        }
        return new ResponseEntity<User>(adminService.registerNewAdmin(admin), HttpStatus.CREATED)  ;
    }

    @PostMapping({"/registerUserByAdmin"})
    public ResponseEntity<User> registerUserByAdmin(@RequestBody User user){
        String email = user.getGmail();
        String phoneNumber = user.getPhoneNumber();

        if (!adminService.isValidEmail(email)) {
            // Email is not valid
            throw new ResourceNotFoundException("Invalid email address.");
        }

        if (adminService.isExistingEmail(email)) {
            // Email already exists
            throw new ResourceNotFoundException("Email address already exists.");
        }

        if (!adminService.isValidPhoneNumber(phoneNumber)) {
            // Phone number is not valid
            throw new ResourceNotFoundException("Invalid phone number.");
        }
       return new ResponseEntity<User>(adminService.registerUser(user),HttpStatus.CREATED);
    }
    @GetMapping({"/getUsers"})
    public List<User> getUsers(){
        return adminService.getUsers();
    }
    @DeleteMapping("/deleteUser/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        adminService.deleteUser(userId);
    }
    @PutMapping({"/updateUser"})
    public ResponseEntity<User> updateUser(@RequestBody User user){
        String email = user.getGmail();
        String phoneNumber = user.getPhoneNumber();

        if (!adminService.isValidEmail(email)) {
            // Email is not valid
            throw new ResourceNotFoundException("Invalid email address.");
        }
        if (adminService.isExistingEmailUpdate(email, user.getUserName())) {
            // Existing user with the same email
            throw new ResourceNotFoundException("Email already exists for another user.");
        }
        if (!adminService.isValidPhoneNumber(phoneNumber)) {
            // Phone number is not valid
            throw new ResourceNotFoundException("Invalid phone number.");
        }
      return new ResponseEntity<User>(adminService.updateUser(user),HttpStatus.CREATED)  ;
    }

}
