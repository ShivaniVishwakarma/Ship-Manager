package com.hpc.shipservice.controller;

import com.hpc.shipservice.entities.Ship;
import com.hpc.shipservice.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
@RestController
@RequestMapping("/ships")
public class ShipController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ShipService shipService;

    @PostMapping("/add")
    public ResponseEntity<?> addNewShipInfo(@RequestBody Ship ship){
        return shipService.addNewShipInfo(ship);
    }

    @GetMapping("/getall")
    public Collection<Ship> getShips() {
        return shipService.getShips();
    }

    @GetMapping("/get/{shipCode}")
    public ResponseEntity<?> getShipsByShipCode(@PathVariable("shipCode") String shipCode) {
        return shipService.getShipByShipCode(shipCode);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateShipInfo(@RequestBody Ship ship){
        return shipService.updateShipInfo(ship);
    }

    @DeleteMapping("/delete/{shipCode}")
    public ResponseEntity<?> deleteShipInfo(@PathVariable("shipCode") String shipCode){
        return shipService.deleteShipInfo(shipCode);
    }

    @GetMapping("/getAllShipsPage")
    public ResponseEntity<?> getAllShipsPage(@RequestParam(required = false) String shipName,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "2") int size,
                                                    @RequestParam(defaultValue = "id,desc") String[] sort) {
        return shipService.getAllShipsPage(shipName,page,size,sort);
    }
}
