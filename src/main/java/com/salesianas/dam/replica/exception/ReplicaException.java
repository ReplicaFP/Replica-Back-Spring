package com.salesianas.dam.replica.exception;
import com.salesianas.dam.replica.exception.dto.ErrorDto;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


@Data
public class ReplicaException extends RuntimeException{

    private HttpStatus httpStatus;
    private ErrorDto errorDto;
    private String msg;

    public ReplicaException(String errorCode, String msg, HttpStatus httpStatus) {
        super(msg);
        this.msg = msg;
        this.httpStatus = httpStatus;
        this.errorDto = new ErrorDto(errorCode);
    }

}
