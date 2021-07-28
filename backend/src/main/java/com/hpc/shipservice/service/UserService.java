package com.hpc.shipservice.service;

import com.hpc.shipservice.entities.User;
import com.hpc.shipservice.entities.UserDto;

import java.util.List;

public interface UserService {
    User save(UserDto user);
    List<User> findAll();
    User findOne(String username);
}
