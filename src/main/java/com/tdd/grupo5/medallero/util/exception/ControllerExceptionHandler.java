package com.tdd.grupo5.medallero.util.exception;

import com.tdd.grupo5.medallero.exception.BaseAPIException;
import com.tdd.grupo5.medallero.exception.dto.ErrorResponse;
import com.tdd.grupo5.medallero.util.handler.UnprocessableEntityResponse;
import com.tdd.grupo5.medallero.util.handler.Validation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

/** Basic handling for exceptions. */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

  private static final String LOG_FLAGS = "[message:{}][error:{}][status:{}]";
  private static final String CAUSE_FLAGS = LOG_FLAGS + "[cause:[{}]]";

  /**
   * Handler for not found routes.
   *
   * @param req the incoming request.
   * @param ex the exception thrown when route is not found.
   * @return {@link ResponseEntity} with 404 status code and the route that was not found in the
   *     body.
   */
  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<ErrorResponse> noHandlerFoundException(
      HttpServletRequest req, NoHandlerFoundException ex) {
    ErrorResponse ErrorResponse =
        new ErrorResponse(
            "route_not_found",
            String.format("Route %s not found", req.getRequestURI()),
            HttpStatus.NOT_FOUND.value());
    return ResponseEntity.status(ErrorResponse.getStatus()).body(ErrorResponse);
  }

  /**
   * Handler for external API exceptions.
   *
   * @param e the exception thrown during a request to external API.
   * @return {@link ResponseEntity} with status code and description provided for the handled
   *     exception.
   */
  @ExceptionHandler(BaseAPIException.class)
  protected ResponseEntity<ErrorResponse> handleApiException(BaseAPIException e) {
    Integer statusCode = e.getStatusCode();
    boolean expected = HttpStatus.INTERNAL_SERVER_ERROR.value() > statusCode;
    if (expected) {
      LOGGER.warn("Internal Api warn. Status Code: " + statusCode, e);
    } else {
      LOGGER.error("Internal Api error. Status Code: " + statusCode, e);
    }

    ErrorResponse ErrorResponse = new ErrorResponse(e.getError(), e.getMessage(), statusCode);
    return ResponseEntity.status(ErrorResponse.getStatus()).body(ErrorResponse);
  }

  /**
   * Handler for constriant exceptions.
   *
   * @param ex the exception thrown during request processing.
   * @return {@link ResponseEntity} with 400 status code and description indicating an internal
   *     error.
   */
  @ExceptionHandler({MethodArgumentNotValidException.class})
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException ex) {
    final Set<Validation> validations = new HashSet<>();
    for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
      validations.add(new Validation(error.getField(), error.getDefaultMessage()));
    }
    for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      validations.add(new Validation(error.getObjectName(), error.getDefaultMessage()));
    }
    final UnprocessableEntityResponse response = new UnprocessableEntityResponse("", validations);
    LOGGER.info(
        "[method:exception-handler][exception:MethodArgumentNotValidException]" + CAUSE_FLAGS,
        response.getMessage(),
        response.getError(),
        response.getStatus(),
        response.getCause());
    return new ResponseEntity<>(response, new HttpHeaders(), response.getStatus());
  }

  /**
   * Handler for constriant exceptions.
   *
   * @param ex the exception thrown during request processing.
   * @return {@link ResponseEntity} with 400 status code and description indicating an internal
   *     error.
   */
  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex) {
    final Set<Validation> validations = new HashSet<>();
    for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
      validations.add(
          new Validation(violation.getPropertyPath().toString(), violation.getMessage()));
    }
    final UnprocessableEntityResponse response = new UnprocessableEntityResponse("", validations);
    LOGGER.info(
        "[method:exception-handler][exception:ConstraintViolationException]" + CAUSE_FLAGS,
        response.getMessage(),
        response.getError(),
        response.getStatus(),
        response.getValidationErrorsMessage());
    return new ResponseEntity<>(response, new HttpHeaders(), response.getStatus());
  }
  /**
   * Handler for internal exceptions.
   *
   * @param e the exception thrown during request processing.
   * @return {@link ResponseEntity} with 500 status code and description indicating an internal
   *     error.
   */
  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ErrorResponse> handleUnknownException(Exception e) {
    LOGGER.error("Internal error", e);

    ErrorResponse ErrorResponse =
        new ErrorResponse(
            "internal_error", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR.value());
    return ResponseEntity.status(ErrorResponse.getStatus()).body(ErrorResponse);
  }
}
