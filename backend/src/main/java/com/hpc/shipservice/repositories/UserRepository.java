package com.hpc.shipservice.repositories;

import com.hpc.shipservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A JPA repository for handling the CRUD operations on a User entity
 * @author Shivani Vishwakarma
 * @since 29.07.21
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
