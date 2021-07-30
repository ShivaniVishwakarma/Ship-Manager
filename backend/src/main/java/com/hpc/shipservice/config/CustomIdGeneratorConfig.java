package com.hpc.shipservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * A configuration class for creating a bean which returns the map of codes to generate ship codes
 * @author Shivani Vishwakarma
 * @since 29.07.21
 */

@Configuration
public class CustomIdGeneratorConfig {

    /**
     * A bean that generates a map of codes to be used for generating custom ship code values
     * @return code map
     */

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
