package com.hpc.shipservice.service;

import com.hpc.shipservice.entities.Role;

public interface RoleService {
    Role findByName(String name);
}
