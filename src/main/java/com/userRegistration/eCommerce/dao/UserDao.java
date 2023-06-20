package com.userRegistration.eCommerce.dao;


import com.userRegistration.eCommerce.entity.Role;
import com.userRegistration.eCommerce.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends CrudRepository<User, Long> {

    List<User> findByRole(Role role);
    boolean existsByGmail(String gmail);
    User findByGmail(String gmail);
    User findByUserName(String userName);

    User findByPhoneNumber(String phoneNumber);

}
