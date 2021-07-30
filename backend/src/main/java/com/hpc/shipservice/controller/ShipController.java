package com.hpc.shipservice.controller;

import com.hpc.shipservice.entities.Ship;
import com.hpc.shipservice.service.impl.ShipServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Rest controller for handing ship related request from user like fetch, delete, add, update details
 * @author Shivani Vishwakarma
 * @since 29.07.21
 */

@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
@RestController
@RequestMapping("/ships")
public class ShipController {

    @Autowired
    private ShipServiceImpl shipServiceImpl;

    /**
     * This is a GET api which fetches all the ship records from database and returns it to the client
     * @return List of all the ship records present in the database
     */
    @GetMapping("/getall")
    public Collection<Ship> getShips() {
        return shipServiceImpl.getShips();
    }

    /**
     * This is a GET api which fetches the ship record based on the given shipCode and returns it to the client
     * @param shipCode
     * @return Ship records present for the given shipCode
     */
    @GetMapping("/get/{shipCode}")
    public ResponseEntity<?> getShipsByShipCode(@PathVariable("shipCode") String shipCode) {
        return shipServiceImpl.getShipByShipCode(shipCode);
    }

    /**
     * This is a POST api which adds a new ship record into the database
     * @return successful response in case of successful addition, else returns failure message to client
     */
    @PostMapping("/add")
    public ResponseEntity<?> addNewShipInfo(@RequestBody Ship ship){
        return shipServiceImpl.addNewShipInfo(ship);
    }

    /**
     * This is a PUT api which updates a ship record
     * @param ship
     * @return successful response in case of successful update, else returns failure message to client
     */
    @PutMapping("/update")
    public ResponseEntity<?> updateShipInfo(@RequestBody Ship ship){
        return shipServiceImpl.updateShipInfo(ship);
    }

    /**
     * This is a DELETE api which deletes a ship record for a given shipCode
     * @param shipCode
     * @return successful response in case of successful deletion, else returns failure message to client
     */
    @DeleteMapping("/delete/{shipCode}")
    public ResponseEntity<?> deleteShipInfo(@PathVariable("shipCode") String shipCode){
        return shipServiceImpl.deleteShipInfo(shipCode);
    }

    /**
     * This is a GET api which fetches the ship records based on the below params.
     * This method is for server side pagination
     * @param shipName - search string
     * @param page - page number to be retrieved
     * @param size - number of records per page
     * @param sort - sorting field if any along with order
     * @return List of ship records based on matching criteria
     */
    @GetMapping("/getAllShipsPage")
    public ResponseEntity<?> getAllShipsPage(@RequestParam(required = false) String shipName,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "2") int size,
                                                    @RequestParam(defaultValue = "id,desc") String[] sort) {
        return shipServiceImpl.getAllShipsPage(shipName,page,size,sort);
    }
}
