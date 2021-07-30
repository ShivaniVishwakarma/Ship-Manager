package com.hpc.shipservice.service;

import com.hpc.shipservice.entities.Ship;
import com.hpc.shipservice.models.Response;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Optional;

public interface ShipService {

    ResponseEntity<Response> addNewShipInfo(Ship ship);
    Collection<Ship> getShips();
    ResponseEntity<?> getShipByShipCode(String shipCode);
    Optional<Ship> getShip(String shipCode);
    ResponseEntity<?> updateShipInfo(Ship ship);
    ResponseEntity<?> deleteShipInfo(String shipCode);
    ResponseEntity<?> getAllShipsPage(String shipName, int page, int size, String[] sort);
}
