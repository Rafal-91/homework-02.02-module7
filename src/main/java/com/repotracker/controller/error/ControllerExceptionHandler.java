package com.repotracker.controller.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorNotFoundUserResponseDto> handleUserNotFoundException(UserNotFoundException ex) {
        log.warn("error while founding user");
        ErrorNotFoundUserResponseDto response = new ErrorNotFoundUserResponseDto(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @ExceptionHandler(HttpMediaTypeException.class)
    public ResponseEntity<ErrorFormatExceptionResponseDto> handleHttpMediaTypeException() {
        log.warn("error with http media type");
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorFormatExceptionResponseDto(HttpStatus.NOT_ACCEPTABLE, "invalid http media type"));
    }
}
