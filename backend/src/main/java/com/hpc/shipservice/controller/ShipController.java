package com.hpc.shipservice.controller;

import com.hpc.shipservice.entities.Ship;
import com.hpc.shipservice.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
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
        return shipService.getShips();
    }

    @GetMapping("/getallships")
    public ResponseEntity<?> getAllShips() {
        return shipService.getAllShips();
    }

    //Fetch ship by ShipCode
    @GetMapping("/get/{shipCode}")
    public ResponseEntity<?> getShipsByShipCode(@PathVariable("shipCode") String shipCode) {
        return shipService.getShipByShipCode(shipCode);
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

    //Get all ships in sorted order
    @GetMapping("/getAllSortedShips")
    public ResponseEntity<List<Ship>> getAllSortedShips(@RequestParam(defaultValue = "id,desc") String[] sort) {
        return shipService.getAllSortedShips(sort);
    }

    //Get all ships page
    @GetMapping("/getAllShipsPage")
    public ResponseEntity<?> getAllShipsPage(@RequestParam(required = false) String shipName,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "2") int size,
                                                    @RequestParam(defaultValue = "id,desc") String[] sort) {
        return shipService.getAllShipsPage(shipName,page,size,sort);
    }
}
