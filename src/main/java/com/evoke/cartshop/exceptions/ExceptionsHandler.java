package com.evoke.cartshop.exceptions;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler({DuplicateAccountException.class})
    public ResponseEntity<Object> handleDuplicateAccountException(DuplicateAccountException e){
      return new ResponseEntity(
              e.getMessage(),new HttpHeaders(),HttpStatus.CONFLICT);
    }

  @ExceptionHandler({ResourceNotFoundException.class})
  public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e){
    return new ResponseEntity(
            e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

}


