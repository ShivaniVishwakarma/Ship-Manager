package com.hpc.shipservice.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A model class for returning the token to the authenticated user
 * @author Shivani Vishwakarma
 * @since 29.07.21
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthToken {

    private String token;

}
