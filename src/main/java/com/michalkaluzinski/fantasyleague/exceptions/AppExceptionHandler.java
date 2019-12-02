package com.michalkaluzinski.fantasyleague.exceptions;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.michalkaluzinski.fantasyleague.dtos.ExceptionDTO;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class AppExceptionHandler {

  @ExceptionHandler(ResourceExistsException.class)
  public ResponseEntity<ExceptionDTO> handleResourceExistsException(Exception exception) {
    ExceptionDTO exceptionDTO = new ExceptionDTO();
    exceptionDTO.setMessage(exception.getMessage());
    exceptionDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(exceptionDTO, HttpStatus.OK);
  }

  @ExceptionHandler(value = EmailException.class)
  public ResponseEntity<ExceptionDTO> handleEmailException(Exception exception) {
    ExceptionDTO exceptionDTO = new ExceptionDTO();
    exceptionDTO.setMessage(exception.getMessage());
    exceptionDTO.setStatusCode(HttpStatus.SERVICE_UNAVAILABLE.value());
    return new ResponseEntity<>(exceptionDTO, HttpStatus.OK);
  }

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ExceptionDTO> handleException(Exception exception) {
    log.info("Unknown exception occured: ", exception);
    ExceptionDTO exceptionDTO = new ExceptionDTO();
    exceptionDTO.setMessage("Internal server error.");
    exceptionDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    return new ResponseEntity<>(exceptionDTO, HttpStatus.OK);
  }

  @ExceptionHandler(value = NotFoundException.class)
  public ResponseEntity<ExceptionDTO> handleNotFoundException(Exception exception) {
    ExceptionDTO exceptionDTO = new ExceptionDTO();
    exceptionDTO.setMessage(exception.getMessage());
    exceptionDTO.setStatusCode(HttpStatus.NOT_FOUND.value());
    return new ResponseEntity<>(exceptionDTO, HttpStatus.OK);
  }

  @ExceptionHandler(value = ConstraintViolationException.class)
  public ResponseEntity<ExceptionDTO> handleConstraintViolationException(
      ConstraintViolationException exception) {

    Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
    Set<String> messages = new HashSet<>(constraintViolations.size());
    messages.addAll(
        constraintViolations
            .stream()
            .map(
                constraintViolation ->
                    String.format(
                        "%s value '%s' %s",
                        constraintViolation.getPropertyPath(),
                        constraintViolation.getInvalidValue(),
                        constraintViolation.getMessage()))
            .collect(Collectors.toList()));
    StringBuilder sb = new StringBuilder();
    messages.forEach(sb::append);
    ExceptionDTO exceptionDTO = new ExceptionDTO();
    exceptionDTO.setMessage(sb.toString());
    exceptionDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(exceptionDTO, HttpStatus.OK);
  }
}
