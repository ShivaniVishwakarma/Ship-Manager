package com.hpc.shipservice.service.impl;

import com.hpc.shipservice.service.ShipCodeGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Service for handing unique ship code generation with format AAAA-1111-A1
 * @author Shivani Vishwakarma
 * @since 29.07.21
 */

@Service
public class ShipCodeGeneratorServiceImpl implements ShipCodeGeneratorService {

    @Autowired
    Map<Integer,String> codes;

    private final Integer MAX_CODES = 234;
    private final String PREFIX = "SHIP-";

    /**
     * Generates a unique value of a ship code making in use the unique id attribute of the ship
     * @param id
     * @return ship code
     */
    @Override
    public String generateShipCode(Integer id){
        int i  = (id / MAX_CODES) + 1000;
        String c = codes.get(id % MAX_CODES);
        return PREFIX + i + "-" + c;
    }
}
