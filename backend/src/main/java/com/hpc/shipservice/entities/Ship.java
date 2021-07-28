package com.hpc.shipservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String shipCode;
    private String shipName;
    private Float shipLengthInMeters;
    private Float shipWidthInMeters;

    /*
    Each ship must have a name (string), length (in metres), width (in metres) and code
    (a string with a format of AAAA-1111-A1

    0000, 0001
    SHIP-1000-A1, SHIP-1000-A1, SHIP-1000-A3, SHIP-1000-A1...SHIP-1000-B1, SHIP-1000-B1.... SHIP-1000-Z9
    SHIP-1001-A1, SHIP-1001-A2, SHIP-1001-A3, SHIP-1001-A1...SHIP-1001-B1, SHIP-1001-B1.... SHIP-1001-Z9
    .
    SHIP-9999-A1, SHIP-9999-A2, SHIP-9999-A3, SHIP-9999-A1...SHIP-9999-B1, SHIP-9999-B1.... SHIP-9999-Z9
    where A is any character from the Latin alphabet and 1 is a number from 0 to 9).
     */
}
