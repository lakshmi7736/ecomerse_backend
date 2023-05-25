    package com.userRegistration.eCommerce.service;

    import com.userRegistration.eCommerce.dao.RoleDao;
    import com.userRegistration.eCommerce.dao.UserDao;
    import com.userRegistration.eCommerce.entity.Role;
    import com.userRegistration.eCommerce.entity.User;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;
    import org.springframework.util.StringUtils;


    import java.util.HashSet;

    import java.util.Set;
    import java.util.regex.Matcher;
    import java.util.regex.Pattern;


    @Service
    public class UserService {
        @Autowired
        private UserDao userDao;
        @Autowired
        private RoleDao roleDao;
        @Autowired
        private PasswordEncoder passwordEncoder;


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
            adminUser.setGmail("admin.og@gmail.com");
            adminUser.setPhoneNumber("8075842670");
            Set<Role> adminRoles=new HashSet<>();
            adminRoles.add(adminRole);
            adminUser.setRole(adminRoles);
            userDao.save(adminUser);


            User user = new User();
            user.setUserName("lakshmi");
            user.setUserFirstName("lakshmi");
            user.setUserLastName("mi");
            user.setGmail("lakshmilaksh7736@gmail.com");
            user.setPhoneNumber("7736225705");
            user.setUserPassword(getEncodedPassword("111222"));
            Set<Role> userRoles=new HashSet<>();
            userRoles.add(userRole);
            user.setRole(userRoles);
            userDao.save(user);
        }



        public String getEncodedPassword(String password){
            return passwordEncoder.encode(password);
        }


        public boolean isExistingEmail(String email){
            // Check if email already exists
            return userDao.existsByGmail(email);
        }

        public boolean isValidPhoneNumber(String phoneNumber) {
            if (StringUtils.isEmpty(phoneNumber)) {
                return false;
            }
            String phoneRegex = "^[6-9]\\d{9}$";
            return Pattern.matches(phoneRegex, phoneNumber);
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

    }


