package com.futureh.dronefeeder.service;

import com.futureh.dronefeeder.dto.DeliveryDto;
import com.futureh.dronefeeder.model.Delivery;

import java.util.List;

public interface DeliveryService {

  List<Delivery> list();

  Delivery findById(int id);

  Delivery create(Delivery delivery);

  Delivery update(int id, DeliveryDto delivery);

  void delete(int id);
}
