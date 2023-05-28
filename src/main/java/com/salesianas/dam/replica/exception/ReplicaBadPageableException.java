package com.salesianas.dam.replica.exception;

import org.springframework.http.HttpStatus;

public class ReplicaBadPageableException extends ReplicaException {

    private static final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    private String msg;
    private String errorCode;

    public ReplicaBadPageableException(String msg, String errorCode) {
        super(errorCode, msg, httpStatus);
        this.msg = msg;
        this.errorCode = errorCode;
    }

}