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
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PasswordEncoder passwordEncoder;



        public User registerNewAdmin(User admin){

       Role role= roleDao.findById("Admin").get();
       Set<Role> roleSet =new HashSet<>();
       roleSet.add(role);

       admin.setRole(roleSet);
       String password=getEncodedPassword(admin.getUserPassword());
       admin.setUserPassword(password);
       return userDao.save(admin);
    }

    public User registerNewUser(User user){

        Role role= roleDao.findById("User").get();
        Set<Role> roleSet =new HashSet<>();
        roleSet.add(role);
        user.setRole(roleSet);

        String password=getEncodedPassword(user.getUserPassword());
        user.setUserPassword(password);
        return userDao.save(user);
    }




    public void initRolesAndUsers(){
        Role adminRole=new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);


        Role userRole=new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created user");
        roleDao.save(userRole);


        User adminUser = new User();
        adminUser.setUserName("admin@123");
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        adminUser.setUserPassword(getEncodedPassword("admin.og@123"));
        Set<Role> adminRoles=new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);


        User user = new User();
        user.setUserName("lakshmi");
        user.setUserFirstName("lakshmi");
        user.setUserLastName("mi");
        user.setUserPassword(getEncodedPassword("1122"));
        Set<Role> userRoles=new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        userDao.save(user);
    }



    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }


}
