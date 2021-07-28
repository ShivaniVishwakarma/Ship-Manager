package com.hpc.shipservice.entities;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginUser {

    private String username;
    private String password;
}