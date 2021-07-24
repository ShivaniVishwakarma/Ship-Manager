package com.hpc.shipservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ShipCodeGenerator {

    @Autowired
    Map<Integer,String> codes;

    public String generateShipCode(Integer id){

        int max = 18;
        int i  = (Integer) (id/max) + 1000;
        String c = codes.get(id%max);
        return "SHIP-" + i + "-" + c;
    }
}
