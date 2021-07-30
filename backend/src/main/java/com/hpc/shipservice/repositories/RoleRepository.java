package com.hpc.shipservice.repositories;

import com.hpc.shipservice.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A JPA repository for handling the CRUD operations on a Role entity
 * @author Shivani Vishwakarma
 * @since 29.07.21
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
}
