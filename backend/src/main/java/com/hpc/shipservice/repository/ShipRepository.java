package com.hpc.shipservice.repository;

import com.hpc.shipservice.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<Ship,String> {
    Optional<Ship> findByShipCode(String shipCode);
    Optional<Ship> findById(Integer id);
}
