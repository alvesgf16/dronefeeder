package com.futureh.dronefeeder.service;

import com.futureh.dronefeeder.dto.DroneDto;
import com.futureh.dronefeeder.exception.DroneNotFoundException;
import com.futureh.dronefeeder.model.Delivery;
import com.futureh.dronefeeder.model.Drone;
import com.futureh.dronefeeder.repository.DroneRepository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  /**
   * Finds a drone by its id.
   * 
   */
  public Drone findById(int id) {
    Optional<Drone> drone = repository.findById(id);

    if (drone.isEmpty()) {
      throw new DroneNotFoundException();
    }

    return drone.get();
  }

  /**
   * Updates the localization of a drone.
   * 
   */
  @Transactional
  public Drone updateLocalization(int id, DroneDto drone) {
    Drone droneToSave = findById(id);

    droneToSave.setLatitude(drone.getLatitude());
    droneToSave.setLongitude(drone.getLongitude());

    return repository.save(droneToSave);
  }

  /**
   * Creates a delivery and associates it to a drone.
   * 
   */
  @Transactional
  public Drone addDelivery(Integer id) {
    Drone drone = findById(id);

    Delivery delivery = new Delivery();

    delivery.setDrone(drone);
    drone.addDelivery(delivery);

    return repository.save(drone);
  }

  /**
   * List the deliveries of a drone.
   * 
   */
  public List<Delivery> listDeliveries(Integer id) {
    Drone drone = findById(id);

    return drone.getDeliveries();
  }

  @Transactional
  public void delete(int id) {
    Drone droneToDelete = findById(id);
    repository.delete(droneToDelete);
  }
}
