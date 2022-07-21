package com.futureh.dronefeeder.service;

import com.futureh.dronefeeder.dto.DeliveryDto;
import com.futureh.dronefeeder.model.Delivery;
import com.futureh.dronefeeder.repository.DeliveryRepository;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
  @Autowired
  private DeliveryRepository repository;

  /**
   * Lists all deliveries.
   * 
   */
  public List<Delivery> list() {
    List<Delivery> deliveries = repository.findAll();

    return deliveries;
  }

  public Delivery findById(int id) {
    return repository.findById(id).orElse(null);
  }

  /**
   * Updates a delivery.
   * 
   */
  @Transactional
  public Delivery update(int id, DeliveryDto delivery) {
    Delivery toUpdate = repository.findById(id).orElse(null);

    toUpdate.setDeliveryTime(LocalDateTime.now().toString());
    toUpdate.setStatus(delivery.getStatus());

    return repository.save(toUpdate);
  }

  @Transactional
  public void delete(int id) {
    Delivery deliveryToDelete = repository.findById(id).orElse(null);
    repository.delete(deliveryToDelete);
  }

}
