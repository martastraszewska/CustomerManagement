package com.example.customermanagement.api;

import com.example.customermanagement.app.validators.CustomerNotFoundException;
import com.example.customermanagement.app.validators.InvalidCustomerException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = InvalidCustomerException.class)
    protected ResponseEntity<Object> handleInvalidCustomerException(InvalidCustomerException ex,
                                                                    HttpServletRequest request) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(value = CustomerNotFoundException.class)
    protected ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException ex,
                                                                     HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatusCode.valueOf(404))
                .body(new ErrorResponse(ex.getMessage()));
    }
}
