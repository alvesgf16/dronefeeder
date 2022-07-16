package com.futureh.dronefeeder.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futureh.dronefeeder.model.Delivery;
import com.futureh.dronefeeder.model.Drone;
import com.futureh.dronefeeder.repository.DroneRepository;

@Service
public class DroneService {
  @Autowired
  private DroneRepository repository;

  @Transactional
  public Drone create(Drone drone) {
    return repository.save(drone);
  }

  public List<Drone> list() {
    return repository.findAll();
  }

  public Drone findById(int id) {
    return repository.findById(id).orElse(null);
  }

  @Transactional
  public Drone updateLocalization(int id, Drone drone) {
    Drone droneToSave = repository.findById(id).orElse(null);

    droneToSave.setLatitude(drone.getLatitude());
    droneToSave.setLongitude(drone.getLongitude());

    return repository.save(droneToSave);
  }

  @Transactional
  public Drone addDelivery(Integer id, Delivery delivery) {
    Drone drone = findById(id);

    delivery.setDrone(drone);
    drone.addDelivery(delivery);

    return repository.save(drone);
  }

  public List<Delivery> listDeliveries(Integer id) {
    Drone drone = findById(id);

    return drone.getDeliveries();
  }

  @Transactional
  public void delete(int id) {
    Drone droneToDelete = repository.findById(id).orElse(null);
    repository.delete(droneToDelete);
  }
}
