package com.salesianas.dam.replica.exception;

import org.springframework.http.HttpStatus;

public class ReplicaNotFoundException extends ReplicaException{

    private static final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    private String msg;
    private String errorCode;

    public ReplicaNotFoundException(String msg, String errorCode) {
        super(errorCode, msg, httpStatus);
        this.msg = msg;
        this.errorCode = errorCode;
    }

}

