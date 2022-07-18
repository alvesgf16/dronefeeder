package com.futureh.dronefeeder.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futureh.dronefeeder.dto.DeliveryDto;
import com.futureh.dronefeeder.model.Delivery;
import com.futureh.dronefeeder.repository.DeliveryRepository;

@Service
public class DeliveryServiceImpl implements DeliveryService {
  @Autowired
  private DeliveryRepository repository;

  @Override
  public List<Delivery> list() {
    List<Delivery> deliveries = repository.findAll();

    return deliveries;
  }

  @Override
  public Delivery findById(int id) {
    return repository.findById(id).orElse(null);
  }

  @Override
  @Transactional
  public Delivery create(Delivery delivery) {
    return repository.save(delivery);
  }

  @Override
  @Transactional
  public Delivery update(int id, DeliveryDto delivery) {
    Delivery toUpdate = repository.findById(id).orElse(null);

    toUpdate.setDeliveryTime(LocalDateTime.now());
    toUpdate.setStatus(delivery.getStatus());

    return repository.save(toUpdate);
  }

  @Override
  @Transactional
  public void delete(int id) {
    Delivery deliveryToDelete = repository.findById(id).orElse(null);
    repository.delete(deliveryToDelete);
  }

}