package com.hpc.shipservice.service;

import com.hpc.shipservice.entity.Ship;
import com.hpc.shipservice.entity.User;
import com.hpc.shipservice.models.AuthenticationRequest;
import com.hpc.shipservice.models.Response;
import com.hpc.shipservice.repository.ShipRepository;
import com.hpc.shipservice.repository.UserRepository;
import com.hpc.shipservice.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ShipService {

    @Autowired
    ShipCodeGenerator shipCodeGenerator;

    @Autowired
    ShipRepository shipRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public ResponseEntity<Response> addNewShipInfo(Ship ship){
        Response response = new Response();
        Optional<Ship> s = shipRepository.findByShipCode(ship.getShipCode());

        if (!s.isPresent()) {
            Ship newShip = shipRepository.save(ship);
            String code = shipCodeGenerator.generateShipCode(newShip.getId());
            Optional<Ship> s1 = shipRepository.findById(newShip.getId());
            newShip.setShipCode(code);
            if(s1.isPresent()) {
                shipRepository.save(newShip);
            }
            response.setMessage("Ship Added Successfully");
            response.setStatus(true);
        } else {
            response.setMessage("Ship already exists with " + ship.getShipCode());
        }
        return ResponseEntity.ok(response);
    }

    public Collection<Ship> getShips() {
        return shipRepository.findAll();
    }

    public Optional<Ship> getShipsByShipCode(String shipCode) {
        return shipRepository.findByShipCode(shipCode);
    }

    public ResponseEntity<?> updateShipInfo(Ship ship) {
        Response response = new Response();
        Optional<Ship> s = shipRepository.findByShipCode(ship.getShipCode());
        if (s.isPresent()) {
            shipRepository.save(ship);
            response.setMessage("Ship Updated Successfully");
            response.setStatus(true);
        } else {
            response.setMessage("Ship does not exists with " + ship.getShipCode());
        }
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> deleteShipInfo(String shipCode) {
        Response response = new Response();
        Optional<Ship> s = shipRepository.findByShipCode(shipCode);

        if (!s.isPresent()) {
            response.setMessage("Ship does not exist");
        } else {
            shipRepository.deleteById(shipCode);
            response.setMessage("Ship deleted successfully");
        }
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> authenticate(AuthenticationRequest u) {
        Response response = new Response();
        String jwt = null;
        System.out.println(bCryptPasswordEncoder.encode(u.getPassword()));
        Optional<User> user = userRepository.getUserByUsername(u.getUsername(), bCryptPasswordEncoder.encode(u.getPassword()));
        if(!user.isPresent()){
            response.setMessage("Invalid username or password");
        }else{
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(u.getUsername(), u.getPassword()));
            }catch(BadCredentialsException e){
                e.printStackTrace();
                response.setMessage("Invalid password");
            }
            UserDetails userDetails = userDetailsService.loadUserByUsername(u.getUsername());
            System.out.println(userDetails);
            jwt = jwtTokenUtil.generateToken(userDetails);
            response.setData(jwt);
            response.setMessage("Authentication success");
            System.out.println(jwt);
        }
        return ResponseEntity.ok(response);
    }

    //$2a$10$2SKDbWdrk3TLV0LiS5KJ2uHvCadmjvChu8FN2EVtloK3yob9mXfxq

}

