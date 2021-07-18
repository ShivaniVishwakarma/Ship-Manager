package com.hpc.shipservice.service;

import com.hpc.shipservice.entity.Ship;
import com.hpc.shipservice.models.Response;
import com.hpc.shipservice.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

@Service
public class ShipService {

    @Autowired
    ShipRepository shipRepository;

    public ResponseEntity<Response> addNewShipInfo(Ship ship){
        Response response = new Response();
        Optional<Ship> s = shipRepository.findByShipCode(ship.getShipCode());

        if (!s.isPresent()) {
            Ship s1 = shipRepository.save(ship);
            generateShipCode(1);
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
            Ship savedShip = shipRepository.save(ship);

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

    public void generateShipCode(Integer ide){
        int id = 20;
        int max = 18;
        HashMap<Integer, String> keys = new HashMap<>();
        keys.put(1,"A1");
        keys.put(2,"A2");
        keys.put(3,"A3");
        keys.put(4,"A4");
        keys.put(5,"A5");
        keys.put(6,"A6");
        keys.put(7,"A7");
        keys.put(8,"A8");
        keys.put(9,"A9");
        keys.put(10,"B1");
        keys.put(11,"B2");
        keys.put(12,"B3");
        keys.put(13,"B4");
        keys.put(14,"B5");
        keys.put(15,"B6");
        keys.put(16,"B7");
        keys.put(17,"B8");
        keys.put(18,"B9");

        int i  = (Integer) (id/max) + 1000;
        System.out.println("i - " + i);

        int temp = id%max;
        System.out.println("temp - " + temp);

        String c = keys.get(temp);
        System.out.println("c - " + c);

        String key = "SHIP-" + i + "-" + c;
        System.out.println("Shipcode - " + key);
    }
}

