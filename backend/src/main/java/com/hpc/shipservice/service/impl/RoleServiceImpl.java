package com.hpc.shipservice.service.impl;

import com.hpc.shipservice.dao.RoleDao;
import com.hpc.shipservice.entities.Role;
import com.hpc.shipservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findByName(String name) {
        Role role = roleDao.findRoleByName(name);
        return role;
    }
}
