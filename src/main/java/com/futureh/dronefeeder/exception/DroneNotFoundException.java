package com.futureh.dronefeeder.exception;

public class DroneNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public DroneNotFoundException() {
    super("Drone not found");
  }

}
