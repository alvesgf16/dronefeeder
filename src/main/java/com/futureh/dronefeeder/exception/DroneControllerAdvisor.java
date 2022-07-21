package com.futureh.dronefeeder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class DroneControllerAdvisor {
  @ExceptionHandler(DroneNotFoundException.class)
  public ResponseEntity<DataError> handleNotFound(RuntimeException error) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DataError(error.getMessage()));
  }

  @ExceptionHandler({UnexpectedErrorException.class, RuntimeException.class})
  public ResponseEntity<DataError> handleServerError(RuntimeException error) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new DataError(error.getMessage()));
  }
}
