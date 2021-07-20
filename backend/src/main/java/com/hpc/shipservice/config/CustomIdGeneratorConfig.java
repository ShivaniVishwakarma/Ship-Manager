package com.hpc.shipservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CustomIdGeneratorConfig {

    @Bean
    public Map<Integer,String> generateCodes(){

        Map<Integer,String> codes = new HashMap<>();
        codes.put(1,"A1");
        codes.put(2,"A2");
        codes.put(3,"A3");
        codes.put(4,"A4");
        codes.put(5,"A5");
        codes.put(6,"A6");
        codes.put(7,"A7");
        codes.put(8,"A8");
        codes.put(9,"A9");
        codes.put(10,"B1");
        codes.put(11,"B2");
        codes.put(12,"B3");
        codes.put(13,"B4");
        codes.put(14,"B5");
        codes.put(15,"B6");
        codes.put(16,"B7");
        codes.put(17,"B8");
        codes.put(18,"B9");
        return codes;
    }
}
