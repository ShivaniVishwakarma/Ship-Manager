package com.hpc.shipservice.service;

import com.hpc.shipservice.entity.Ship;
import com.hpc.shipservice.entity.User;
import com.hpc.shipservice.models.Response;
import com.hpc.shipservice.repository.ShipRepository;
import com.hpc.shipservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<?> authenticate(User u) {
        Response response = new Response();
        Optional<User> user = userRepository.getUserByUsername(u.getUsername());
        if(!user.isPresent()){
            response.setMessage("Username does not exist");
        }else if(!user.get().getPassword().equals(u.getPassword())){
            response.setMessage("Password incorrect");
        }else{
            response.setMessage("Login successful");
            generateToken(user.get());
        }
        return ResponseEntity.ok(response);
    }

    private void generateToken(User user) {
    }
}

