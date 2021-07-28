package com.hpc.shipservice.service;

import com.hpc.shipservice.entities.Ship;
import com.hpc.shipservice.models.Response;
import com.hpc.shipservice.repositories.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShipService {

    @Autowired
    ShipCodeGenerator shipCodeGenerator;

    @Autowired
    ShipRepository shipRepository;

    public ResponseEntity<Response> addNewShipInfo(Ship ship) {
        Response response = new Response();
        Ship newShip = shipRepository.save(ship);
        String code = shipCodeGenerator.generateShipCode(newShip.getId());
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

    public Collection<Ship> getShips() {
        return shipRepository.findAll();
    }

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

    public ResponseEntity<?> deleteShipInfo(String shipCode) {
        return ResponseEntity.ok(shipRepository.deleteShipByShipCode(shipCode));
    }

    public ResponseEntity<List<Ship>> getAllSortedShips(String[] sort) {
        try {
            List<Sort.Order> orders = new ArrayList<Sort.Order>();

            if (sort[0].contains(",")) {
                // will sort more than 2 fields
                // sortOrder="field, direction"
                //localhost:8080/ships/getAllSortedShips?sort=id,desc&sort=shipCode,desc
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                //localhost:8080/ships/getAllSortedShips?sort=id,desc
                // sort=[field, direction]
                orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
            }

            List<Ship> Ships = shipRepository.findAll(Sort.by(orders));

            if (Ships.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(Ships, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }

    public ResponseEntity<List<Ship>> getAllShipsPage(String shipName, int page, int size, String[] sort) {

        Response resp = new Response();
        try {
            List<Sort.Order> orders = new ArrayList<Sort.Order>();

            if (sort[0].contains(",")) {
                // will sort more than 2 fields
                // sortOrder="field, direction"
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                // sort=[field, direction]
                orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
            }

            List<Ship> Ships = new ArrayList<Ship>();
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

            Page<Ship> pageTuts;
            if (shipName == null)
                pageTuts = shipRepository.findAll(pagingSort);
            else
                pageTuts = shipRepository.findByShipNameContaining(shipName, pagingSort);

            Ships = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("Ships", Ships);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            resp.setData(response);
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

