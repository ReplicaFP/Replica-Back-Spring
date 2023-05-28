package com.salesianas.dam.replica.exception.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;

@Data
@NoArgsConstructor
public class ErrorDto implements Serializable {
    private String code;
    private String error;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> validationErr;


    public ErrorDto(String errorCode) {
        this.code = errorCode;
        this.error = ResourceBundle.getBundle("error-messages").getString(errorCode);
    }

}
