package com.salesianas.dam.replica.exception;
import org.springframework.http.HttpStatus;


import java.util.Collection;
public class ReplicaPermissionException extends ReplicaException{
    private static HttpStatus httpStatus = HttpStatus.FORBIDDEN;
    private String msg;
    private String errorCode;

    public ReplicaPermissionException(String msg, String errorCode) {
        super(errorCode, msg, httpStatus);
        this.msg = msg;
        this.errorCode = errorCode;
    }

    public ReplicaPermissionException(HttpStatus httpStatus, String msg, String errorCode) {
        super(errorCode, msg, httpStatus);
        this.httpStatus = httpStatus;
        this.msg = msg;
        this.errorCode = errorCode;
    }
}
