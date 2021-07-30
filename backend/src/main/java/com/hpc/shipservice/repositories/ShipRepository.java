package com.hpc.shipservice.repositories;

import com.hpc.shipservice.entities.Ship;
import com.hpc.shipservice.models.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * A JPA repository for handling the CRUD operations on a Ship entity
 * @author Shivani Vishwakarma
 * @since 29.07.21
 */

@Repository
public interface ShipRepository extends JpaRepository<Ship, String> {

    Optional<Ship> findByShipCode(String shipCode);

    Optional<Ship> findById(Integer id);

    Page<Ship> findByShipNameContaining(String shipName, Pageable pageable);

    default Response deleteShipByShipCode(String shipCode) {
        Response response = new Response();
        Optional<Ship> s = findByShipCode(shipCode);
        if (!s.isPresent()) {
            response.setStatus(false);
            response.setMessage("No ship exists with the given ship code");
        } else {
            delete(s.get());
            response.setStatus(true);
            response.setMessage("Ship deleted successfully");
        }
        return response;
    }
}
