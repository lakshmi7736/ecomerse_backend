package com.userRegistration.eCommerce.dao;

import com.userRegistration.eCommerce.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, String> {
    Role findByRoleName(String roleName);
}
