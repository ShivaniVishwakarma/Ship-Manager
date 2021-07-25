package com.hpc.shipservice.repository;

import com.hpc.shipservice.entity.Ship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<Ship,String> {
    Optional<Ship> findByShipCode(String shipCode);
    Optional<Ship> findById(Integer id);

    Page<Ship> findByShipCode(String shipName, Pageable pageable);

    Page<Ship> findByShipNameContaining(String shipName, Pageable pageable);

    List<Ship> findByShipNameContaining(String shipName, Sort sort);

}
