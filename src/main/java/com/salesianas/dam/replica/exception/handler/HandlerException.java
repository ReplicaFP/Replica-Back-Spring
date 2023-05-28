package com.salesianas.dam.replica.exception.handler;

import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.exception.dto.ErrorDto;
import com.salesianas.dam.replica.response.ReplicaResponse;
import com.salesianas.dam.replica.response.ReplicaResponseStatus;
import com.salesianas.dam.replica.utils.constant.ValidationConstants;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@ControllerAdvice
public class HandlerException {
    private static final Logger log = LogManager.getLogger(HandlerException.class);


    @Autowired
    private MessageSource messageSource;

    /**
     * Validation DTO's exceptions
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ReplicaResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        //
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        ErrorDto errorDto = new ErrorDto("400-01");
        errorDto.setValidationErr(errors);

        String responseErrMsg = errorDto.getError();
        errorDto.setError(String.format(ValidationConstants.VALIDATION_ERR_MSG, errors.size()));


        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.ERROR)
                .message(responseErrMsg)
                .data(errorDto)
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    /**
     * Access denied
     *
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ReplicaResponse> handleAccessDenied(final HttpServletRequest req, final AccessDeniedException ex) {
        logException(ex);

        ErrorDto errorDto = new ErrorDto("403");

        String responseErrMsg = errorDto.getError();
        errorDto.setError("Forbidden access");

        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.ERROR)
                .message(responseErrMsg)
                .data(errorDto)
                .build();

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(response);
    }

    /**
     * Customize exceptions
     * @param ex
     * @return
     */
    @ExceptionHandler({ReplicaException.class})
    public ResponseEntity<ReplicaResponse> handledErrors(ReplicaException ex) {
        logException(ex);

        String responseErrMsg = ex.getErrorDto().getError();
        ex.getErrorDto().setError(ex.getMessage());

        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.ERROR)
                .message(responseErrMsg)
                .data(ex.getErrorDto())
                .build();

        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(response);
    }


   /* @ExceptionHandler({Exception.class})
    public ResponseEntity<ReplicaResponse> unhandledErrors(final HttpServletRequest req, final Exception ex) {
        logException(ex);

        ErrorDto errorDto = new ErrorDto("500");

        String responseErrMsg = errorDto.getError();
        errorDto.setError(ex.getMessage());


        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.ERROR)
                .message(responseErrMsg)
                .data(errorDto)
                .build();

        return ResponseEntity
                .internalServerError()
                .body(response);
    }
*/
    private void logException(final Exception exception) {
        log.error(ExceptionUtils.getStackTrace(exception));
    }
}

