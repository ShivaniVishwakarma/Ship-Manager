package com.hpc.shipservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Response model class to hold data which would be sent to requested client
 * @author Shivani Vishwakarma
 * @since 29.07.21
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private String message;
    private Object data;
    private boolean status;
}
