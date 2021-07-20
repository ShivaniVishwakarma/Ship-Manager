package com.hpc.shipservice.repository;

import com.hpc.shipservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.username =:username")
    public Optional<User> getUserByUsername(@Param("username") String username);
}
