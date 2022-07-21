package com.futureh.dronefeeder.controller;

import com.futureh.dronefeeder.dto.DeliveryDto;
import com.futureh.dronefeeder.model.Delivery;
import com.futureh.dronefeeder.service.DeliveryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("deliveries")
public class DeliveryController {
  @Autowired
  DeliveryService service;

  /**
   * Lists all deliveries.
   * 
   */
  @GetMapping
  public ResponseEntity<List<Delivery>> list() {
    List<Delivery> deliveries = service.list();

    return ResponseEntity.ok().body(deliveries);
  }

  /**
   * Finds a delivery by its id.
   * 
   */
  @GetMapping("{id}")
  public ResponseEntity<Delivery> findById(@PathVariable Integer id) {
    Delivery delivery = service.findById(id);

    return ResponseEntity.ok().body(delivery);
  }

  /**
   * Updates a delivery.
   * 
   */
  @PutMapping("{id}")
  public ResponseEntity<Delivery> update(@PathVariable Integer id,
      @RequestBody DeliveryDto delivery) {
    Delivery toUpdate = service.update(id, delivery);

    return ResponseEntity.ok().body(toUpdate);
  }

  /**
   * Deletes a delivery.
   * 
   */
  @DeleteMapping("{id}")
  public ResponseEntity<Delivery> delete(@PathVariable Integer id) {
    service.delete(id);

    return ResponseEntity.ok().build();
  }
}
