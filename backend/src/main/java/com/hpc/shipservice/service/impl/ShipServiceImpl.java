package com.hpc.shipservice.service.impl;

import com.hpc.shipservice.entities.Ship;
import com.hpc.shipservice.models.Response;
import com.hpc.shipservice.repositories.ShipRepository;
import com.hpc.shipservice.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service for handing ship related operations
 * @author Shivani Vishwakarma
 * @since 29.07.21
 */

@Service
public class ShipServiceImpl implements ShipService {

    @Autowired
    ShipCodeGeneratorServiceImpl shipCodeGeneratorServiceImpl;

    @Autowired
    ShipRepository shipRepository;

    /**
     * Method for adding a new ship in the database. It makes use of the ship code generator to generate unique ship code.
     * @param ship
     * @return successful response in case of successful addition, else returns failure message in response entity
     */
    public ResponseEntity<Response> addNewShipInfo(Ship ship) {
        Response response = new Response();
        Ship newShip = shipRepository.save(ship);
        String code = shipCodeGeneratorServiceImpl.generateShipCode(newShip.getId());
        Optional<Ship> s1 = shipRepository.findById(newShip.getId());
        newShip.setShipCode(code);
        if (s1.isPresent()) {
            shipRepository.save(newShip);
            response.setMessage("Ship Added Successfully");
            response.setStatus(true);
            response.setData(newShip);
        } else {
            response.setMessage("Failed to add ship");
            response.setStatus(false);
        }
        return ResponseEntity.ok(response);
    }

    /** Method for finding all the list of ships in the database
     * @return List of ships
     */
    public Collection<Ship> getShips() {
        return shipRepository.findAll();
    }

    /**
     * Method for finding a ship record for the given ship code.
     * @param shipCode
     * @return successful response in case of successful retrieval, else returns failure message in response entity
     */
    public ResponseEntity<?> getShipByShipCode(String shipCode) {
        Response response = new Response();
        Optional<Ship> s = shipRepository.findByShipCode(shipCode);
        if (s.isPresent()) {
            response.setMessage("Ship fetched Successfully");
            response.setStatus(true);
            response.setData(s.get());
        } else {
            response.setStatus(false);
            response.setMessage("Ship does not exists with " + shipCode);
        }
        return ResponseEntity.ok(response);
    }

    /**
     * Method for finding a ship record for the given ship code.
     * @param shipCode
     * @return ship in case of successful retrieval, else returns failure
     */
    public Optional<Ship> getShip(String shipCode) {
        Response response = new Response();
        Optional<Ship> s = shipRepository.findByShipCode(shipCode);
        if (s.isPresent()) {
            response.setMessage("Ship fetched Successfully");
            response.setStatus(true);
            response.setData(s.get());
        } else {
            response.setStatus(false);
            response.setMessage("Ship does not exists with " + shipCode);
        }
        return s;
    }

    /**
     * Method for finding a ship record with given ship details
     * @param ship
     * @return successful response in case of successful retrieval, else returns failure message in response entity
     */
    public ResponseEntity<?> updateShipInfo(Ship ship) {
        Response response = new Response();
        Optional<Ship> s = getShip(ship.getShipCode());
        if (s.isPresent()) {
            ship.setShipCode(s.get().getShipCode());
            ship.setId(s.get().getId());
            shipRepository.save(ship);
            response.setData(ship);
            response.setMessage("Ship Updated Successfully");
            response.setStatus(true);
        } else {
            response.setStatus(false);
            response.setMessage("Failed to update ship info ");
        }
        return ResponseEntity.ok(response);
    }

    /**
     * Method for deleting a ship record with given ship code
     * @param shipCode
     * @return successful response in case of successful retrieval, else returns failure message in response entity
     */
    public ResponseEntity<?> deleteShipInfo(String shipCode) {
        return ResponseEntity.ok(shipRepository.deleteShipByShipCode(shipCode));
    }

    /**
     * Method for converting direction in the api param from asc to Sort.Direction.ACS and desc to Sort.Direction.DESC
     * @param direction
     * @return sort direction
     */
    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    /**
     * Method to fetch the ship records based on the below params.
     * This method is created for server side pagination.
     * @param shipName - search string
     * @param page - page number to be retrieved
     * @param size - number of records per page
     * @param sort - sorting field if any along with order
     * @return successful response in case of successful retrieval, else returns failure message in response entity
     */
    public ResponseEntity<?> getAllShipsPage(String shipName, int page, int size, String[] sort) {

        Response resp = new Response();
        try {
            List<Sort.Order> orders = new ArrayList<Sort.Order>();
            if (sort[0].contains(",")) {
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
            }
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));
            Page<Ship> pageShips;
            if (shipName == null)
                pageShips = shipRepository.findAll(pagingSort);
            else
                pageShips = shipRepository.findByShipNameContaining(shipName, pagingSort);

            List<Ship> shipList = pageShips.getContent();
            resp.setData(shipList);
            resp.setStatus(true);
            resp.setMessage("Successfully retrieved ship info");
            return new ResponseEntity(resp, HttpStatus.OK);

        } catch (Exception e) {
            resp.setStatus(false);
            resp.setMessage("Failed to retrieve data");
            return new ResponseEntity(resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

