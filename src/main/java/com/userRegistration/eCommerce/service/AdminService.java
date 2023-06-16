package com.userRegistration.eCommerce.service;

import com.userRegistration.eCommerce.Exception.ResourceNotFoundException;
import com.userRegistration.eCommerce.dao.RoleDao;
import com.userRegistration.eCommerce.dao.UserDao;
import com.userRegistration.eCommerce.entity.Role;
import com.userRegistration.eCommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AdminService {
@Autowired
    private UserDao userDao;
@Autowired
private RoleDao roleDao;
@Autowired
private PasswordEncoder passwordEncoder;


    public User registerUser(User user) {
        // Validate inputs
        if (!isValidEmail(user.getGmail())) {
            throw new ResourceNotFoundException("Invalid gmail");
        }

        if (!isValidPhoneNumber(user.getPhoneNumber())) {
            throw new ResourceNotFoundException("Invalid phone number");
        }

        Role role= roleDao.findById("User").get();
        Set<Role> roleSet =new HashSet<>();
        roleSet.add(role);
        user.setRole(roleSet);

        String password=getEncodedPassword(user.getUserPassword());
        user.setUserPassword(password);
        return userDao.save(user);
    }

    public User registerNewAdmin(User admin){

        Role role= roleDao.findById("Admin").get();
        Set<Role> roleSet =new HashSet<>();
        roleSet.add(role);

        admin.setRole(roleSet);
        String password=getEncodedPassword(admin.getUserPassword());
        admin.setUserPassword(password);
        return userDao.save(admin);
    }


    public List<User> getUsers(){
        Role userRole = roleDao.findByRoleName("User");
        return userDao.findByRole(userRole);
    }

    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }


    public void deleteUser(Long userId) { userDao.deleteById(userId); }


    public User updateUser(User user){

        // Validate inputs
        if (!isValidEmail(user.getGmail())) {
            throw new ResourceNotFoundException("Invalid email format");
        }
        if (!isValidPhoneNumber(user.getPhoneNumber())) {
            throw new ResourceNotFoundException("Invalid phone number");
        }
        Long userId= user.getUserId();
       User sr=userDao.findById(userId).get();
       sr.setUserName(user.getUserName());
       sr.setUserFirstName(user.getUserFirstName());
       sr.setUserLastName(user.getUserLastName());
       sr.setGmail(user.getGmail());
       sr.setPhoneNumber(user.getPhoneNumber());
        return userDao.save(sr);
    }


    public boolean isValidEmail(String email) {
        // Define the pattern to match a valid email address
        String emailRegex = "^[\\w\\.-]+@gmail\\.com$";
        Pattern pattern = Pattern.compile(emailRegex);

        // Match the given email address with the pattern
        Matcher matcher = pattern.matcher(email);

        // Return true if the email address matches the pattern, false otherwise
        return matcher.matches();
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        if (StringUtils.isEmpty(phoneNumber)) {
            return false;
        }
        String phoneRegex = "^[6-9]\\d{9}$";
        return Pattern.matches(phoneRegex, phoneNumber);
    }
    public boolean isExistingEmail(String email){
        // Check if email already exists
        return userDao.existsByGmail(email);
    }

    public boolean isExistingEmailUpdate(String email, String username) {
        User existingUser = userDao.findByGmail(email);
        return existingUser != null && !existingUser.getUserName().equals(username);
    }

}
