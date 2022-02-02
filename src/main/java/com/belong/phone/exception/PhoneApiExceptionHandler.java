package com.belong.phone.exception;

import com.belong.phone.api.ApiError;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class PhoneApiExceptionHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler(CustomerNotFoundException.class)
  public ResponseEntity<ApiError> handleCustomerNotFound(CustomerNotFoundException cnfe) {
    return new ResponseEntity<ApiError>(ApiError.builder().message(cnfe.getMessage())
    .httpStatus(HttpStatus.NOT_FOUND).build(), HttpStatus.NOT_FOUND);
  }
  
}
