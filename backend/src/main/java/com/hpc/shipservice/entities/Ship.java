package com.hpc.shipservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * An entity class for holding the ship information
 * @author Shivani Vishwakarma
 * @since 29.07.21
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String shipCode;
    private String shipName;
    private Float shipLengthInMeters;
    private Float shipWidthInMeters;
}
