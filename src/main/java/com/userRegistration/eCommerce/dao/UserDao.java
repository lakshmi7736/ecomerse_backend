package com.userRegistration.eCommerce.dao;


import com.userRegistration.eCommerce.entity.Role;
import com.userRegistration.eCommerce.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends CrudRepository<User,String> {
//    User findByUserName(String userName);
    List<User> findByRole(Role role);
}
