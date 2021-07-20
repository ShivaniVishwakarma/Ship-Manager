package com.hpc.shipservice.service;

import com.hpc.shipservice.entity.Ship;
import com.hpc.shipservice.models.Response;
import com.hpc.shipservice.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class ShipService {

    @Autowired
    Map<Integer,String> codes;


    @Autowired
    ShipRepository shipRepository;

    public ResponseEntity<Response> addNewShipInfo(Ship ship){
        Response response = new Response();
        Optional<Ship> s = shipRepository.findByShipCode(ship.getShipCode());

        if (!s.isPresent()) {
            Ship newShip = shipRepository.save(ship);
            String code = generateShipCode(newShip.getId());
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


    public String generateShipCode(Integer id){
      //  int id = 2;
        int max = 18;

        //test to chk duplicates - Executor service

        int i  = (Integer) (id/max) + 1000; //alternate of division operator
        //System.out.println("i - " + i);

        int temp = id%max; //alternative
        //System.out.println("temp - " + temp);

        String c = codes.get(temp);
       // System.out.println("c - " + c);

        String key = "SHIP-" + i + "-" + c;
        System.out.println("Shipcode - " + key);
        return key;
    }
}

