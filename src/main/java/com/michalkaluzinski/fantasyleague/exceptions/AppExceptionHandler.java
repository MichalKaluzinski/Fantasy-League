package com.michalkaluzinski.fantasyleague.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.michalkaluzinski.fantasyleague.dtos.ExceptionDTO;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class AppExceptionHandler {

  @ExceptionHandler(value = ResourceExistsException.class)
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
}
