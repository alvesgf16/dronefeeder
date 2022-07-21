package com.futureh.dronefeeder.controller;

import com.futureh.dronefeeder.dto.DroneDto;
import com.futureh.dronefeeder.exception.DataError;
import com.futureh.dronefeeder.exception.DroneNotFoundException;
import com.futureh.dronefeeder.model.Delivery;
import com.futureh.dronefeeder.model.Drone;
import com.futureh.dronefeeder.service.DroneService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("drones")
public class DroneController {
  @Autowired
  DroneService service;

  /**
   * Creates a drone.
   * 
   */
  @PostMapping
  public ResponseEntity<Drone> create(@RequestBody Drone drone) {
    Drone newDrone = service.create(drone);

    return ResponseEntity.status(HttpStatus.CREATED).body(newDrone);
  }

  /**
   * Lists all the drones.
   * 
   */
  @GetMapping
  public ResponseEntity<List<Drone>> list() {
    List<Drone> drones = service.list();

    return ResponseEntity.ok().body(drones);
  }

  /**
   * Finds a drone by its id.
   * 
   */
  @GetMapping("{id}")
  public ResponseEntity<Drone> findById(@PathVariable Integer id) {
    Drone drone = service.findById(id);

    return ResponseEntity.ok().body(drone);
  }

  /**
   * Updates the localization of a drone.
   * 
   */
  @PatchMapping("{id}/localization")
  public ResponseEntity<Drone> updateLocalization(@PathVariable Integer id,
      @RequestBody DroneDto drone) {
    Drone toUpdate = service.updateLocalization(id, drone);

    return ResponseEntity.ok().body(toUpdate);
  }

  /**
   * Creates a delivery and associates it to a drone.
   * 
   */
  @CircuitBreaker(name = "addDelivery", fallbackMethod = "addDeliveryFallback")
  @PatchMapping(value = "{id}/deliveries")
  public ResponseEntity<Drone> addDelivery(@PathVariable Integer id) {
    Drone drone = service.addDelivery(id);

    return ResponseEntity.ok().body(drone);
  }

  /**
   * Return method in case there is 50% error in addDelivery requests.
   * 
   */
  public ResponseEntity<DataError> addDeliveryFallback(@PathVariable Integer id, Exception e) {
    if (e.getClass() == DroneNotFoundException.class) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DataError(e.getMessage()));
    }

    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
        .body(new DataError("Service temporarily unavailable"));
  }

  /**
   * List the deliveries of a drone.
   * 
   */
  @GetMapping("{id}/deliveries")
  public ResponseEntity<List<Delivery>> listDeliveries(@PathVariable Integer id) {
    List<Delivery> deliveries = service.listDeliveries(id);

    return ResponseEntity.ok().body(deliveries);
  }

  /**
   * Deletes a drone.
   * 
   */
  @DeleteMapping("{id}")
  public ResponseEntity<Drone> delete(@PathVariable Integer id) {
    service.delete(id);

    return ResponseEntity.ok().build();
  }
}
