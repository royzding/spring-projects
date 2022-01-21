package com.sample.microservices.department.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sample.microservices.department.exception.InternalRuntimeException;
import com.sample.microservices.department.exception.NotFoundException;
import com.sample.microservices.department.exception.ValidationFailureException;

@RestControllerAdvice
class GlobalControllerExceptionHandler {

  private static final Logger LOG = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

  @ResponseStatus(NOT_FOUND)
  @ExceptionHandler(NotFoundException.class)
  public @ResponseBody HttpErrorInfo handleNotFoundExceptions(
    ServerHttpRequest request, NotFoundException ex) {

    return createHttpErrorInfo(NOT_FOUND, request, ex);
  }

  @ResponseStatus(BAD_REQUEST)
  @ExceptionHandler(ValidationFailureException.class)
  public @ResponseBody HttpErrorInfo handleValidationFailureExceptions(
    ServerHttpRequest request, ValidationFailureException ex) {

    return createHttpErrorInfo(BAD_REQUEST, request, ex);
  }

  @ResponseStatus(INTERNAL_SERVER_ERROR)
  @ExceptionHandler(InternalRuntimeException.class)
  @ResponseBody
  public HttpErrorInfo handleInternalRuntimeExceptions(
    ServerHttpRequest request, InternalRuntimeException ex) {

    return createHttpErrorInfo(INTERNAL_SERVER_ERROR, request, ex);
  }
  
  private HttpErrorInfo createHttpErrorInfo(
    HttpStatus httpStatus, ServerHttpRequest request, Exception ex) {

    final String path = request.getPath().pathWithinApplication().value();
    final String message = ex.getMessage();

    LOG.debug("Returning HTTP status: {} for path: {}, message: {}", httpStatus, path, message);
    return new HttpErrorInfo(httpStatus, path, message);
  }
  
}
