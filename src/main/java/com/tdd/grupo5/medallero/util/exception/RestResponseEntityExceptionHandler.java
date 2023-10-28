package com.tdd.grupo5.medallero.util.exception;

import com.tdd.grupo5.medallero.exception.BaseAPIException;
import com.tdd.grupo5.medallero.exception.dto.ErrorResponse;
import com.tdd.grupo5.medallero.util.handler.UnprocessableEntityResponse;
import com.tdd.grupo5.medallero.util.handler.Validation;
import jakarta.persistence.OptimisticLockException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.HashSet;
import java.util.Set;


@RestControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  private static final String LOCK_ERROR = "optimistic_lock_error";
  private static final String LOG_FLAGS = "[message:{}][error:{}][status:{}]";
  private static final String CAUSE_FLAGS = LOG_FLAGS + "[cause:[{}]]";


  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex) {
    final Set<Validation> validations = new HashSet<>();
    for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
      validations.add(
          new Validation(violation.getPropertyPath().toString(), violation.getMessage()));
    }
    final UnprocessableEntityResponse response = new UnprocessableEntityResponse("", validations);
    log.info(
        "[method:exception-handler][exception:ConstraintViolationException]"
            + CAUSE_FLAGS,
        response.getMessage(), response.getError(), response.getStatus(),
        response.getValidationErrorsMessage());
    return new ResponseEntity<>(response, new HttpHeaders(), response.getStatus());
  }

  @ExceptionHandler({BaseAPIException.class})
  public ResponseEntity<Object> handleBaseHttpException(final BaseAPIException ex) {
    ErrorResponse errorResponse;
    if (ex.getStatus() == null) {
      errorResponse = new ErrorResponse(ex);
    } else {
      errorResponse = new ErrorResponse(ex.getStatus(), ex.getError(), ex.getMessage());
    }
    log.info(
        "[method:exception-handler][exception:BaseHttpException]"
            + LOG_FLAGS,
        ex.getMessage(), ex.getError(),
        ex.getStatus());
    return new ResponseEntity<>(errorResponse, new HttpHeaders(), errorResponse.getStatus());
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleAll(final Exception ex) {
    final ErrorResponse errorResponse =
        new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "internal_server_error", "Something didnt work well.");
    log.error("[method:exception-handler][exception:Exception]"
            + LOG_FLAGS,
        errorResponse.getMessage(),
        errorResponse.getError(), errorResponse.getStatus());
    log.error("[method:exception-handler][exception:Exception]", ex);
    return new ResponseEntity<>(errorResponse, new HttpHeaders(), errorResponse.getStatus());
  }

  @ExceptionHandler(OptimisticLockException.class)
  public ResponseEntity<Object> handleOptimisticLockException(final OptimisticLockException ex) {
    final ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.CONFLICT,
        LOCK_ERROR, ex.getMessage());
    log.info(
        "[method:exception-handler][exception:OptimisticLockException]"
            + LOG_FLAGS,
        errorResponse.getMessage(), errorResponse.getError(), HttpStatus.CONFLICT.value(), ex);
    return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.CONFLICT);
  }

  @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
  public ResponseEntity<Object> handleObjectOptimisticLockingFailureExceptionException(
      final ObjectOptimisticLockingFailureException ex) {
    final ErrorResponse errorResponse =
        new ErrorResponse(
            HttpStatus.CONFLICT,
            LOCK_ERROR, ex.getMessage());
    log.info(
        "[method:exception-handler][exception:ObjectOptimisticLockingFailureException]"
            + LOG_FLAGS,
        errorResponse.getMessage(), errorResponse.getError(), HttpStatus.CONFLICT.value(), ex);
    return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.CONFLICT);
  }
}
