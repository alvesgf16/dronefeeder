package com.futureh.dronefeeder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.futureh.dronefeeder.model.Drone;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Integer> {
}
