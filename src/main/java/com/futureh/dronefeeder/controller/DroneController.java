package com.futureh.dronefeeder.controller;

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

import com.futureh.dronefeeder.dto.DroneDto;
import com.futureh.dronefeeder.model.Delivery;
import com.futureh.dronefeeder.model.Drone;
import com.futureh.dronefeeder.service.DroneService;

@RestController
@RequestMapping("drone")
public class DroneController {
  @Autowired
  DroneService service;

  @PostMapping
  public ResponseEntity<Drone> create(@RequestBody Drone drone) {
    Drone newDrone = service.create(drone);

    return ResponseEntity.status(HttpStatus.CREATED).body(newDrone);
  }

  @GetMapping
  public ResponseEntity<List<Drone>> list() {
    List<Drone> drones = service.list();

    return ResponseEntity.ok().body(drones);
  }

  @GetMapping("{id}")
  public ResponseEntity<Drone> findById(@PathVariable Integer id) {
    Drone drone = service.findById(id);

    return ResponseEntity.ok().body(drone);
  }

  @PatchMapping("{id}/localization")
  public ResponseEntity<Drone> updateLocalization(@PathVariable Integer id,
      @RequestBody DroneDto drone) {
    Drone toUpdate = service.updateLocalization(id, drone);

    return ResponseEntity.ok().body(toUpdate);
  }

  @PatchMapping("{id}/deliveries")
  public ResponseEntity<Drone> addDelivery(@PathVariable Integer id) {
    Drone drone = service.addDelivery(id);

    return ResponseEntity.ok().body(drone);
  }

  @GetMapping("{id}/deliveries")
  public ResponseEntity<List<Delivery>> listDeliveries(@PathVariable Integer id) {
    List<Delivery> deliveries = service.listDeliveries(id);

    return ResponseEntity.ok().body(deliveries);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Drone> delete(@PathVariable Integer id) {
    service.delete(id);

    return ResponseEntity.ok().build();
  }
}
