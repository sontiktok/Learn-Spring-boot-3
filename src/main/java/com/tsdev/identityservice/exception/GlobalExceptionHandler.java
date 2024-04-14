package com.tsdev.identityservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


//Khi co exception thi se su ly o day
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class )
    ResponseEntity<String> runtimeExceptionHandler(RuntimeException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(exception.getFieldError().getDefaultMessage());
    }
}
