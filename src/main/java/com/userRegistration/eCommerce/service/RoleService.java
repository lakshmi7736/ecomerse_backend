package com.userRegistration.eCommerce.service;

import com.userRegistration.eCommerce.dao.RoleDao;

import com.userRegistration.eCommerce.entity.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;
    public Role createNewRole(Role role){
       return roleDao.save(role);
    }


}
