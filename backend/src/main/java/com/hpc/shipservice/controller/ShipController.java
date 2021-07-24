package com.hpc.shipservice.controller;

import com.hpc.shipservice.entity.Ship;
import com.hpc.shipservice.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/ships")
public class ShipController {


    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ShipService shipService;

    //Add new ship info
    @PostMapping("/add")
    public ResponseEntity<?> addNewShipInfo(@RequestBody Ship ship){
        return shipService.addNewShipInfo(ship);
    }

    //Fetch all ships info
    @GetMapping("/getall")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public Collection<Ship> getShips() {
        System.out.println(bCryptPasswordEncoder.encode("admin"));
        return shipService.getShips();
    }

    //Fetch ship by ShipCode
    @GetMapping("/get/{shipCode}")
    public Optional<Ship> getShipsByShipCode(@PathVariable("shipCode") String shipCode) {
        return shipService.getShipsByShipCode(shipCode);
    }

    //Update ship by shipCode
    @PutMapping("/update")
    public ResponseEntity<?> updateShipInfo(@RequestBody Ship ship){
        return shipService.updateShipInfo(ship);
    }

    //Delete ship by ShipCode
    @DeleteMapping("/delete/{shipCode}")
    public ResponseEntity<?> deleteShipInfo(@PathVariable("shipCode") String shipCode){
        return shipService.deleteShipInfo(shipCode);
    }


}
