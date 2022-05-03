package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import apiException.NotFoundException;
import apiException.InvalidRequestException;
@ControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Object processNotFoundException(NotFoundException ex) {
    	
    	return ex.getMessage();
    }
    
    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Object processInvalidRequestException(InvalidRequestException ex) {
    	
    	return ex.getMessage();
    }
}