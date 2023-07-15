package com.spring30.spring310;


import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.HttpHeaders;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Handle all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("Something went wrong.");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Hnadle unseccuessful delation
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Customer not found.");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle unsuccessful interaction
    @ExceptionHandler(InteractionNotFoundException.class)
    public ResponseEntity<Object> handleInteractionNotFound(InteractionNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle Validation Errors

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        // Get list of all validation errors from the exception object
        List<ObjectError> validationErrors = ex.getBindingResult().getAllErrors();

        // Create a StringBuilder to store all error messages
        StringBuilder sb = new StringBuilder();

        // Loop through all the errors and append the error messages to the
        // StringBuilder
        for (ObjectError error : validationErrors) {
            sb.append(error.getDefaultMessage() + " ");
        }

        // Create custom error response
        ErrorResponse errorResponse = new ErrorResponse(sb.toString());

        // Return
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }
}
