package com.futureh.dronefeeder.repository;

import com.futureh.dronefeeder.model.Drone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Integer> {
}
