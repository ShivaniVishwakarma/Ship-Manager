package com.hpc.shipservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private String message;
    private Object data;
    private boolean status;
}
