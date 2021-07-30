package com.hpc.shipservice.models;

import lombok.Data;
import lombok.ToString;

/**
 * A model class for taking the user credentials for any login request
 * @author Shivani Vishwakarma
 * @since 29.07.21
 */

@Data
@ToString
public class LoginUser {
    private String username;
    private String password;
}
