package com.futureh.dronefeeder.service;

import com.futureh.dronefeeder.dto.DroneDto;
import com.futureh.dronefeeder.model.Delivery;
import com.futureh.dronefeeder.model.Drone;

import java.util.List;

public interface DroneService {

  Drone create(Drone drone);
  List<Drone> list();

  Drone findById(int id);

  Drone updateLocalization(int id, DroneDto drone);

  Drone addDelivery(Integer id);

  List<Delivery> listDeliveries(Integer id);

  void delete(int id);
}
