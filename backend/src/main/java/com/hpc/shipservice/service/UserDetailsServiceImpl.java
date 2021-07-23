package com.hpc.shipservice.service;

import com.hpc.shipservice.models.ShipUserDetails;
import com.hpc.shipservice.entity.User;
import com.hpc.shipservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<User> user = this.userRepository.findUserByUsername(s);
        return user.map(ShipUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User does not exists"));
    }

}
