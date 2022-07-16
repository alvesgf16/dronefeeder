package com.futureh.dronefeeder.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futureh.dronefeeder.model.Delivery;
import com.futureh.dronefeeder.repository.DeliveryRepository;

@Service
public class DeliveryService {
  @Autowired
  private DeliveryRepository repository;

  public List<Delivery> list() {
    return repository.findAll();
  }

  public Delivery findById(int id) {
    return repository.findById(id).orElse(null);
  }

  @Transactional
  public Delivery update(int id, Delivery delivery) {
    Delivery toUpdate = repository.findById(id).orElse(null);

    toUpdate.setDeliveryTime(delivery.getDeliveryTime());
    toUpdate.setStatus(delivery.getStatus());

    return repository.save(toUpdate);
  }

  @Transactional
  public void delete(int id) {
    Delivery deliveryToDelete = repository.findById(id).orElse(null);
    repository.delete(deliveryToDelete);
  }

}
