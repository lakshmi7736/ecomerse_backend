package com.userRegistration.eCommerce.controller;

import com.userRegistration.eCommerce.Exception.ResourceNotFoundException;
import com.userRegistration.eCommerce.entity.User;
import com.userRegistration.eCommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostConstruct
    public void initRolesAndUsers(){
        userService.initRolesAndUsers();
    }


    @PostMapping("/registerNewUser")
    public ResponseEntity<User> registerNewUser(@RequestBody User user) {
        String email = user.getGmail();
        String phoneNumber = user.getPhoneNumber();

        if (!userService.isValidEmail(email)) {
            // Email is not valid
            throw new ResourceNotFoundException("Invalid email address.");

        }

        if (userService.isExistingEmail(email)) {
            // Email already exists
            throw new ResourceNotFoundException("Email address already exists.");
        }

        if (!userService.isValidPhoneNumber(phoneNumber)) {
            // Phone number is not valid
            throw new ResourceNotFoundException("Invalid phone number.");
        }

        // All conditions are satisfied, proceed with user registration
        return new ResponseEntity<User>(userService.registerNewUser(user), HttpStatus.CREATED) ;
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

}
