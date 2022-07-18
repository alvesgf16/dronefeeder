package com.futureh.dronefeeder.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futureh.dronefeeder.dto.DroneDto;
import com.futureh.dronefeeder.model.Delivery;
import com.futureh.dronefeeder.model.Drone;
import com.futureh.dronefeeder.repository.DroneRepository;

@Service
public class DroneServiceImpl implements DroneService {
  @Autowired
  private DroneRepository repository;

  @Override
  @Transactional
  public Drone create(Drone drone) {
    return repository.save(drone);
  }

  @Override
  public List<Drone> list() {
    return repository.findAll();
  }

  public Drone findById(int id) {
    return repository.findById(id).orElse(null);
  }

  @Override
  @Transactional
  public Drone updateLocalization(int id, DroneDto drone) {
    Drone droneToSave = repository.findById(id).orElse(null);

    droneToSave.setLatitude(drone.getLatitude());
    droneToSave.setLongitude(drone.getLongitude());

    return repository.save(droneToSave);
  }

  @Override
  @Transactional
  public Drone addDelivery(Integer id) {
    Drone drone = findById(id);

    Delivery delivery = new Delivery();

    delivery.setDrone(drone);
    drone.addDelivery(delivery);

    return repository.save(drone);
  }

  @Override
  public List<Delivery> listDeliveries(Integer id) {
    Drone drone = findById(id);

    return drone.getDeliveries();
  }

  @Override
  @Transactional
  public void delete(int id) {
    Drone droneToDelete = repository.findById(id).orElse(null);
    repository.delete(droneToDelete);
  }
}
