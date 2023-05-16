package com.userRegistration.eCommerce.service;

import com.userRegistration.eCommerce.dao.RoleDao;
import com.userRegistration.eCommerce.dao.UserDao;
import com.userRegistration.eCommerce.entity.Role;
import com.userRegistration.eCommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AdminService {
@Autowired
    private UserDao userDao;
@Autowired
private RoleDao roleDao;
@Autowired
private PasswordEncoder passwordEncoder;


    public User registerUser(User user) {
        Role role= roleDao.findById("User").get();
        Set<Role> roleSet =new HashSet<>();
        roleSet.add(role);
        user.setRole(roleSet);

        String password=getEncodedPassword(user.getUserPassword());
        user.setUserPassword(password);
        return userDao.save(user);
    }

    public List<User> getUsers(){
        Role userRole = roleDao.findByRoleName("User");
        return userDao.findByRole(userRole);
    }



    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }


}
