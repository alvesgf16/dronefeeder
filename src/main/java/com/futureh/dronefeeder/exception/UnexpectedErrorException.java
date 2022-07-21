package com.futureh.dronefeeder.exception;

public class UnexpectedErrorException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public UnexpectedErrorException() {
    super("Unexpected error");
  }
}
