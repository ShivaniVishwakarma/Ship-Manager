package com.hpc.shipservice.service.impl;

import com.hpc.shipservice.repositories.RoleRepository;
import com.hpc.shipservice.entities.Role;
import com.hpc.shipservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for handing role related operations
 * @author Shivani Vishwakarma
 * @since 29.07.21
 */

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Finds the role information by given name
     * @param name
     * @return Role
     */
    @Override
    public Role findByName(String name) {
        Role role = roleRepository.findRoleByName(name);
        return role;
    }
}
