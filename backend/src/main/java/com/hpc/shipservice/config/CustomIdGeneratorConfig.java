package com.hpc.shipservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CustomIdGeneratorConfig {

    @Bean
    public Map<Integer,String> generateCodes(){

        Map<Integer,String> codeMap = new HashMap<>();

        Integer count = 1;
        for(int i=65; i<=90; i++){
            for(int j=0; j<10; j++){
                Character c = (char) i;
                codeMap.put(count, c.toString()+j);
                count++;
            }
        }

        return codeMap;
    }
}
