package com.hpc.shipservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Pravin P Patil
 * @apiNote Response class to hold data which would be sent to requested client
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private String message;
    private Object data;
    private boolean status;
}
