package com.example.demo.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.rest.response.BadResponse;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
      List<String> details = new ArrayList<>();
      for(ObjectError error : ex.getBindingResult().getAllErrors()) {
          details.add(error.getDefaultMessage());
      }
      return new ResponseEntity<Object>(BadResponse.builder()
      		.message(ex.getMessage())
      		.details(details)
      		.build()
      		,HttpStatus.BAD_REQUEST);
  }
}