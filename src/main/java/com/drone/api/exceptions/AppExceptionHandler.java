package com.drone.api.exceptions;

import com.drone.api.common.model.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> handleClientErrorException(HttpClientErrorException exception) {
        return new ResponseEntity<>(exception.getMessage(), exception.getStatusCode());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<Object> handleAppException(ApiException exception) {
        ResponseDto responseDTO = new ResponseDto();
        responseDTO.addError(exception.getMessage());
        return new ResponseEntity<>(responseDTO, exception.getStatusCode());
    }

}
