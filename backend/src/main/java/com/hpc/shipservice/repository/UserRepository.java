package com.hpc.shipservice.repository;

import com.hpc.shipservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.username =:username and u.password =:password")
    public Optional<User> getUserByUsername(@Param("username") String username, @Param("password") String password);

    public Optional<User> findUserByUsername(String username);
}
