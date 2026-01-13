package org.snakeInc.api.service;

import org.snakeInc.api.errors.ErrorDTO;
import org.snakeInc.api.errors.PlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handlePlayerNotFound(PlayerNotFoundException ex) {
        return new ErrorDTO(ex.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> exceptionHandler(MethodArgumentNotValidException e) {
        return new ResponseEntity<ErrorDTO>(new ErrorDTO(e), HttpStatusCode.valueOf(404));
    }
}
